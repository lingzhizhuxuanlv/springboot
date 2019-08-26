package com.lingzhizhuxuanlv.springboot.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static String secret;
    private static Long expiredTime;
    private static String subject;
    private static String issuer;

    public void setSecret(String sec) {
        secret = sec;
    }
    public void setExpiredTime(String exp) {
        expiredTime = Long.valueOf(exp);
    }
    public void setSubject(String sub) {
        subject = sub;
    }
    public void setIssuer(String iss) {
        issuer = iss;
    }

    public static String createJwt(Long userId, String username){
        Date now = new Date(System.currentTimeMillis());
        Date exp = new Date(System.currentTimeMillis() + expiredTime);
        return Jwts.builder()
                //压缩算法
                .compressWith(CompressionCodecs.DEFLATE)
                //主题名称
                .setSubject(subject)
                //签发者
                .setIssuer(issuer)
                //授予者
                .setAudience(username)
                //发行时间
                .setIssuedAt(now)
                //生效时间
                .setNotBefore(now)
                //到期时间
                .setExpiration(exp)
                //ID
                .setId(UUID.randomUUID().toString())
                //自定义声明
                .claim("userId", userId)
                //加密算法
                .signWith(generalKey())
                .compact();
    }

    public static Claims verifyJwt(String token){
        //通常最长三分钟
        long seconds = 3 * 60;
        Claims claims;
        try {
            claims = Jwts.parser()
                    //时间偏差兼容
                    .setAllowedClockSkewSeconds(seconds)
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private static Key generalKey() {
        byte[] jwtSecretBytes = Base64.getDecoder().decode(secret);
        return new SecretKeySpec(jwtSecretBytes, "HmacSHA256");
    }

}
