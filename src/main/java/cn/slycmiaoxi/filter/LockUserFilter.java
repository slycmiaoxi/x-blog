package cn.slycmiaoxi.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.service.ITUserService;

/**
 * <p>
 * 过滤黑名单用户
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-27
 */
public class LockUserFilter extends AccessControlFilter {
    @Autowired
    private ITUserService itUserService;
    
    private String loginUrl = "/500.jsp";
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
        throws Exception {
        String nickName = request.getParameter("nickName");
        TUser user = itUserService.getUserByName(nickName);
        if (null == user || user.getUserState() == 0) {
            return false;
        }
        return true;
    }
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
        throws Exception {
        WebUtils.issueRedirect(request, response, loginUrl);
        return false;
    }
}
