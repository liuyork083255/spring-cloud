//package liu.york.spring.cloud.user.server.oauth2.service;
//
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDetailService implements UserDetailsService{
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        switch (username){
//            case "admin" : return getUserDetailByUsername("admin");
//            case "user" : return getUserDetailByUsername("user");
//            default:return getUserDetailByUsername("guest");
//        }
//    }
//
//    private UserDetails getUserDetailByUsername(String username){
//        return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList(username));
//    }
//}