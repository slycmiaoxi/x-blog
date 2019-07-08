package cn.slycmiaoxi.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <p>
 * xss请求域对象
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-07-01
 */
public class XssRequest extends HttpServletRequestWrapper {
    
    public XssRequest(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getParameter(String name) {
        // 返回值之前 先进行过滤
        return XssShieldUtil.stripXss(super.getParameter(XssShieldUtil.stripXss(name)));
    }
    
    @Override
    public String[] getParameterValues(String name) {
        // 返回值之前 先进行过滤
        String[] values = super.getParameterValues(XssShieldUtil.stripXss(name));
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                values[i] = XssShieldUtil.stripXss(values[i]);
            }
        }
        return values;
    }
    
}
