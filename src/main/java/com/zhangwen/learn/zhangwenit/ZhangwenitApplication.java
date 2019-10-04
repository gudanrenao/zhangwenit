package com.zhangwen.learn.zhangwenit;

import com.zhangwen.learn.zhangwenit.api.merchant.dto.DemoDto;
import com.zhangwen.learn.zhangwenit.api.system.entity.User;
import com.zhangwen.learn.zhangwenit.common.dto.ResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("http://www.${feign.thcUrl}")
    private String host;

    @Value("#{'http://www' + '${feign.thcUrl}'.substring('${feign.thcUrl}'.indexOf('.'))}")
    private String host2;


    @GetMapping("/testValue")
    public Object user() {
        System.out.println(host);
        System.out.println(host2);
        return ResponseVO.buildSuccess();
    }
}
