package ru.grow.sovcombank.solution.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.grow.sovcombank.solution.types.Role;

@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/admin", "/api/v1/admin/**").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers("/api/v1/transaction", "/api/v1/transaction/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .antMatchers("/api/v1/broker/account", "/api/v1/broker/account/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .antMatchers("/api/v1/user", "/api/v1/user/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic()
                .and().build();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
}