package cn.slycmiaoxi.controller;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.service.ITSessionService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 在线人数控制类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-23
 */
@Controller
@RequestMapping("/core/v1/userOnline")
@Slf4j
public class UserOnlineController {
    @Autowired
    private ITSessionService itSessionService;
    
    /**
     * 返回在线人数首页面
     * 
     * @param model ModelAndView对象
     * @return 在线人数首页面
     * @author slycmiaoxi
     * @since 2019-06-23
     */
    @RequestMapping("/show")
    public ModelAndView list(ModelAndView model)
        throws Exception {
        model.addObject("activesSessionList", itSessionService.listActivesSession());
        model.addObject("activesSessionCount", itSessionService.countActivesSession());
        model.setViewName("sys/sessionList");
        return model;
    }
    
    /**
     * 强制踢人
     * 
     * @param sessionId 会话id
     * @param redirectAttributes RedirectAttributes对象
     * @return 在线人数首页面
     * @throws Exception
     */
    @RequestMapping("/{sessionId}/forceLogout")
    public String forceLogout(@PathVariable("sessionId") String sessionId, RedirectAttributes redirectAttributes)
        throws Exception {
        try {
            Session session = itSessionService.getSessionDAO().readSession(sessionId);
            if (session != null) {
                session.setAttribute(Constants.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
            }
        }
        catch (Exception e) {
            /* ignore */}
        redirectAttributes.addFlashAttribute("msg", "强制退出成功！");
        return "redirect:/core/v1/userOnline/show";
    }
    
}
