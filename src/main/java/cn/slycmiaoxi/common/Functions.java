package cn.slycmiaoxi.common;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 会话管理函数
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public class Functions {
    
    /**
     * 检验是否包含某个对象
     *
     * @param iterable 迭代器
     * @param element 待检验对象
     * @return true-包含 false-不包含
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    public static boolean in(Iterable iterable, Object element) {
        if (iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }
    
    /**
     * 根据会话获得principal对象
     *
     * @param session shiro管理的会话
     * @return principal对象
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    public static String principal(Session session) {
        PrincipalCollection principalCollection =
            (PrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        return (String)principalCollection.getPrimaryPrincipal();
    }
    
    /**
     * 会话是否失效了
     *
     * @param session shiro管理的会话
     * @return true-活跃 false-失效
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    public static boolean isForceLogout(Session session) {
        return session.getAttribute(Constants.SESSION_FORCE_LOGOUT_KEY) != null;
    }
}
