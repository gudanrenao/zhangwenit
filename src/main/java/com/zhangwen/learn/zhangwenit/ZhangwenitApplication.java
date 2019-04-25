package com.zhangwen.learn.zhangwenit;

import com.zhangwen.learn.zhangwenit.api.merchant.dto.DemoDto;
import com.zhangwen.learn.zhangwenit.api.system.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAsync
@SpringBootApplication
@RestController
public class ZhangwenitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhangwenitApplication.class, args);
    }

    /**
     * 登录密码加密方法
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/test/{id}")
    public User user(@PathVariable Long id, @RequestParam(required = false) String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(id + String.valueOf(name));
        return user;
    }

    @PostMapping("/test")
    public Object user(@RequestBody DemoDto demoDto) {
        System.out.println(demoDto);
        return demoDto;
    }
}
