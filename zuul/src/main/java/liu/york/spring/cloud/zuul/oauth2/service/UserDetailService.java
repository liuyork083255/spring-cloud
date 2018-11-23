package liu.york.spring.cloud.zuul.oauth2.service;

import liu.york.spring.cloud.model.resource.Resource;
import liu.york.spring.cloud.zuul.oauth2.simulator.RoleManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import static liu.york.spring.cloud.model.resource.Resource.*;

@Component
public class UserDetailService implements UserDetailsService{



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = "123456";

        switch (username){
            case RoleManager.ROLE_ADMIN :
                return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(ADMIN));
            case RoleManager.ROLE_USER :
                return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(USER));
            default:
                return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(ALL));
        }
    }
}