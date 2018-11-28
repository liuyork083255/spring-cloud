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
 * 		其实后面加入的bus功能，也不能真正的动态更新，因为提交代码到git-config上面，必然有一个动作触发通知服务，
 * 		解决方案就是 将 bus 依赖添加到config-server 和 config-client 服务，然后git一有提交，那么就调用config-server
 * 		服务的 /bus/refresh 端点，这样就可以通知一个配置中心服务，而不是一个一个通知客户端了
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
