package com.zhangwen.learn.zhangwenit.api.system.controller;

import com.zhangwen.learn.zhangwenit.api.system.entity.ManageUser;
import com.zhangwen.learn.zhangwenit.api.system.service.ManageUserService;
import com.zhangwen.learn.zhangwenit.constant.ConstantKey;
import com.zhangwen.learn.zhangwenit.util.JwtTokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwen at 2018-08-15 22:56
 **/
@RestController
@RequestMapping("/manageUsers")
public class ManageUserController {

    private final ManageUserService manageUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ManageUserController(ManageUserService manageUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.manageUserService = manageUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/test")
    public String test() {

        int a = 9;
        int b = 8;


        return "hello security !!!";


    }

    /**
     * 该方法是注册用户的方法，默认放开访问控制
     *
     * @param user
     */
    @PostMapping("/signup")
    public void signUp(@RequestBody ManageUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        manageUserService.save(user);
    }

    @GetMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, HttpServletResponse response) {
        ManageUser userVo = manageUserService.findByName(name);
        if (userVo != null && bCryptPasswordEncoder.matches(password, userVo.getPassword())) {
            //自定义生成Token，因为使用了自定义登录，就不会执行JWTLoginFilter了，所以需要字段调用生成token并返给前端
            // 这里可以根据用户信息查询对应的角色信息，这里为了简单，我直接设置个空list
            List roleList = new ArrayList<>();
            String subject = userVo.getName() + "-" + roleList;
            String token = JwtTokenUtil.generateToken(subject);
            // 登录成功后，返回token到header里面
            response.addHeader("Authorization", token);
            return "SUCCESS";
        }
        return "FAIL";
    }

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    @GetMapping("/refreshToken")
    public String refreshToken(@RequestParam String token) {
        if (token == null || !token.startsWith(ConstantKey.SIGNING_PREFIX)) {
            return "token is null";
        }
        boolean expired = JwtTokenUtil.isTokenExpired(token);
        if (expired) {
            return "token is expired";
        } else {
            //返回一个新的token
            return JwtTokenUtil.refreshToken(token);
        }

    }
}
