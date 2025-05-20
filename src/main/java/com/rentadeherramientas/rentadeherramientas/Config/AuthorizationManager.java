package com.rentadeherramientas.rentadeherramientas.Config;

import org.springframework.security.core.Authentication;


public interface AuthorizationManager {
    boolean hasRole(Authentication authentication, String role);
    
    boolean hasAnyRole(Authentication authentication, String... roles);
    
    boolean isAuthenticated(Authentication authentication);
    
    boolean hasPermission(Authentication authentication, String permission);
}
