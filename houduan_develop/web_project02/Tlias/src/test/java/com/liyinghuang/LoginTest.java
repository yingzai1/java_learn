package com.liyinghuang;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginTest {
    //生成响应的jwt
    @Test
    public void generateJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 10);
        claims.put("username", "liyinghuang");
        //生成jwt
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "bGl5aW5naHVhbmc=")
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }
    //解析jwt
    //eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAsInVzZXJuYW1lIjoibGl5aW5naHVhbmciLCJleHAiOjE3NjQ0NDQwNjd9.NkJBHEXWhTRt9JT1e99JAeAK3U7ROBA1cg0Gu14x1rA
    @Test
    public void parseJwt(){
        Claims body = Jwts.parser().setSigningKey("bGl5aW5naHVhbmc=")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwidXNlcm5hbWUiOiJzb25namlhbmciLCJleHAiOjE3NjQ0NDQ4Mzd9.oca2--51MQn0gzC1gjtPAVkp6jeEzCiaw66WHY9KCfM")
                .getBody();
        System.out.println(body);
    }
}
