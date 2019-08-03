package com.lingzhizhuxuanlv.springboot.util;

import com.lingzhizhuxuanlv.springboot.conf.ConfigConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    public static String createJwt(Long userId, String username){
        Date now = new Date(System.currentTimeMillis());
        Date exp = new Date(System.currentTimeMillis() + ConfigConstant.jwtExpiredTime);
        return Jwts.builder()
                //签发者
                .setIssuer(ConfigConstant.jwtIssuer)
                //授予者
                .setAudience(username)
                //主题名称
                .setSubject(ConfigConstant.jwtSubject)
                //ID
                .setId(userId.toString())
                //开始时间
                .setIssuedAt(now)
                //结束时间
                .setExpiration(exp)
                //附加参数
                .claim("userId", userId)
                .claim("username", username)
                //加密方法
                .signWith(SignatureAlgorithm.HS256, generalKey())
                .compact();
    }

    public static Claims verifyJwt(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private static Key generalKey() {
        byte[] jwtSecretBytes = Base64.getDecoder().decode(ConfigConstant.jwtSecret);
        return new SecretKeySpec(jwtSecretBytes, "HS256");
    }

}
