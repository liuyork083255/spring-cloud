package liu.york.spring.cloud.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * {@link WebSecurityConfigurerAdapter} 是默认情况下 spring security的http配置
 * {@link ResourceServerConfigurerAdapter}是默认情况下 spring security oauth2的http配置
 *
 * 在 {@link ResourceServerProperties} 中，定义了它的order默认值为SecurityProperties.ACCESS_OVERRIDE_ORDER - 1;
 * 是大于100的,即WebSecurityConfigurerAdapter的配置的拦截要优先于ResourceServerConfigurerAdapter，
 * 优先级高的http配置是可以覆盖优先级低的配置的。
 * 某些情况下如果需要ResourceServerConfigurerAdapter的拦截优先于WebSecurityConfigurerAdapter需要在配置文件中添加
 *  security.oauth2.resource.filter-order=99
 *
 *
 *
 *
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/health").permitAll()
                .anyRequest().authenticated();
    }

}