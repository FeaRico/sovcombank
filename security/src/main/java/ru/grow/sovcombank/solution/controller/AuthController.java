package ru.grow.sovcombank.solution.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grow.sovcombank.solution.dto.user.auth.AuthRequest;
import ru.grow.sovcombank.solution.dto.user.auth.AuthResponse;
import ru.grow.sovcombank.solution.service.SecurityUserService;
import ru.grow.sovcombank.solution.utils.JwtUtils;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final SecurityUserService securityUserService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, SecurityUserService securityUserService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.securityUserService = securityUserService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = securityUserService.loadUserByUsername(authRequest.getUsername());
        String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
