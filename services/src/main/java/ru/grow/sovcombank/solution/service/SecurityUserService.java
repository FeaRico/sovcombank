package ru.grow.sovcombank.solution.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface SecurityUserService extends UserDetailsService {
    PasswordEncoder getPasswordEncoder();
}
