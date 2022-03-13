package com.tanerinal.securejwtusage.controller;

import com.tanerinal.securejwtusage.Constants;
import com.tanerinal.securejwtusage.model.entity.UserLogin;
import com.tanerinal.securejwtusage.repository.UserLoginRepository;
import com.tanerinal.securejwtusage.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
@Slf4j
public class BusinessController {
    private final UserLoginRepository userLoginRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @GetMapping
    public ResponseEntity<String> businessMethod(@RequestHeader HttpHeaders headers) {
        //This validation block should be done by Spring Security or other centralized mechanism in a proper project but now is beyond the scope of this project.
        Optional<UserLogin> userLogin = userLoginRepository.findByUsernameAndUserTokenAndTokenActive(headers.getFirst(Constants.HEADER_USERNAME), headers.getFirst(Constants.HEADER_AUTHORIZATION), true);
        JwtUtils.verifyToken(headers.getFirst(Constants.HEADER_AUTHORIZATION), jwtSecret, headers.getFirst(Constants.HEADER_USERNAME), userLogin);

            return ResponseEntity.ok(Constants.MESSAGE_SUCCESS);
    }
}
