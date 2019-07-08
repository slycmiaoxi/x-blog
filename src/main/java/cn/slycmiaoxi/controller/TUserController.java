package cn.slycmiaoxi.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import cn.slycmiaoxi.service.ITUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.dto.UserDto;
import cn.slycmiaoxi.entity.TEmailCode;
import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.service.ITEmailCodeService;
import cn.slycmiaoxi.service.ITUserService;
import cn.slycmiaoxi.tools.MailUtil;
import cn.slycmiaoxi.tools.SendMailToSomeone;
import cn.slycmiaoxi.utils.R;
import cn.slycmiaoxi.utils.RRException;
import cn.slycmiaoxi.utils.RandUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
@Controller
@RequestMapping("/core/v1/tUser")
@Slf4j
public class TUserController {
    @Autowired
    private ITUserService itUserService;
    
    @Autowired
    private ITEmailCodeService itEmailCodeService;

    
    /**
     * 登录
     *
     * @param nickName 用户名
     * @param pwd 密码
     * @param request 域对象
     * @return 登录结果
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    @RequestMapping("/login")
    @ResponseBody
    public R login(String nickName, @RequestParam("userPwd") String pwd, HttpServletRequest request) {
        if (StringUtils.isEmpty(nickName) || StringUtils.isEmpty(pwd)) {
            throw new RRException("params is not empty !");
        }
        
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(nickName, pwd);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            
            TUser user = itUserService.getUserByNameAndPwd(nickName, pwd);
            user.setUserPwd("");
            subject.getSession().setAttribute(Constants.SESSION_USER, user);
            // 用户登录成功标志，用于登录日志统计
            request.setAttribute("flag", Enable.YES.getValue());
        }
        catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            return R.fail().put("msg", "用户名或者密码不对");
        }
        catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            return R.fail().put("msg", "用户名不存在");
        }
        catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            return R.fail().put("msg", "用户名错误登录过多");
        }
        catch (AuthenticationException eae) {
            // 捕获错误权限异常
            return R.fail().put("msg", "没有权限");
        }
        return R.ok().put("msg", "登录成功");
    }
    
    /**
     * 校验用户名是否已存在
     *
     * @param nickName 用户名
     * @return 校验结果 true - 存在 false -不存在
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    @RequestMapping("/checkRepeat")
    @ResponseBody
    public boolean checkRepeat(@RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(nickName)) {
            return true;
        }
        
        TUser user = itUserService.getUserByName(nickName);
        return null != user ? true : false;
    }
    
    /**
     * 发送邮件
     *
     * @param eMail 邮箱地址
     * @return 验证码
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    @RequestMapping("sendEmail")
    @ResponseBody
    public R sendEmail(@RequestParam("email") String eMail) {
        if (StringUtils.isEmpty(eMail)) {
            return R.fail();
        }
        
        // 1.邮箱是否已经被注册
        TEmailCode emailCode = itEmailCodeService.getOneByEmail(eMail);
        if (null != emailCode) {
            return R.fail().put("msg", "该邮箱已经被注册！");
        }
        
        try {
            // 2.生成验证码
            String code = RandUtil.createActiveCode(6);
            log.info("生成验证码  " + code);
            // 3.发送邮件
            new SendMailToSomeone().send(MailUtil.getName(),
                "激活码",
                code,
                eMail,
                MailUtil.getFromEmail(),
                MailUtil.getPassword(),
                MailUtil.getMailType());
            return R.ok().put("msg", code);
        }
        catch (Exception e) {
            return R.fail();
        }
    }
    
    /**
     * 注册用户
     * 
     * @param dto 用户dto
     * @return 添加结果
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    @RequestMapping("/regist")
    @ResponseBody
    public R regist(UserDto dto) {
        if (null == dto) {
            return R.fail().put("msg", "注册失败");
        }
        
        boolean checked = itUserService.add(dto);
        // 入邮箱记录表
        itEmailCodeService.add(dto.geteMail(), dto.geteCode());
        if (!checked) {
            return R.fail().put("msg", "注册失败");
        }
        return R.ok();
    }
    
    /**
     * 忘记密码
     * 
     * @param eMail 邮箱地址
     * @param eCode 验证码
     * @param userPwd 新密码
     * @return 结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("forget")
    @ResponseBody
    public R forget(@RequestParam("eMail") String eMail, @RequestParam("eCode") String eCode,
        @RequestParam("userPwd") String userPwd) {
        if (StringUtils.isEmpty(eMail) || StringUtils.isEmpty(eCode) || StringUtils.isEmpty(userPwd)) {
            return R.fail().put("msg", "参数不能为空!");
        }
        
        try {
            // 1.email是否注册过
            boolean checked = itEmailCodeService.checkEmail(eMail);
            if (!checked) {
                return R.fail().put("msg", "该email未被注册，用户不存在,修改失败!");
            }
            
            // 2.发送验证码(目前采用简单策略，直接为最后一次的验证码)
            TEmailCode emailCode = itEmailCodeService.getOneByEmail(eMail);
            if (null == emailCode || !emailCode.getEmailCode().equals(eCode)) {
                return R.fail().put("msg", "验证码不正确!");
            }
            
            // 3.更新密码
            TUser user = itUserService
                .selectOne(new EntityWrapper<TUser>().eq("e_mail", eMail).eq("delete_flag", Enable.YES.getValue()));
            user.setUserPwd(new Md5Hash(userPwd, Constants.SALT).toString());
            user.setGmtModified(new Date());
            itUserService.updateInfoById(user);
            return R.ok().put("msg", "修改成功!");
        }
        catch (Exception e) {
            return R.fail().put("msg", "服务器发生异常!");
        }
    }
    
    /**
     * 退出登录
     * 
     * @return ModelAndView
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/logout")
    public ModelAndView exit() {
        try {
            SecurityUtils.getSubject().logout();
            return new ModelAndView("other/404");
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("other/500");
        }
    }
    
}
