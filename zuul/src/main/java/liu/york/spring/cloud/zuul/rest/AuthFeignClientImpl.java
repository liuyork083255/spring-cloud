package liu.york.spring.cloud.zuul.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class AuthFeignClientImpl implements AuthFeignClient {
    @Override
    public Object checkToken(@RequestParam("token") String token) {
        System.out.println("调用 认证服务 校验 token 失败");
        return null;
    }
}