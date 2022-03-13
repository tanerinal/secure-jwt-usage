package com.tanerinal.securejwtusage.controller;

import com.tanerinal.securejwtusage.model.dto.AuthRequest;
import com.tanerinal.securejwtusage.model.dto.AuthResponse;
import com.tanerinal.securejwtusage.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
@Slf4j
public class AuthenticateController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public AuthResponse authenticate(@RequestBody @NonNull AuthRequest authRequest) {
        log.info("Authentication request for user {} received!", authRequest.getUsername());
        return authenticationService.authenticateUser(authRequest.getUsername(), authRequest.getPassword());
    }
}
