//package liu.york.client.two.service;
//
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@FeignClient(value = "client-server-2", path = "/feign", fallback = TwoFeignServiceImpl.class)
//public interface TwoFeignClient {
//
//    @RequestMapping("/call")
//    String call();
//
//}