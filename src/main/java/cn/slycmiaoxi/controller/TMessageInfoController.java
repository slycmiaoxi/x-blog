package cn.slycmiaoxi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.slycmiaoxi.object.ResultMap;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import cn.slycmiaoxi.entity.TMessageInfo;
import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.service.ITMessageInfoService;
import cn.slycmiaoxi.service.ITUserService;
import cn.slycmiaoxi.utils.CodecUtils;
import cn.slycmiaoxi.utils.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 留言前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-04
 */
@Controller
@RequestMapping("/core/v1/tMessageInfo")
@Slf4j
public class TMessageInfoController {
    
    @Autowired
    private ITMessageInfoService itMessageInfoService;
    
    @Autowired
    private ITUserService itUserService;
    
    /**
     * 留言版首页
     * 
     * @return 留言版首页
     * @author slycmiaoxi
     * @since 2019-07-04
     */
    @RequestMapping("/show")
    public ModelAndView show() {
        return new ModelAndView("algotithms/message");
    }
    
    /**
     * 初始化留言（因为前端比较弱，分页目前无法实现，以加载指定数暂代)
     *
     * @param currentPage 当前页
     * @return 所有留言数据
     * @author slycmiaoxi
     * @since 2019-07-04
     */
    @RequestMapping("load/{currentPage}")
    @ResponseBody
    public R load(@PathVariable int currentPage) {
        PageModal pg;
        try {
            Page page = new Page(currentPage, 100);
            pg = itMessageInfoService.queryListbyPage(page, new HashMap<>());
            if (null == pg) {
                return R.fail();
            }
        }
        catch (Exception e) {
            log.error("message load error !", e);
            return R.fail();
        }
        return R.ok().put("obj", pg);
    }
    
    /**
     * 添加顶层评论
     *
     * @param parentNode 父节点id
     * @param entryUserId 当前用户id
     * @param messageContent 评论内容
     * @param request 域对象
     * @return 添加结果
     * @author slycmiaoxi
     * @since 2019-07-04
     */
    @RequestMapping(value = "addTop", method = RequestMethod.POST)
    @ResponseBody
    public R addTopUserMessage(@RequestParam("parentNode") long parentNode, @RequestParam("userId") String entryUserId,
        @RequestParam("messageContent") String messageContent, HttpServletRequest request) {
        if (StringUtils.isEmpty(entryUserId) || StringUtils.isBlank(messageContent)) {
            return R.fail();
        }
        
        try {
            TMessageInfo po = gerneratorMessage(parentNode,
                Integer.parseInt(entryUserId),
                messageContent,
                request);
            itMessageInfoService.insert(po);
        }
        catch (Exception e) {
            log.error("message add error !", e);
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 添加楼中楼回复评论
     *
     * @param replyName 楼中楼回复者姓名
     * @param parentNode 父节点id
     * @param messageContent 评论内容
     * @param request 域对象
     * @return 添加结果
     * @author slycmiaoxi
     * @since 2019-07-04
     */
    @RequestMapping(value = "addReply", method = RequestMethod.POST)
    @ResponseBody
    public R addTopUserMessage(@RequestParam("replyName") String replyName, @RequestParam("parentNode") long parentNode,
        @RequestParam("messageContent") String messageContent, HttpServletRequest request) {
        if (StringUtils.isEmpty(replyName) || StringUtils.isEmpty(messageContent)) {
            return R.fail();
        }
        try {
            TUser user = itUserService.getUserByName(replyName);
            if (null == user) {
                return R.fail();
            }
            int userId = user.getUserId();
            
            TMessageInfo po = gerneratorMessage(parentNode, userId, messageContent, request);
            itMessageInfoService.insert(po);
        }
        catch (Exception e) {
            log.error("message add reply error !", e);
            return R.fail();
        }
        return R.ok();
    }

    /**
     * 删除留言
     *
     * @param messageId 留言主键
     * @return 删除结果
     * @author slycmiaoxi
     * @since 2019-07-06
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public R delete( @RequestBody String messageid) {
        try {
            String[] messageId = messageid.split("=");
            itMessageInfoService.delete(new ResultMap("messageId", messageId[1]).toMap());
        }
        catch (NumberFormatException e) {
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 生成po实体
     * 
     * @param parentNode 父节点id
     * @param userId 当前用户id
     * @param messageContent 评论内容
     * @param request 域对象
     * @return po
     * @author slycmiaoxi
     * @since 2019-07-04
     */
    private TMessageInfo gerneratorMessage(long parentNode, Integer userId, String messageContent,
        HttpServletRequest request) {
        TMessageInfo po = new TMessageInfo();
        String ipAddress = request.getRemoteAddr();
        po.setIpAddress(ipAddress);
        po.setUserId(userId);
        po.setParentNode(parentNode);
        po.setMessageContent(messageContent);
        po.setGmtCreate(new Date());
        po.setDeleteFlag(Enable.YES.getValue());
        return po;
    }
    
}
