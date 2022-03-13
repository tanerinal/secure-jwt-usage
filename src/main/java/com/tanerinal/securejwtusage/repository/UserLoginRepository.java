package com.tanerinal.securejwtusage.repository;

import com.tanerinal.securejwtusage.model.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    Optional<UserLogin> findByUsernameAndUserTokenAndTokenActive(String username, String userToken, boolean tokenActive);
    UserLogin findByUserToken(String userToken);
}
