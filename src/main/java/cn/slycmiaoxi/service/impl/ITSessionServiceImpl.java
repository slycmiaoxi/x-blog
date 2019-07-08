package cn.slycmiaoxi.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.slycmiaoxi.service.ITSessionService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 在线人数服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-23
 */
@Service
@Slf4j
public class ITSessionServiceImpl implements ITSessionService {
    @Autowired
    private SessionDAO sessionDAO;
    
    @Override
    public int countActivesSession() {
        return CollectionUtils.isEmpty(listActivesSession()) ? 0 : listActivesSession().size();
    }
    
    @Override
    public Collection<Session> listActivesSession() {
        // 1. 获得当前所有活跃的在线用户
        Collection<Session> currentSessionList = sessionDAO.getActiveSessions();
        
        // 2.仍然存在缓存中但已失效的的用户会话
        Collection<Session> invalidSessionList = new LinkedList<>();
        
        // 3.筛选出失效的会话
        Iterator<Session> it = currentSessionList.iterator();
        while (it.hasNext()) {
            try {
                Session session = it.next();
                PrincipalCollection principalCollection =
                    (PrincipalCollection)session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
                invalidSessionList.add(session);
            }
            catch (Exception e) {
            }
        }
        
        // 4.删除缓存活跃会话中失效的会话
        invalidSessionList.stream().filter(x -> x != null).forEach(x -> {
            if (currentSessionList.contains(x)) {
                sessionDAO.delete(x);
            }
        });
        
        // 5. 剩下正常的在线用户
        Collection<Session> normalSessionList = sessionDAO.getActiveSessions();
        return normalSessionList;
    }
    
    @Override
    public SessionDAO getSessionDAO() {
        return this.sessionDAO;
    }
}
