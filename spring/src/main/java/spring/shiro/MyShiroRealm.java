package spring.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.model.UserModel;
import spring.service.IUserService;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    IUserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        UserModel userModel = userService.getUserByUsername(username);
        if (username==null){
            throw new AuthenticationException();
        }
        return new SimpleAuthenticationInfo(userModel.getId(), userModel.getPassword(), userModel.getUsername());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
