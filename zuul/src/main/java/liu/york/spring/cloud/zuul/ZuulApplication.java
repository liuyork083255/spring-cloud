package liu.york.spring.cloud.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
public class ZuulApplication {

	@Autowired
	private RestTemplateBuilder builder;

	/**
	 * 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
 	 */
	@Bean
	public RestTemplate restTemplate() {
		return builder.build();
	}


	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
}
