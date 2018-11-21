package liu.york.client.two.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {

    @RequestMapping("/call")
    public String call(){
        System.out.println("我是客户端2");
        return "2";
    }

}