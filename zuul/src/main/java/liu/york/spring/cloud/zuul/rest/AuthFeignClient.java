package liu.york.spring.cloud.zuul.rest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AUTH-SERVER", path = "/oauth", fallback = AuthFeignClientImpl.class)
public interface AuthFeignClient {

    @RequestMapping("/check_token")
    Object checkToken(@RequestParam("token") String token);

}