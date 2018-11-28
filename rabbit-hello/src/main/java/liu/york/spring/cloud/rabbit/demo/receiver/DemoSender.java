package liu.york.spring.cloud.rabbit.demo.receiver;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){

        amqpTemplate.convertAndSend("hello", "hello rabbit!");

    }

}