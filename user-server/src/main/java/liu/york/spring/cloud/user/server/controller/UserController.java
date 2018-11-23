package liu.york.spring.cloud.user.server.controller;

import liu.york.spring.cloud.model.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/query")
    public List<User> query(){

        User u1 = new User();
        u1.setUsername("张三");u1.setPassword("123456");

        User u2 = new User();
        u2.setUsername("李四");u2.setPassword("654321");

        return Arrays.asList(u1, u2);
    }

    @GetMapping("/admin/query")
    public String res1(){
        System.out.println("admin");
        return "admin";
    }

    @GetMapping("/user/query")
    public String res2(){
        System.out.println("user");
        return "user";
    }
}