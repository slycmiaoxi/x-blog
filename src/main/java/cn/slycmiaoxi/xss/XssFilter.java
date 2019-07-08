package cn.slycmiaoxi.xss;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * xss拦截器
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-01
 */
public class XssFilter implements Filter {
    
    @Override
    public void destroy() {
        
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        request = new XssRequest(request);
        chain.doFilter(request, response);
    }
    
    @Override
    public void init(FilterConfig arg0)
        throws ServletException {
        
    }
    
}