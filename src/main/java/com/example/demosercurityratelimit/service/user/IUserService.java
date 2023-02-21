package com.example.demosercurityratelimit.service.user;

import com.example.demosercurityratelimit.dto.UserRegisterDTO;
import com.example.demosercurityratelimit.model.User;
import com.example.demosercurityratelimit.service.IGeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
//    User findByUsername(String username);

    Optional<User> checkDoubleUser(String username);

    ResponseEntity<?> login( User user);

    ResponseEntity<User> register(UserRegisterDTO userRegisterDTO);
}
