package liu.york.spring.cloud.auth.server.config;

import liu.york.spring.cloud.auth.server.service.ClientDetailService;
import liu.york.spring.cloud.auth.server.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private ClientDetailService clientDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(redisTokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager);

        /* 修改默认token获取地址 */
//        endpoints.pathMapping("/oauth/token","/oauth/token");

        /* 手动设置token service 因为下面注入后没有生效 */
        endpoints.tokenServices(defaultTokenServices());

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");

        /*
         * 这个如果配置支持allowFormAuthenticationForClients的，ClientCredentialsTokenEndpointFilter
         * 且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter来保护
         * 如果这里不设置，那么跟oauth相关的几个接口都会被保护起来
         * Note:
         *      这里如果不允许，那么访问 /oauth/token 是禁止的，但是在WebSecurityConfig 设置这个路径 permitAll()
         *      也没用，还在疑问中
         *
         */
        security.allowFormAuthenticationForClients();
    }

    /**
     * 注意，自定义TokenServices的时候，需要设置@Primary，否则报错
     * 这里这样定义后不会生效，因为 AuthorizationServerEndpointsConfigurer 还是会拿默认的，很奇怪
     * 所以需要手动在设置一下
     * @return      token 生成器
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailService);
        tokenServices.setAccessTokenValiditySeconds(60 * 60); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60);//默认30天，这里修改

        /* 设置token */
        tokenServices.setTokenEnhancer(new TokenEnhancerConfig());

        /* 这里如果有多个token加强链，那么可以使用这种方式 */
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        enhancerChain.setTokenEnhancers(Collections.singletonList(new YorkTokenEnhancer()));
//        tokenServices.setTokenEnhancer(enhancerChain);

        return tokenServices;
    }

}