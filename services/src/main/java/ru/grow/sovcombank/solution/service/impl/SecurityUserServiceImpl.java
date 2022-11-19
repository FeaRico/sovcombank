package ru.grow.sovcombank.solution.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.grow.sovcombank.solution.repository.SecurityUserRepository;
import ru.grow.sovcombank.solution.service.SecurityUserService;

@Service
public class SecurityUserServiceImpl implements SecurityUserService {
    private final SecurityUserRepository securityUserRepository;

    public SecurityUserServiceImpl(SecurityUserRepository securityUserRepository) {
        this.securityUserRepository = securityUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securityUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user " + username));
    }
}
