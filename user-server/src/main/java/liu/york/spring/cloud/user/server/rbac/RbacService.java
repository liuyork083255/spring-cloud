package liu.york.spring.cloud.user.server.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

@Component("rbacService")
public class RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {

            UserDetails userDetails = (UserDetails) principal;
            /* 按理说 GrantedAuthority 应该是保存权限获取角色之类的数据，这里是利用字符串直接保存资源 */
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                if (antPathMatcher.match(authority.getAuthority(), request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }

        return hasPermission;
    }
}