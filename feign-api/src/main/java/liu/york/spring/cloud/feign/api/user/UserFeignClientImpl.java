package liu.york.spring.cloud.feign.api.user;

import liu.york.spring.cloud.model.user.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 熔断默认处理
 */
@Component
public class UserFeignClientImpl implements UserFeignClient {

    @Override
    public List<User> query() {
        System.out.println("查询 user 异常，导致熔断处理");
        return Collections.emptyList();
    }
}