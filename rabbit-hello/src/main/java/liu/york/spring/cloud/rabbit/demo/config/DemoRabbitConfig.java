package liu.york.spring.cloud.rabbit.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoRabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

}