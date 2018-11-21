package liu.york.client.one.controller;

import com.alibaba.fastjson.JSON;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.PutParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {

    @Autowired
    private ConsulClient consulClient;



    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/health")
    public void health(){

//        System.out.println("心跳检测...");
    }

}