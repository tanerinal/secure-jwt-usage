package com.tanerinal.securejwtusage.util;

import com.tanerinal.securejwtusage.exception.UnauthenticatedException;
import com.tanerinal.securejwtusage.model.entity.UserLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class JwtUtils {
    private static final SignatureAlgorithm HS256 = SignatureAlgorithm.HS256;
    private static final String ALG_NAME = HS256.getJcaName();
    private static final String TOKEN_ISSUER = "securejwtusage.tanerinal.com";

    private JwtUtils() {
    }

    public static String createJWTToken(String username, String secret, long expiration) {
        ZonedDateTime now = ZonedDateTime.now();

        return Jwts.builder()
                .setSubject(username)
                .setAudience(username)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(now.plusSeconds(expiration).toInstant()))
                .signWith(HS256, new SecretKeySpec(DatatypeConverter.parseBase64Binary(secret), ALG_NAME))
                .setId(UUID.randomUUID().toString())
                .setIssuer(TOKEN_ISSUER)
                .compact();
    }

    public static void verifyToken(String token, String secret, String audience, Optional<UserLogin> userLogin) {
        Claims claims = extractAllClaims(token, secret);

        Assert.isTrue(StringUtils.equals(claims.getIssuer(), TOKEN_ISSUER), "Invalid token issuer!");
        Assert.isTrue(StringUtils.equals(claims.getAudience(), audience), "Invalid token audience!");
        Assert.isTrue(userLogin.orElseThrow(() -> new UnauthenticatedException("Logged out token!")).isTokenActive(), "Logged out token!");

    }

    public static Claims extractAllClaims(String token, String secret) {
        return Jwts
                .parser()
                .setSigningKey(new SecretKeySpec(DatatypeConverter.parseBase64Binary(secret), ALG_NAME))
                .parseClaimsJws(token)
                .getBody();
    }
}
