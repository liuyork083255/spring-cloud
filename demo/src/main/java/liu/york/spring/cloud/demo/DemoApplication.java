package liu.york.spring.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 由于feign接口在依赖中，不在当前启动类路径下面，
 * 所以这里必须要指明 {@link FeignClient}  注解所在路径
 */
@EnableFeignClients(basePackages = "liu.york.spring.cloud.feign.api")
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {"liu.york.spring.cloud"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
