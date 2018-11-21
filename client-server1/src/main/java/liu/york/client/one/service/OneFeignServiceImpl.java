package liu.york.client.one.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class OneFeignServiceImpl implements OneFeignClient {
    @Override
    public String call() {
        System.out.println("client-1：熔断采用默认处理");
        return "1";
    }
}