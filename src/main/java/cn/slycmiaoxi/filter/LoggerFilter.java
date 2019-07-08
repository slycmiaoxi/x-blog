package cn.slycmiaoxi.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import cn.slycmiaoxi.common.Const;
import cn.slycmiaoxi.common.Constants;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;

import cn.slycmiaoxi.entity.TLoginLog;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.service.ITLoginLogService;
import cn.slycmiaoxi.utils.AddressUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 登录日志统计
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-27
 */
@Slf4j
public class LoggerFilter implements Filter {
    
    private ITLoginLogService iTLoginLogService;
    
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException {
        ServletContext sc = filterConfig.getServletContext();
        XmlWebApplicationContext cxt =
            (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
        
        if (cxt != null && cxt.getBean("iTLoginLogService") != null && this.iTLoginLogService == null) {
            this.iTLoginLogService = (ITLoginLogService)cxt.getBean("iTLoginLogService");
        }
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        Date time_begin = new Date();
        chain.doFilter(request, response);
        Date time_end = new Date();
        // 成功登录
        if (request.getAttribute("flag") != null) {
            // 请求耗时
            long i = (long)((time_end.getTime() - time_begin.getTime()));
            TLoginLog tLoginLog = generatorLogger(httpRequest, i);
            iTLoginLogService.insert(tLoginLog);
        }
        else {
            log.error("login fail !");
        }
    }
    
    @Override
    public void destroy() {
        
    }
    
    /**
     * 生成登录日志记录对象
     * 
     * @param httpRequest 域对象
     * @param i 耗时时长
     * @return po
     * @author slycmiaoxi
     * @since 2019-06-26
     */
    private TLoginLog generatorLogger(HttpServletRequest httpRequest, long i)
        throws UnsupportedEncodingException {
        TLoginLog tLoginLog = new TLoginLog();
        tLoginLog.setLoggerName("登录系统");
        tLoginLog.setLoggerType("业务日志");
        tLoginLog.setRequestUrl(httpRequest.getServletPath());
        tLoginLog.setRequestType(httpRequest.getMethod());
        tLoginLog.setUserName(httpRequest.getParameter("nickName"));
        tLoginLog.setVisitorIp(httpRequest.getRemoteAddr());
        tLoginLog.setRequestTime(i);
        tLoginLog.setDeleteFlag(Enable.YES.getValue());
        // 获得ip对应的实际地址
        AddressUtils addressUtils = new AddressUtils();
        String json_result = addressUtils.getAddresses("ip=" + httpRequest.getRemoteAddr(), "utf-8");
        String ipAddress = "";
        if (!json_result.equals(String.valueOf(Const.ZERO)) && StringUtils.isNotEmpty(json_result)) {
            JSONObject json = new JSONObject(json_result);
            String region = JSON.toJSONString(((JSONObject)json.get("data")).get("region"));
            String city = JSON.toJSONString(((JSONObject)json.get("data")).get("city"));
            ipAddress = Joiner.on(" ").join(region.replace("\"", ""), city.replace("\"", ""));
        }
        
        tLoginLog.setIpAddress(ipAddress);
        tLoginLog.setGmtCreate(new Date());
        return tLoginLog;
    }
}
