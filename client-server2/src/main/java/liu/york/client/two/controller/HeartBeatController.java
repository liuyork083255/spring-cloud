package liu.york.client.two.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/health")
    public void health(){
//        System.out.println("client-2 心跳检测...");
    }
}