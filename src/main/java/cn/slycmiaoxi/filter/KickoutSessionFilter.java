package cn.slycmiaoxi.filter;

import java.io.Serializable;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.slycmiaoxi.common.Constants;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>
 * 并发登陆人数控制
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-27
 */
public class KickoutSessionFilter extends AccessControlFilter {
    private Logger logger = (Logger)LoggerFactory.getLogger(this.getClass().getName());
    
    /**
     * 踢出后到的地址
     */
    private String kickoutUrl;
    
    /**
     * 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
     */
    private boolean kickoutAfter = false;
    
    /**
     * 同一个帐号最大会话数 默认1
     */
    private int maxSession;
    
    private SessionManager sessionManager;
    
    private Cache<String, Deque<Serializable>> cache;
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
        throws Exception {
        return false;
    }
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
        throws Exception {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String nickName = (String)subject.getPrincipal();
        Serializable sessionId = session.getId();
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            // 如果没有登录，直接进行之后的流程
            return true;
        }
        
        synchronized (this.cache) {
            Deque<Serializable> deque = this.cache.get(nickName);
            if (deque == null) {
                deque = new ConcurrentLinkedDeque<Serializable>();
            }
            
            if (!deque.contains(sessionId) && session.getAttribute("kickOut") == null) {
                session.setAttribute("nickName", nickName);
                deque.addLast(sessionId);
            }
            this.logger.debug("deque = " + deque);
            if (deque.size() > this.maxSession) {
                if (!this.kickoutAfter) {
                    sessionId = deque.removeFirst();
                }
                else {
                    sessionId = deque.removeLast();
                }
                try {
                    Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(sessionId));
                    if (kickoutSession != null) {
                        kickoutSession.stop();
                    }
                }
                catch (Exception e) {// ignore exception
                }
            }
            
            this.cache.put(nickName, deque);
        }
        // 如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
            // 会话被踢出了
            try {
                subject.logout();
            }
            catch (Exception e) { // ignore
            }
            saveRequest(request);
            WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
        }
        return true;
    }
    
    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }
    
    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }
    
    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }
    
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    
    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache(Constants.LOGIN_SESSION);
    }
}