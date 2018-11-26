package liu.york.spring.cloud.auth.server.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static liu.york.spring.cloud.auth.server.constants.AuthConstants.*;

@Component
public class UserDetailService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = "123456";

        switch (username){
            case ROLE_ADMIN :
                return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(ADMIN_ROLES));
            case ROLE_USER :
                return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(USER_ROLES));
            default:
                throw new UsernameNotFoundException(username + " 用户不存在 !");
        }
    }
}