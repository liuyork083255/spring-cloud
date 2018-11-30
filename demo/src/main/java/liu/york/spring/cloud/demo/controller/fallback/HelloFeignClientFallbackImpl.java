package liu.york.spring.cloud.demo.controller.fallback;

import liu.york.spring.cloud.feign.api.hello.HelloFeignClientFallback;
import org.springframework.stereotype.Component;

@Component
public class HelloFeignClientFallbackImpl implements HelloFeignClientFallback {
    @Override
    public void hello() {
        System.out.println("熔断处理");
    }
}