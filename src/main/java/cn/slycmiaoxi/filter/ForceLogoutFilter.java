package cn.slycmiaoxi.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import cn.slycmiaoxi.common.Constants;

/**
 * <p>
 * 强制踢人
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-29
 */
public class ForceLogoutFilter extends AccessControlFilter {
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
        throws Exception {
        Session session = getSubject(request, response).getSession(false);
        if (session == null) {
            return true;
        }
        return session.getAttribute(Constants.SESSION_FORCE_LOGOUT_KEY) == null;
    }
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
        throws Exception {
        try {
            getSubject(request, response).logout();
        }
        catch (Exception e) {
            /* ignore exception */}
            
        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        WebUtils.issueRedirect(request, response, loginUrl);
        return false;
    }
}
