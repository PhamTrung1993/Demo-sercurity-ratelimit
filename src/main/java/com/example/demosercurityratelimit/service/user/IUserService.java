package com.example.demosercurityratelimit.service.user;

import com.example.demosercurityratelimit.model.User;
import com.example.demosercurityratelimit.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    Optional<User> checkDoubleUser(String username);
}
