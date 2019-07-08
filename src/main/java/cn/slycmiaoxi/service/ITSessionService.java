package cn.slycmiaoxi.service;

import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;

/**
 * <p>
 * 在线人数服务类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-23
 */
public interface ITSessionService {
    
    /**
     * 在线人数
     * 
     * @return 在线人数
     * @author slycmiaoxi
     * @since 2019-06-23
     */
    int countActivesSession();
    
    /**
     * 统计所有在线会话
     * 
     * @return 所有在线会话
     * @author slycmiaoxi
     * @since 2019-06-23
     */
    Collection<Session> listActivesSession();
    
    /**
     * 获得 SessionDAO
     * 
     * @return SessionDAO
     * @author slycmiaoxi
     * @since 2019-06-23
     */
    SessionDAO getSessionDAO();
}
