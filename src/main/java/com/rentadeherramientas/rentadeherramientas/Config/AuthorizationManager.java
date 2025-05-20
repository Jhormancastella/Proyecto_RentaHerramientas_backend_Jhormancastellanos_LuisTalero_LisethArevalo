package com.rentadeherramientas.rentadeherramientas.Config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationManager {
    
    public boolean hasRole(Authentication authentication, String role) {
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(role));
    }
    
    public boolean hasAnyRole(Authentication authentication, String... roles) {
        if (authentication == null) return false;
        return authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .anyMatch(role -> {
                    for (String r : roles) {
                        if (role.equals(r)) return true;
                    }
                    return false;
                });
    }
    
    public boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }
    
    public boolean hasPermission(Authentication authentication, String permission) {
        return hasRole(authentication, "ROLE_" + permission.toUpperCase());
    }
}
