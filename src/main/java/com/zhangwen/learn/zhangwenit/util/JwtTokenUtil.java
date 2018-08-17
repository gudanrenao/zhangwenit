package com.zhangwen.learn.zhangwenit.util;

import com.zhangwen.learn.zhangwenit.constant.ConstantKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.Date;

/**
 * token验证相关工具类
 *
 * @author zhangwen at 2018-08-16 17:22
 **/
public class JwtTokenUtil implements Serializable {

    private JwtTokenUtil() {
    }

    /**
     * 从subject生成令牌
     *
     * @return 令牌
     */
    public static String generateToken(String subject) {
        return ConstantKey.SIGNING_PREFIX + Jwts.builder()
                .setSubject(subject)
                // 设置过期时间  单位毫秒
                .setExpiration(new Date(System.currentTimeMillis() + ConstantKey.SIGNING_EFFECTIVE_TIME))
                //采用什么算法是可以自己选择的，不一定非要采用HS512
                .signWith(SignatureAlgorithm.HS512, ConstantKey.SIGNING_KEY)
                .compact();
    }

    /**
     * 从令牌中获取subject
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static String getSubjectFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(ConstantKey.SIGNING_KEY)
                .parseClaimsJws(token.replace(ConstantKey.SIGNING_PREFIX, ""))
                .getBody()
                .getSubject();
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(ConstantKey.SIGNING_KEY)
                    .parseClaimsJws(token.replace(ConstantKey.SIGNING_PREFIX, ""))
                    .getBody();
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 旧令牌
     * @return 新令牌
     */
    public static String refreshToken(String token) {
        return generateToken(getSubjectFromToken(token));
    }
}
