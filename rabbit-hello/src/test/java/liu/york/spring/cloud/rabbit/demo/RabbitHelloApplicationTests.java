package liu.york.spring.cloud.rabbit.demo;

import liu.york.spring.cloud.rabbit.demo.receiver.DemoSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitHelloApplicationTests {


	@Autowired
	private DemoSender sender;

	@Test
	public void contextLoads() throws InterruptedException {

		sender.send();

		TimeUnit.DAYS.sleep(1);

	}

}
