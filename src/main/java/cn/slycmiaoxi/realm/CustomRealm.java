package cn.slycmiaoxi.realm;

import java.util.HashSet;
import java.util.Set;

import cn.slycmiaoxi.common.Constants;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.slycmiaoxi.entity.TRole;
import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.service.ITRoleService;
import cn.slycmiaoxi.service.ITUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: slycmiaoxi
 * @Date: 2019/6/23
 * @Description: 自定义Realm
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private ITUserService itUserService;
    
    @Autowired
    private ITRoleService itRoleService;
    
    /**
     * realm授权方法 从输入参数principalCollection得到身份信息 根据身份信息到数据库查找权限信息 将权限信息添加给授权信息对象 返回 授权信息对象(判断用户访问url是否在权限信息中没有体现)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String nickName = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
        // 1.分配角色
        Set<TRole> roles = itRoleService.findRolesByNickName(nickName);
        Set<String> roleSet = new HashSet<>();
        roles.stream().filter(x -> x != null).forEach(x -> {
            roleSet.add(x.getRoleName());
        });
        
        // 2.初始化
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(new HashSet<>());
        return authorizationInfo;
    }
    
    /**
     * 表单认证过滤器认证时会调用自定义Realm的认证方法进行认证，成功回到index.do，再跳转到index.jsp页面
     *
     * 前提：表单认证过滤器收集和组织用户名和密码信息封装为token对象传递给此方法
     *
     * token:封装了身份信息和凭证信息 2步骤：比对身份 信息；比对凭证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
        throws AuthenticationException {
        String nickName = (String)token.getPrincipal();
        String pwd = new String((char[])token.getCredentials());
        
        TUser user = itUserService.getUserByName(nickName);
        if (null == user) {
            throw new UnknownAccountException("账号不存在!");
        }
        
        pwd = new Md5Hash(pwd, Constants.SALT).toString();
        if (!pwd.equals(user.getUserPwd())) {
            throw new IncorrectCredentialsException("账号或密码不正确!");
        }
        
        return new SimpleAuthenticationInfo(nickName, pwd, ByteSource.Util.bytes(Constants.SALT), getName());
    }
}
