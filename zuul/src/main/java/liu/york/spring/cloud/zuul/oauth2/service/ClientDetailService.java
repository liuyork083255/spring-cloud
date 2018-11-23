package liu.york.spring.cloud.zuul.oauth2.service;

import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ClientDetailService implements ClientDetailsService {

    private ClientDetailsService clientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(clientId);
    }


    @PostConstruct
    public void init() {
        String password = "123456";

        InMemoryClientDetailsServiceBuilder inMemoryClientDetailsServiceBuilder = new InMemoryClientDetailsServiceBuilder();
        inMemoryClientDetailsServiceBuilder.
                /* 客户端名称 */
                withClient("client")
                /* client密码 */
                .secret(password)
                /*
                 *每一个Resource Server（一个微服务实例）设置一个resourceId。再给client授权的时候，
                 * 可以设置这个client可以访问哪一些微服务实例，如果没设置，就是对所有的 resource 都有访问权限
                 */
                .resourceIds("id1")
                /*
                 * 认证类型
                 * 如果配置了password模式，那么必须指定一个 authenticationManager
                 * eg:endpoints.authenticationManager(authenticationManager);
                 */
                .authorizedGrantTypes("password","authorization_code", "implicit", "refresh_token")
                /* 角色列表 理解成 rbac 中的角色 */
                .authorities("ROLE_ADMIN")
                /*
                 * 权限scope 理解成 rbac 中的权限
                 * 再请求的时候如果不指定scope，那么默认就是获取全部的配置的权限
                 */
                .scopes("read", "write")
                .autoApprove(true);
        try {
            clientDetailsService = inMemoryClientDetailsServiceBuilder.build();
        } catch (Exception e) {
            System.out.println("初始化客户端信息异常");
        }
    }
}