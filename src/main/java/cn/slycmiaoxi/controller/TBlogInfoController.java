package cn.slycmiaoxi.controller;

import java.util.*;

import cn.slycmiaoxi.utils.JsoupUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.dto.ArticleDto;
import cn.slycmiaoxi.dto.SearchModelDto;
import cn.slycmiaoxi.elasticsearch.EsUtil;
import cn.slycmiaoxi.entity.TBlogInfo;
import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.enumerate.BlogStatusEnum;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.object.ResultMap;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageListUtil;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.pagehelper.Pages;
import cn.slycmiaoxi.service.ITBlogInfoService;
import cn.slycmiaoxi.utils.CodecUtils;
import cn.slycmiaoxi.utils.IDUtils;
import cn.slycmiaoxi.utils.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 帖子前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
@Controller
@RequestMapping("/core/v1/tBlogInfo")
@Slf4j
public class TBlogInfoController {
    @Autowired
    private ITBlogInfoService itBlogInfoService;
    
    /**
     * 返回发帖首页
     * 
     * @return 发帖首页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/blogWriteShow")
    public String blogWriteShow() {
        return "article/articleWrite";
    }
    
    /**
     * 返回论坛文章显示首页面
     *
     * @param currentPage 当前页
     * @param mv ModelAndView对象
     * @return 论坛文章显示首页面
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("blogAllShow/{currentPage}")
    public ModelAndView blogAllShow(@PathVariable int currentPage, ModelAndView mv) {
        mv.setViewName("article/articleList");
        
        try {
            // 1.加载所有帖子
            Page page = new Page(currentPage, Constants.DEFAULT_EVERY_PAGE);
            PageModal pg = itBlogInfoService.queryListbyPage(page, new HashMap<>());
            long total = itBlogInfoService.queryTotal();
            new PageListUtil().pageModelList(total, mv, pg);
            
            // 2.置顶帖
            List<Integer> stateList = new ArrayList<>();
            stateList.add(BlogStatusEnum.TOP.getCode());
            stateList.add(BlogStatusEnum.TOP_HOT.getCode());
            List<ArticleDto> topDtos = itBlogInfoService.listDtoByState(stateList);
            mv.addObject("topBlog", topDtos);
        }
        catch (Exception e) {
            log.error("error !", e);
            return mv;
        }
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
    @RequestMapping("sysBlogShow/{currentPage}")
    public ModelAndView sysBlogShow(@PathVariable int currentPage, ModelAndView mv) {
        mv.setViewName("sys/sysBlogInfo");
        try {
            Page page = new Page(currentPage, Constants.DEFAULT_EVERY_PAGE);
            PageModal pg = itBlogInfoService.queryListbyPage(page, new HashMap<>());
            long total = itBlogInfoService.queryTotal();
            new PageListUtil().pageModelList(total, mv, pg);
        }
        catch (Exception e) {
            log.error("error !", e);
            return mv;
        }
        return mv;
    }
    
    /**
     * 返回论坛用户的所有文章显示页面
     *
     * @param currentPage 当前页
     * @param userId 用户主键
     * @param mv ModelAndView对象
     * @return 论坛用户的所有文章显示页面
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("userBlogAllShow/{currentPage}/{userId}")
    public ModelAndView userBlogAllShow(@PathVariable int currentPage, @PathVariable String userId, ModelAndView mv) {
        mv.setViewName("article/articleUserList");
        try {
            Page page = new Page(currentPage, Constants.DEFAULT_EVERY_PAGE);
            PageModal pg =
                itBlogInfoService.queryListbyPage(page, new ResultMap("userId", CodecUtils.decodeData(userId)).toMap());
            long total = itBlogInfoService.queryTotal();
            new PageListUtil().pageModelList(total, mv, pg);
            mv.addObject("userId", userId);
        }
        catch (Exception e) {
            log.error("error !", e);
            return mv;
        }
        return mv;
    }
    
    /**
     * 返回帖子搜索页
     * 
     * @param key 关键字
     * @param mv ModelAndView对象
     * @return 帖子搜索页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/searchShow")
    public ModelAndView SearchArticleView(@RequestParam(defaultValue = "") String key, ModelAndView mv) {
        mv.setViewName("article/articleSearch");
        try {
            mv.addObject("key", key);
            List<SearchModelDto> search = listSearchModelDto(key);
            mv.addObject("recordCount", search.size());
        }
        catch (Exception e) {
            log.error("error !", e);
            return mv;
        }
        return mv;
    }
    
    /**
     * 文章检索
     *
     * @param key 关键字
     * @param recordCount 记录总数
     * @return 文章检索的dto json格式串
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public String SearchArticle(@RequestParam("key") String key, @RequestParam("recordCount") String recordCount) {
        List<SearchModelDto> search = listSearchModelDto(key);
        return JSON.toJSONString(search);
    }
    
    /**
     * 添加帖子
     *
     * @param blogTitle 帖子标题
     * @param blogLable 帖子标签
     * @param blogContent 帖子内容
     * @return 添加结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public R add(@RequestParam("blogTitle") String blogTitle, @RequestParam("blogLable") String blogLable,
        @RequestParam("blogContent") String blogContent) {
        if (StringUtils.isEmpty(blogTitle) || StringUtils.isEmpty(blogLable) || StringUtils.isEmpty(blogContent)) {
            return R.fail();
        }
        
        // 1.会话失效不能添加
        TUser user = (TUser)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        if (null == user) {
            return R.fail();
        }
        
        try {
            // 2 .添加帖子
            TBlogInfo blogInfo = generatorBlogInfo(blogTitle, blogLable, JsoupUtil.clean(blogContent), user.getUserId());
            itBlogInfoService.insert(blogInfo);
            
            // 3.添加到es库
            SearchModelDto search = new SearchModelDto();
            search.setBlogTitle(blogTitle);
            search.setBlogContent(blogContent);
            search.setGmtCreate(blogInfo.getGmtCreate());
            EsUtil.addDoc(Constants.BLOG_SEARCH, blogInfo.getBlogItem(), search);
        }
        catch (Exception e) {
            log.error("add blogInfo error !");
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 更新帖子状态
     *
     * @param eblogId 帖子主键
     * @param blogState 帖子状态
     * @return 更新结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping(value = "updateState", method = RequestMethod.POST)
    @ResponseBody
    public R updateState(@RequestParam("blogId") String eblogId, @RequestParam("blogState") Integer blogState) {
        if (StringUtils.isEmpty(eblogId) || null == blogState) {
            return R.fail();
        }
        
        long blogId = Long.parseLong(CodecUtils.decodeData(eblogId));
        // 校验该帖子是否存在
        TBlogInfo blogInfo = itBlogInfoService.selectById(blogId);
        if (null == blogInfo) {
            return R.fail();
        }
        blogInfo.setBlogState(blogState);
        blogInfo.setGmtModified(new Date());
        itBlogInfoService.updateById(blogInfo);
        return R.ok();
    }
    
    /**
     * 更新帖子信息
     *
     * @param eblogId 帖子主键
     * @param blogTitle 帖子主题
     * @param blogLable 帖子标签
     * @return 更新结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public R update(@RequestParam("blogId") String eblogId, @RequestParam("blogTitle") String blogTitle,
        @RequestParam("blogLable") String blogLable) {
        if (StringUtils.isEmpty(eblogId) || StringUtils.isEmpty(blogTitle) || StringUtils.isEmpty(blogLable)) {
            return R.fail();
        }
        long blogId = Long.parseLong(CodecUtils.decodeData(eblogId));
        // 校验该帖子是否存在
        TBlogInfo blogInfo = itBlogInfoService.selectById(blogId);
        if (null == blogInfo) {
            return R.fail();
        }
        blogInfo.setBlogTitle(blogTitle);
        blogInfo.setBlogLable(blogLable);
        blogInfo.setGmtModified(new Date());
        itBlogInfoService.updateById(blogInfo);
        return R.ok();
        
    }
    
    /**
     * 删除帖子
     *
     * @param eblogId 帖子主键
     * @return 删除结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public R delete(@RequestParam("blogId") String eblogId) {
        if (StringUtils.isEmpty(eblogId)) {
            return R.fail();
        }
        try {
            long blogId = Long.parseLong(CodecUtils.decodeData(eblogId));
            itBlogInfoService.delete(blogId);
        }
        catch (NumberFormatException e) {
            log.error("delete blogInfo error !");
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 生成po实体对象
     *
     * @param blogTitle 帖子标题
     * @param blogLable 帖子标签
     * @param blogContent 帖子内容
     * @param userId 用户主键
     * @return po实体
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    private TBlogInfo generatorBlogInfo(String blogTitle, String blogLable, String blogContent, Integer userId) {
        TBlogInfo blogInfo = new TBlogInfo();
        blogInfo.setBlogTitle(blogTitle);
        blogInfo.setBlogLable(blogLable);
        blogInfo.setBlogContent(blogContent);
        blogInfo.setUserId(userId);
        blogInfo.setBlogItem(String.valueOf(IDUtils.genItemId()));
        blogInfo.setBlogState(BlogStatusEnum.COMMON.getCode());
        blogInfo.setGmtCreate(new Date());
        blogInfo.setDeleteFlag(Enable.YES.getValue());
        return blogInfo;
    }
    
    /**
     * 获取文章检索后的dto集合
     * 
     * @param key 关键字
     * @return 文章检索后的dto集合
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    private List<SearchModelDto> listSearchModelDto(@RequestParam(defaultValue = "") String key) {
        HashSet<String> set = new HashSet<String>();
        set.add("blogTitle");
        set.add("blogContent");
        Pages page = EsUtil.getDocHighLight(key, Constants.BLOG_SEARCH, set, 1, 5, true);
        List<SearchModelDto> dtos = (List<SearchModelDto>)page.getRecordList();
        return dtos;
    }
    
}
