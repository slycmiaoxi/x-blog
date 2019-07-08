package cn.slycmiaoxi.controller;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.entity.TFloorInfo;
import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.object.ResultMap;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageListUtil;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.service.ITFloorInfoService;
import cn.slycmiaoxi.utils.CodecUtils;
import cn.slycmiaoxi.utils.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 楼中楼前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
@Controller
@RequestMapping("/core/v1/tFloorInfo")
@Slf4j
public class TFloorInfoController {
    @Autowired
    private ITFloorInfoService itFloorInfoService;
    
    /**
     * 添加楼中楼评论
     * 
     * @param blogId 帖子主键
     * @param floorComment 楼中楼评论
     * @param userId 用户主键
     * @param commentId 楼中楼所属评论主键
     * @return 添加结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public R saveComment(@RequestParam("blogId") String blogId, @RequestParam("floorComment") String floorComment,
        @RequestParam("userId") String userId, @RequestParam("commentId") long commentId) {
        if (StringUtils.isEmpty(blogId) || StringUtils.isEmpty(floorComment) || StringUtils.isEmpty(userId)) {
            return R.fail();
        }
        
        // 1.会话失效不能添加
        TUser user = (TUser)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        if (null == user || user.getUserId() != Integer.parseInt(userId)) {
            return R.fail();
        }
        
        try {
            // 2 .添加帖子
            TFloorInfo floorInfo =
                generatorFloorInfo(blogId, floorComment, commentId, user.getUserId(), user.getUserHeadimg());
            itFloorInfoService.insert(floorInfo);
        }
        catch (Exception e) {
            log.error("add blogInfo error !");
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 返回论坛文章后台显示首页面
     *
     * @param currentPage 当前页
     * @param mv ModelAndView对象
     * @return 论坛文章后台显示首页面
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("sysFloorShow/{currentPage}")
    public ModelAndView sysFloorShow(@PathVariable int currentPage, ModelAndView mv) {
        Page page = new Page(currentPage, Constants.DEFAULT_EVERY_PAGE);
        PageModal pg = itFloorInfoService.queryListbyPage(page, new HashMap<>());
        long total = itFloorInfoService.queryTotal();
        new PageListUtil().pageModelList(total, mv, pg);
        mv.setViewName("sys/sysFloorInfo");
        return mv;
    }
    
    /**
     * 删除帖子
     *
     * @param floorId 帖子主键
     * @return 删除结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public R delete(@RequestParam("floorId") long floorId) {
        try {
            itFloorInfoService.delete(new ResultMap("floorId", floorId).toMap());
        }
        catch (NumberFormatException e) {
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 生成po实体对象
     * 
     * @param blogId 帖子主键
     * @param floorComment 楼中楼评论
     * @param userId 用户主键
     * @param commentId 楼中楼所属评论主键
     * @param userHeadimg 头像地址
     * @return po
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    private TFloorInfo generatorFloorInfo(String blogId, String floorComment, long commentId, Integer userId,
        String userHeadimg) {
        TFloorInfo tFloorInfo = new TFloorInfo();
        tFloorInfo.setBlogId(Long.parseLong(CodecUtils.decodeData(blogId)));
        tFloorInfo.setCommentId(commentId);
        tFloorInfo.setUserId(userId);
        tFloorInfo.setFloorComment(floorComment);
        tFloorInfo.setImgUrl(userHeadimg);
        tFloorInfo.setGmtCreate(new Date());
        tFloorInfo.setDeleteFlag(Enable.YES.getValue());
        return tFloorInfo;
    }
}
