package liu.york.spring.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 如果代码中需要动态刷新配置，在需要的类上加上该注解{@link RefreshScope}就行
 */
@RefreshScope
@RestController
public class TestController {

    @Value("${from}")
    private String from;

    @RequestMapping("/from")
    public String from(){

        if(from == null){
            System.out.println("NULL");
            return null;
        }
        System.out.println(from);
        return from;
    }

}