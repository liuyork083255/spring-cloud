package liu.york.spring.cloud.feign.api.order;

import liu.york.spring.cloud.model.order.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 熔断默认处理
 */
@Component
public class OrderFeignClientImpl implements OrderFeignClient{

    @Override
    public List<Order> query() {
        System.out.println("查询 order 异常，导致熔断处理");
        return Collections.emptyList();
    }
}