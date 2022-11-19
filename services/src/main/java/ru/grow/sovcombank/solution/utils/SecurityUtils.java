package ru.grow.sovcombank.solution.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ru.grow.sovcombank.solution.entity.user.SecurityUserEntity;

import java.security.Principal;

public class SecurityUtils {
    public static Boolean equalsId(Long sourceId, Principal principal) {
        Long principalId = ((SecurityUserEntity) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        return sourceId.equals(principalId);
    }
}
