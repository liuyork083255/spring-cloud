package liu.york.spring.cloud.demo.controller;

import liu.york.spring.cloud.feign.api.order.OrderFeignClient;
import liu.york.spring.cloud.feign.api.user.UserFeignClient;
import liu.york.spring.cloud.model.order.Order;
import liu.york.spring.cloud.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @GetMapping("/query/users")
    public List<User> users(){
        return userFeignClient.query();
    }

    @GetMapping("/query/orders")
    public List<Order> orders(){
        return orderFeignClient.query();
    }
}