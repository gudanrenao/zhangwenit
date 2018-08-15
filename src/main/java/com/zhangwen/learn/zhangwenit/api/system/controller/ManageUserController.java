package com.zhangwen.learn.zhangwenit.api.system.controller;

import com.zhangwen.learn.zhangwenit.api.system.entity.ManageUser;
import com.zhangwen.learn.zhangwenit.api.system.service.ManageUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangwen at 2018-08-15 22:56
 **/
@RestController
@RequestMapping("manageUsers")
public class ManageUserController {

    private final ManageUserService manageUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ManageUserController(ManageUserService manageUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.manageUserService = manageUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @GetMapping(value = "/login")
    public void login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        ManageUser userVo = manageUserService.findByName(username);
        if (userVo != null && bCryptPasswordEncoder.matches(password, userVo.getPassword())) {
            //自定义生成Token，因为使用了自定义登录，就不会执行JWTLoginFilter了，所以需要字段调用生成token并返给前端
            // 这里可以根据用户信息查询对应的角色信息，这里为了简单，我直接设置个空list
            List roleList = new ArrayList<>();
            String subject = userVo.getName() + "-" + roleList;
            String token = Jwts.builder()
                    .setSubject(subject)
                    .setExpiration(new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000)) // 设置过期时间 365 * 24 * 60 * 60秒(这里为了方便测试，所以设置了1年的过期时间，实际项目需要根据自己的情况修改)
                    .signWith(SignatureAlgorithm.HS512, "MyJwtSecret") //采用什么算法是可以自己选择的，不一定非要采用HS512
                    .compact();
            // 登录成功后，返回token到header里面
            response.addHeader("Authorization", "Bearer " + token);
        }
    }
}
