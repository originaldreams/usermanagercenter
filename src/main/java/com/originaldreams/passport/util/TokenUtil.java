package com.originaldreams.passport.util;

import com.originaldreams.passport.config.JwtProperties;
import com.originaldreams.passport.model.entity.JwtUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private JwtProperties jwtProperties;

    @Autowired
    public TokenUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 由字符串生成加密key
     * @return
     */
    private SecretKey generalKey(){
        String stringKey = jwtProperties.getSecret();
//        byte[] encodedKey = Base64.decodeBase64(stringKey);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary (stringKey);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return key;
    }

    /**
     * 检查 token 是否处于有效期内
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = this.getSubjectFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);
        return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)) && !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())));
    }

    /**
     * 获得我们封装在 token 中的 token 创建时间
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.parseJWT(token);
            created = new Date((Long) claims.get("created"));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 获得我们封装在 token 中的 token 过期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.parseJWT(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 检查当前时间是否在封装在 token 中的过期时间之后，若是，则判定为 token 过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    /**
     * 检查 token 是否是在最后一次修改密码之前创建的（账号修改密码之后之前生成的 token 即使没过期也判断为无效）
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 根据 TokenDetail 生成 Token
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", this.generateCurrentDate());
        return this.createJWT(claims);
    }

    /**
     * 创建jwt
     * @return
     * @throws Exception
     */
    private String createJWT(Map<String, Object> claims) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(key, signatureAlgorithm);
        return builder.compact();
    }


    /**
     * token 过期时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.jwtProperties.getExpiration() * 1000);
    }

    /**
     * 获得当前时间
     *
     * @return
     */
    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }


    /**
     * 从 token 中拿到 username
     *
     * @param token
     * @return
     */
    public String getSubjectFromToken(String token) {
        Claims claims = this.parseJWT(token);
        return claims.getSubject();
    }


    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws
            MalformedJwtException, io.jsonwebtoken.security.SignatureException, ExpiredJwtException, UnsupportedJwtException,
            IllegalArgumentException {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

//    public static boolean isJwtString(String jwtToken) {
//        if (null == jwtToken || jwtToken.trim().isEmpty()) {
//            return false;
//        }
//
//        try {
//            this.parseJWT(jwtToken);
//            return true;
//        } catch (MalformedJwtException | SignatureException | ExpiredJwtException | UnsupportedJwtException |
//                IllegalArgumentException e) {
//            return false;
//        }
//
//    }
}
