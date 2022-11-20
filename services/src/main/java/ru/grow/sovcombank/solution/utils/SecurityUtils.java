package ru.grow.sovcombank.solution.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.grow.sovcombank.solution.entity.user.SecurityUserEntity;

import java.security.Principal;

public class SecurityUtils {
    public static Boolean equalsId(Long sourceId, Principal principal) {
        Long principalId = ((SecurityUserEntity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        return sourceId.equals(principalId);
    }

    public static SecurityUserEntity getUserFromContext() {
        return (SecurityUserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
