package com.tanerinal.securejwtusage.controller;

import com.tanerinal.securejwtusage.Constants;
import com.tanerinal.securejwtusage.model.dto.AuthRequest;
import com.tanerinal.securejwtusage.model.dto.AuthResponse;
import com.tanerinal.securejwtusage.service.AuthenticationService;
import com.tanerinal.securejwtusage.service.LogoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
@RequiredArgsConstructor
@Slf4j
public class LogoutController {
    private final LogoutService logoutService;

    @PostMapping
    public ResponseEntity<String> logout(@RequestHeader (Constants.HEADER_AUTHORIZATION) String token) {
        logoutService.logout(token);
        return ResponseEntity.ok(Constants.MESSAGE_SUCCESS);
    }

}
