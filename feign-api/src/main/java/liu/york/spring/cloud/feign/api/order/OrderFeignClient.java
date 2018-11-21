package liu.york.spring.cloud.feign.api.order;

import liu.york.spring.cloud.model.order.Order;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "ORDER-SERVER", path = "/order", fallback = OrderFeignClientImpl.class)
public interface OrderFeignClient {

    @RequestMapping("/query")
    List<Order> query();

}