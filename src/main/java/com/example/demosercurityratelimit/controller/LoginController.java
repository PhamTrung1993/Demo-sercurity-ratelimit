package com.example.demosercurityratelimit.controller;

import com.example.demosercurityratelimit.dto.UserRegisterDTO;
import com.example.demosercurityratelimit.model.JwtResponse;
import com.example.demosercurityratelimit.model.User;
import com.example.demosercurityratelimit.service.jwt.JwtService;
import com.example.demosercurityratelimit.service.role.IRoleService;
import com.example.demosercurityratelimit.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/userManagement/v1")
public class LoginController {
    @Autowired
    private IUserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return userService.login(user);
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.register(userRegisterDTO);
    }
}
