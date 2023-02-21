package com.example.demosercurityratelimit.service.user;

import com.example.demosercurityratelimit.dto.UserRegisterDTO;
import com.example.demosercurityratelimit.model.JwtResponse;
import com.example.demosercurityratelimit.model.User;
import com.example.demosercurityratelimit.model.UserPrinciple;
import com.example.demosercurityratelimit.repository.IRoleRepository;
import com.example.demosercurityratelimit.repository.IUserRepository;
import com.example.demosercurityratelimit.service.jwt.JwtService;
import com.example.demosercurityratelimit.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public User findByUsername(String username) {
//        User user = userRepository.findByUsername(username).get();
//        return user;
//    }


    @Override
    public Optional<User> checkDoubleUser(String username) {
        return userRepository.checkDoubleUser(username);
    }
    @Override
    public ResponseEntity<?> login(User user){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.createToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userRepository.findByUsername(user.getUsername()).get();
            return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
        } catch (Exception e) {
            return ResponseEntity.ok("Not Found User");
        }
    }

    @Override
    public ResponseEntity<User> register(UserRegisterDTO userRegisterDTO) {
        if (userRepository.checkDoubleUser(userRegisterDTO.getUserName()).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User users = new User();
        users.setUsername(userRegisterDTO.getUserName());
        users.setPassword(userRegisterDTO.getPassword());
        String role = "1";
        Long role1 = Long.parseLong(role);
        users.setRole(roleRepository.findById(role1).get());
        save(users);
        return new ResponseEntity<>(save(users),HttpStatus.OK);
    }
}
