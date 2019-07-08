package cn.slycmiaoxi.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.slycmiaoxi.entity.TCritiquerecordInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.dto.ArticleDto;
import cn.slycmiaoxi.dto.FloorDto;
import cn.slycmiaoxi.entity.TBlogInfo;
import cn.slycmiaoxi.entity.TCommentInfo;
import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.object.ResultMap;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageListUtil;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.service.ITBlogInfoService;
import cn.slycmiaoxi.service.ITCommentInfoService;
import cn.slycmiaoxi.service.ITCritiquerecordInfoService;
import cn.slycmiaoxi.service.ITFloorInfoService;
import cn.slycmiaoxi.utils.CodecUtils;
import cn.slycmiaoxi.utils.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 评论前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
@Controller
@RequestMapping("/core/v1/tCommentInfo")
@Slf4j
public class TCommentInfoController {
    @Autowired
    private ITCommentInfoService itCommentInfoService;
    
    @Autowired
    private ITBlogInfoService itBlogInfoService;
    
    @Autowired
    private ITFloorInfoService itFloorInfoService;
    
    @Autowired
    private ITCritiquerecordInfoService itCritiquerecordInfoService;
    
    /**
     * 返回帖子内容页
     *
     * @param currentPage 当前页
     * @param encodeBlogId 帖子id
     * @param mv ModelAndView对象
     * @param request 域对象
     * @return 帖子内容页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("show/{currentPage}/{encodeBlogId}")
    public ModelAndView show(@PathVariable int currentPage, @PathVariable String encodeBlogId, ModelAndView mv,
        HttpServletRequest request) {
        long blogId = Long.parseLong(CodecUtils.decodeData(encodeBlogId));
        // 1.该篇文章数据
        ArticleDto articleDto = itBlogInfoService.getDtoById(blogId);
        
        // 2.楼中楼数据
        List<FloorDto> floorDtos =
            (List<FloorDto>)itFloorInfoService.queryListbyPage(new Page(), new ResultMap("blogId", blogId).toMap())
                .getList();
        
        // 3.访问量统计
        boolean checked = itCritiquerecordInfoService.checkRepeat(blogId, request.getRemoteAddr());
        if (!checked) {
            TCritiquerecordInfo po = new TCritiquerecordInfo();
            po.setBlogId(blogId);
            po.setIpAddress(request.getRemoteAddr());
            po.setGmtCreate(new Date());
            po.setDeleteFlag(Enable.YES.getValue());
            itCritiquerecordInfoService.insert(po);
            TBlogInfo blogInfo = itBlogInfoService.selectById(blogId);
            int hasReadCount = blogInfo.getVisitorHasread();
            blogInfo.setVisitorHasread(++hasReadCount);
            itBlogInfoService.updateById(blogInfo);
        }
        
        // 4.评论数据
        Page page = new Page(currentPage, Constants.DEFAULT_EVERY_PAGE);
        PageModal pg = itCommentInfoService.queryListbyPage(page, new ResultMap("blogId", blogId).toMap());
        long total = itCommentInfoService.queryTotal(new ResultMap("blogId", blogId).toMap());
        new PageListUtil().pageModelList(total, mv, pg);
        mv.addObject("blogInfo", articleDto);
        mv.addObject("Floor", floorDtos);
        
        mv.setViewName("article/articleContent");
        return mv;
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
    @RequestMapping("sysCommentShow/{currentPage}")
    public ModelAndView sysCommentShow(@PathVariable int currentPage, ModelAndView mv) {
        Page page = new Page(currentPage, Constants.DEFAULT_EVERY_PAGE);
        PageModal pg = itCommentInfoService.queryListbyPage(page, new HashMap<>());
        long total = itCommentInfoService.queryTotal(null);
        new PageListUtil().pageModelList(total, mv, pg);
        mv.setViewName("sys/sysCommentInfo");
        return mv;
    }
    
    /**
     * 返回搜索结果
     *
     * @param blogItem 帖子索引
     * @param response HttpServletResponse对象
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("showEsSearch/{blogItem}")
    public void esItemToBlogId(@PathVariable String blogItem, HttpServletResponse response)
        throws IOException {
        TBlogInfo po = (TBlogInfo)itBlogInfoService.selectObj(
            new EntityWrapper<TBlogInfo>().eq("blog_item", blogItem).eq("delete_flag", Enable.YES.getValue()));
        long blogId = Long.parseLong(CodecUtils.encodeData(String.valueOf(po.getBlogId())));
        String root = Constants.BLOG_SEARCH_URL + blogId;
        response.sendRedirect(root);
    }
    
    /**
     * 删除帖子
     *
     * @param commentId 帖子主键
     * @return 删除结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public R delete(@RequestParam("commentId") long commentId) {
        try {
            itCommentInfoService.delete(new ResultMap("commentId", commentId).toMap());
        }
        catch (NumberFormatException e) {
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 添加评论
     *
     * @param blogId 帖子主键
     * @param blogComment 帖子评论
     * @param userId 评论人用户id
     * @return 添加结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public R saveComment(@RequestParam("blogId") String blogId, @RequestParam("blogComment") String blogComment,
        @RequestParam("userId") String userId) {
        if (StringUtils.isEmpty(blogId) || StringUtils.isEmpty(blogComment) || StringUtils.isEmpty(userId)) {
            return R.fail();
        }
        
        // 1.会话失效不能添加
        TUser user = (TUser)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return R.fail();
        }
        
        try {
            // 2 .添加帖子
            TCommentInfo commentInfo =
                generatorCommentInfo(blogId, blogComment, user.getUserId(), user.getUserHeadimg());
            itCommentInfoService.insert(commentInfo);
        }
        catch (Exception e) {
            log.error("add blogInfo error !");
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 生成po实体对象
     *
     * @param blogId 帖子主键
     * @param blogComment 帖子评论
     * @param userId 评论人用户id
     * @param imgUrl 评论人头像地址
     * @return po
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    private TCommentInfo generatorCommentInfo(String blogId, String blogComment, Integer userId, String imgUrl) {
        TCommentInfo commentInfo = new TCommentInfo();
        commentInfo.setBlogId(Long.parseLong(CodecUtils.decodeData(blogId)));
        commentInfo.setUserId(userId);
        commentInfo.setImgUrl(imgUrl);
        commentInfo.setBlogComment(blogComment);
        commentInfo.setDeleteFlag(Enable.YES.getValue());
        commentInfo.setGmtCreate(new Date());
        return commentInfo;
    }
    
}
