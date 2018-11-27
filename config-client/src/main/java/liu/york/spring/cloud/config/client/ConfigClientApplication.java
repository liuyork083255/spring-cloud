package liu.york.spring.cloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注意点一：
 * 	maven-actuator 模块有个/refresh接口，可以刷新git的变化
 * 	但是要实现自动刷新配置还需要有别的配置
 * 	解决方案有两个：
 * 		1 git仓库有个 web hook 功能可以当git提交变化时就主动给配置主机发送 /refresh 实现动态刷新
 * 		2 利用 spring cloud bus 功能
 * 			note：方式1 其实很难维护，原因是 config-client 如果多了，那么git调用负担很大
 *
 * 注意点二：
 *	获取git动态信息，那么关于git的配置必须放在 bootstrap.properties | bootstrap.yml 文件里面
 *	为了不影响原来的配置变化，可以将git的配置全部放在	bootstrap.properties 文件中，
 *	原来的 properties.properties 可以完全不变
 *
 */
@SpringBootApplication
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}
