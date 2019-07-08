package cn.slycmiaoxi.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.fastdfs.FastDFSUtil;
import cn.slycmiaoxi.service.ITUserService;
import cn.slycmiaoxi.utils.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 用户个人前端控制器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-28
 */
@Controller
@RequestMapping("/core/v1/tClient")
@Slf4j
public class TClientController {
    @Autowired
    private ITUserService itUserService;
    
    /**
     * 上传头像
     *
     * @param request 域对象
     * @param model ModelMap对象
     * @return 上传结果
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/upload")
    @ResponseBody
    public R upload(HttpServletRequest request, ModelMap model) {
        
        TUser user = (TUser)SecurityUtils.getSubject().getSession().getAttribute("currentUser");
        if (null == user) {
            return R.fail();
        }
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        // 1.fastDfs
        String urls = FastDFSUtil.uploadFile(multipartRequest.getFile("files"));
        System.out.println(urls);
        // 2.local
        try {
            // String url = UploadUtil.uploadFile(multipartRequest.getFile("files"), multipartRequest);
            TUser po = new TUser();
            po.setUserId(user.getUserId());
            po.setGmtModified(new Date());
            po.setUserHeadimg(urls);
            itUserService.updateById(po);
            user.setUserHeadimg(urls);
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
        }
        catch (Exception e) {
            return R.fail();
        }
        return R.ok();
    }
    
    /**
     * 返回编辑头像信息页
     *
     * @return 编辑头像信息页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/editHeader")
    public ModelAndView showEditHeaderIndex() {
        return new ModelAndView("client/data/userHeadEdit");
    }
    
    /**
     * 返回首页信息页
     *
     * @return 首页信息页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/index")
    public ModelAndView showIndex() {
        return new ModelAndView("client/index");
    }
    
    /**
     * 返回右部信息页
     *
     * @return 右部信息页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/right")
    public ModelAndView showRightIndex() {
        return new ModelAndView("client/right");
    }
    
    /**
     * 返回左部信息页
     *
     * @return 左页信息页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/left")
    public ModelAndView showLeftIndex() {
        return new ModelAndView("client/left");
    }
    
    /**
     * 返回顶部信息页
     *
     * @return 顶部信息页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/top")
    public String showTopIndex() {
        return "client/top";
    }
    
    /**
     * 返回底部信息页
     *
     * @return 底部信息页
     * @author slycmiaoxi
     * @since 2019-06-28
     */
    @RequestMapping("/bottom")
    public ModelAndView showBottomIndex() {
        return new ModelAndView("client/bottom");
    }
}
