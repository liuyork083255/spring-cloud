//package liu.york.spring.cloud.user.server.oauth2;
//
//import liu.york.spring.cloud.user.server.oauth2.service.ClientDetailService;
//import liu.york.spring.cloud.user.server.oauth2.service.UserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private UserDetailService userDetailService;
//
//    @Autowired
//    private ClientDetailService clientDetailService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Bean
//    RedisTokenStore redisTokenStore() {
//        return new RedisTokenStore(redisConnectionFactory);
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailService);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .authenticationManager(authenticationManager);
//
//
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()")
//                .allowFormAuthenticationForClients();
//    }
//
//    /**
//     * 注意，自定义TokenServices的时候，需要设置@Primary，否则报错
//     * 这里这样定义后不会生效，因为 AuthorizationServerEndpointsConfigurer 还是会拿默认的，很奇怪
//     * 所以需要手动在设置一下
//     * @return      token 生成器
//     */
////    @Primary
////    @Bean
////    public DefaultTokenServices defaultTokenServices() {
////        DefaultTokenServices tokenServices = new DefaultTokenServices();
////        tokenServices.setTokenStore(redisTokenStore());
////        tokenServices.setSupportRefreshToken(true);
////        tokenServices.setClientDetailsService(clientDetailService);
////        tokenServices.setAccessTokenValiditySeconds(60); // token有效期自定义设置，默认12小时
////        tokenServices.setRefreshTokenValiditySeconds(60);//默认30天，这里修改
////        return tokenServices;
////    }
//}