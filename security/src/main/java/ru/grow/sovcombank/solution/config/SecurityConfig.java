package ru.grow.sovcombank.solution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.grow.sovcombank.solution.filter.AuthRequestFilter;
import ru.grow.sovcombank.solution.service.SecurityUserService;
import ru.grow.sovcombank.solution.types.Role;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SecurityUserService securityUserService;
    private final PasswordEncoder passwordEncoder;
    private final AuthRequestFilter authRequestFilter;

    public SecurityConfig(SecurityUserService securityUserService, PasswordEncoder passwordEncoder, AuthRequestFilter authRequestFilter) {
        this.securityUserService = securityUserService;
        this.passwordEncoder = passwordEncoder;
        this.authRequestFilter = authRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .disable()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/admin/**").hasAuthority(Role.ROLE_ADMIN.name())
                .antMatchers("/api/v1/broker/account/**").hasAnyAuthority(Role.ROLE_USER.name(), Role.ROLE_ADMIN.name())
                .antMatchers("/api/v1/transaction/**").hasAnyAuthority(Role.ROLE_USER.name(), Role.ROLE_ADMIN.name())
                .antMatchers("/api/v1/user/**").hasAnyAuthority(Role.ROLE_USER.name(), Role.ROLE_ADMIN.name())
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
