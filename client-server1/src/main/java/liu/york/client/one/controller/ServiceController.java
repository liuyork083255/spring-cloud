package liu.york.client.one.controller;

import liu.york.client.one.service.OneFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private OneFeignClient oneFeignClient;

    @RequestMapping("/name")
    public String getName(@RequestParam("name") String name){
        System.out.println("client-1 接收到业务请求...");

        return oneFeignClient.call();
    }

}