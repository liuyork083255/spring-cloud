package liu.york.spring.cloud.order.server.controller;

import liu.york.spring.cloud.model.order.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/query")
    public List<Order> query(){
        Order o1 = new Order();
        o1.setName("衣服");o1.setPrice(10000);

        Order o2 = new Order();
        o2.setName("裤子");o2.setPrice(1000);

        return Arrays.asList(o1, o2);
    }
}