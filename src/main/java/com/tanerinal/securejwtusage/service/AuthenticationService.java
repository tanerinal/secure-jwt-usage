package com.tanerinal.securejwtusage.service;

import com.tanerinal.securejwtusage.model.dto.AuthResponse;
import com.tanerinal.securejwtusage.model.entity.UserLogin;
import com.tanerinal.securejwtusage.repository.UserLoginRepository;
import com.tanerinal.securejwtusage.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserLoginRepository userLoginRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.timeout:18000}")
    private long jwtTimeout;

    public AuthResponse authenticateUser(String username, String password) {
        Assert.isTrue(StringUtils.isNotBlank(username), "Username should not left blank!");
        Assert.isTrue(StringUtils.isNotBlank(password), "Password should not left blank!");

        //Here an authentication check should be done but beyond the scope of this project.
        log.info("Authentication of {} successfull!", username);

        String jwtToken = JwtUtils.createJWTToken(username, this.jwtSecret, this.jwtTimeout);

        userLoginRepository.save(UserLogin.builder()
                .created(new Date())
                .username(username)
                .userToken(jwtToken)
                .tokenActive(true)
                .build());

        return AuthResponse.builder()
                .username(username)
                .token(jwtToken)
                .build();
    }
}