package com.cinevault.controller;

import com.cinevault.config.TokenService;
import com.cinevault.controller.request.LoginRequest;
import com.cinevault.controller.request.UserRequest;
import com.cinevault.controller.response.LoginResponse;
import com.cinevault.controller.response.UserResponse;
import com.cinevault.entity.User;
import com.cinevault.exception.UsernameOrPasswordInvalidException;
import com.cinevault.mapper.UserMapper;
import com.cinevault.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinevault/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        User savedUser = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch(BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("Invalid username or password.");
        }

    }

}
