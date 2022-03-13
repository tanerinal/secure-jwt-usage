package com.tanerinal.securejwtusage.service;

import com.tanerinal.securejwtusage.model.entity.UserLogin;
import com.tanerinal.securejwtusage.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogoutService {
    private final UserLoginRepository userLoginRepository;

    public void logout(String token) {
        UserLogin userLogin = userLoginRepository.findByUserToken(token);
        userLogin.setTokenActive(false);
        userLoginRepository.save(userLogin);
    }
}