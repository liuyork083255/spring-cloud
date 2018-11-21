package liu.york.client.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientServer2Application {

	public static void main(String[] args) {
		SpringApplication.run(ClientServer2Application.class, args);
	}
}
