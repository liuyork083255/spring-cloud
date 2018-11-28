package liu.york.spring.cloud.rabbit.demo.sender;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class DemoReceiver {

    @RabbitHandler
    public void proces(String hello){
        System.out.println("get receive message : " + hello);
    }

}