package cn.slycmiaoxi.controller;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageListUtil;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.service.ITUserService;
import cn.slycmiaoxi.utils.CodecUtils;
import cn.slycmiaoxi.utils.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
@Controller
@RequestMapping("/core/v1/tUser")
@Slf4j
public class TAdminController {
    @Autowired
    private ITUserService itUserService;
    
    /**
     * 返回后台管理员首页面
     * 
     * @param currentPage 当前页
     * @param mv ModelAndView对象
     * @return 后台管理员首页面
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    @RequestMapping("/show/{currentPage}")
    public ModelAndView showVisitInfoView(@PathVariable int currentPage, ModelAndView mv) {
        Page page = new Page(currentPage, Constants.DEFAULT_EVERY_PAGE);
        PageModal pg = itUserService.queryListbyPage(page, new HashMap<>());
        long total = itUserService.queryTotal();
        new PageListUtil().pageModelList(total, mv, pg);
        mv.setViewName("sys/sysAdmin");
        return mv;
    }
    
    /**
     * 删除用户
     *
     * @param userId 用户主键
     * @return 结果
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestParam("userId") String userId) {
        if (StringUtils.isEmpty(userId)) {
            return R.fail();
        }
        
        try {
            itUserService.deleteById(Integer.parseInt(CodecUtils.decodeData(userId)));
        }
        catch (Exception e) {
            log.error("delete fail");
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 拉黑用户
     *
     * @param userId 用户主键
     * @param userState 用户状态
     * @return 结果
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    @RequestMapping("/defriend")
    @ResponseBody
    public R defriend(@RequestParam("userId") String userId, @RequestParam("userState") Integer userState) {
        if (StringUtils.isEmpty(userId) || null == userState) {
            return R.fail().put("msg", "更新失败");
        }
        
        try {
            TUser user = new TUser();
            user.setUserId(Integer.parseInt(CodecUtils.decodeData(userId)));
            user.setUserState(userState);
            itUserService.updateInfoById(user);
        }
        catch (NumberFormatException e) {
            return R.fail().put("msg", "更新失败");
        }
        return R.ok().put("msg", "更新成功");
    }
    
    /**
     * 修改用户个人信息
     *
     * @param userId 用户主键
     * @param userRealName 用户真实姓名
     * @param userPersonality 个性签名
     * @return 结果
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    @RequestMapping("/edit")
    @ResponseBody
    public R edit(@RequestParam("userId") String userId, @RequestParam("userRealName") String userRealName,
        @RequestParam("userPersonality") String userPersonality) {
        if (StringUtils.isEmpty(userId)) {
            return R.fail().put("msg", "更新失败");
        }
        
        try {
            TUser user = new TUser();
            user.setUserId(Integer.parseInt(CodecUtils.decodeData(userId)));
            user.setUserPersonality(userPersonality);
            user.setUserRealName(userRealName);
            itUserService.updateInfoById(user);
        }
        catch (NumberFormatException e) {
            return R.fail().put("msg", "更新失败");
        }
        return R.ok().put("msg", "更新成功");
    }
}
