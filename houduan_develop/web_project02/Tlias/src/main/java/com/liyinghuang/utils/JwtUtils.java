package com.liyinghuang.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    /** 默认签名密钥（Base64 编码后） */
    private static final String DEFAULT_SECRET = "bGl5aW5naHVhbmc=";

    /** 默认过期时间 12h */
    private static final long DEFAULT_EXPIRE = 12 * 3600 * 1000;

    /**
     * 生成 JWT（使用默认密钥与默认 12h 过期）
     */
    public static String generateJwt(Map<String, Object> claims) {
        return generateJwt(claims, DEFAULT_SECRET, DEFAULT_EXPIRE);
    }

    /**
     * 生成 JWT（自定义过期时间，单位毫秒）
     */
    public static String generateJwt(Map<String, Object> claims, long expireMillis) {
        return generateJwt(claims, DEFAULT_SECRET, expireMillis);
    }

    /**
     * 生成 JWT（完全自定义）
     */
    public static String generateJwt(Map<String, Object> claims, String secret, long expireMillis) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expireMillis))
                .compact();
    }

    /**
     * 解析 JWT（使用默认密钥）
     */
    public static Claims parseJwt(String jwt) {
        return parseJwt(jwt, DEFAULT_SECRET);
    }

    /**
     * 解析 JWT（自定义密钥）
     */
    public static Claims parseJwt(String jwt, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
