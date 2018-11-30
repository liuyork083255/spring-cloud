package liu.york.spring.cloud.feign.api.hello;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于测试熔断接口
 * 由于定义熔断接口要和api接口放在一起，这样的话如果当api抽取出来就会导致熔断实现类必须先写好，失去了一种抽象的概念
 * 所以这里的案例测试：
 *      熔断不指定一个特定的是实现类，而是指定一个接口，这个接口再继承api接口
 *      那么这样就将api的熔断类真正的抽象出来了
 *
 *      这里必须要写一个新的接口继承feign-api接口，如果熔断指定feign-api本身，就会导致死循环保存
 *
 * 测试下来熔断指定接口而不是实现类是OK的
 *
 */
@FeignClient(value = "ORDER-SERVER", path = "/order", fallback = HelloFeignClientFallback.class)
public interface HelloFeignClient {

    @RequestMapping("/hello")
    void hello();

}