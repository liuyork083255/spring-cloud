package liu.york.spring.cloud.feign.api.user;

import liu.york.spring.cloud.model.user.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "USER-SERVER", path = "/user", fallback = UserFeignClientImpl.class)
public interface UserFeignClient {

    @RequestMapping("/query")
    List<User> query();

}