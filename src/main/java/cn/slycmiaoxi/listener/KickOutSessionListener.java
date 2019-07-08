package cn.slycmiaoxi.listener;

import java.io.Serializable;
import java.util.Deque;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class KickOutSessionListener implements SessionListener {
    private Logger logger = (Logger)LoggerFactory.getLogger(this.getClass().getName());
    
    private Cache<String, Deque<Serializable>> cache;
    
    @Autowired
    private SessionDAO sessionDAO;
    
    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }
    
    private void removeSessionFromCache(Session session) {
        String nickName = (String)session.getAttribute("nickName");
        if (nickName != null) {
            logger.info("remove session: " + session.getId() + " in deque of " + nickName + "@shiro-kickout-session");
            synchronized (this.cache) {
                
                Deque<Serializable> deque = this.cache.get(nickName);
                deque.remove(session.getId());
                sessionDAO.delete(session);
                
                this.cache.put(nickName, deque);
            }
        }
    }
    
    @Override
    public void onStop(Session session) {
        this.removeSessionFromCache(session);
    }
    
    @Override
    public void onExpiration(Session session) {
        this.removeSessionFromCache(session);
    }
    
    @Override
    public void onStart(Session session) {
    }
    
}
