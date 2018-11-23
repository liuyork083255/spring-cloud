package liu.york.spring.cloud.user.server.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 资源服务器中其实可以只配置 {@link EnableResourceServer} 就可以，但是有点问题，就是在另一个授权服务器上设置
 * user 有 权限1     admin 有权限2
 * 资源服务器是可以校验token的，也可以拿到token里面的信息，但是就是不会校验权限，只要token有效，就直接放行
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .authorizeRequests()
                /*
                 * 由于资源服务器不和授权一起，所有下面的配置不会生效，只要有token就直接放行
                 * 所以这里采用表达式 来校验
                 */
//                .antMatchers("/res1/*").hasAuthority("admin")
//                .antMatchers("/res2/*").hasAuthority("user")
                .anyRequest()
//                .authenticated();
                .access("@rbacService.hasPermission(request,authentication)");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("id1");
        resources.expressionHandler(expressionHandler);
    }


    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;
    /**
     * 权限表达式使用自定的，必要覆盖原来的
     */
    @Primary
    @Bean
    public OAuth2WebSecurityExpressionHandler oauthWebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);

        return expressionHandler;
    }

}