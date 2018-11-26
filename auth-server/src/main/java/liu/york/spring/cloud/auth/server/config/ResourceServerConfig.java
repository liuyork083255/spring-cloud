package liu.york.spring.cloud.auth.server.config;

import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                /* 对于异常处理 */
                .exceptionHandling()
                /* Http401AuthenticationEntryPoint 表示如果出现401异常，那么往响应头中添加下面的字段
                 * 其实还可以自定义类来处理，比如往响应体中存放内容等等
                 *      比如实现：AuthenticationEntryPoint 或者 实现AccessDeniedHandler可以控制权限不足的处理
                 * e.g. 处理ajax
                 *      判断是否ajax请求，是ajax请求则返回json，否则跳转失败页面
                 *      比如在 自定义类 UnauthorizedEntryPoint implements AuthenticationEntryPoint 中：
                        if(isAjaxRequest(request)){
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
                        }else{
                            response.sendRedirect("/login");
                        }

                        public static boolean isAjaxRequest(HttpServletRequest request) {
                            String ajaxFlag = request.getHeader("X-Requested-With");
                            return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
                        }
                 *
                 */
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\""))
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}