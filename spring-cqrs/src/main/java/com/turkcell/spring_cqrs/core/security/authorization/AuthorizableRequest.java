package com.turkcell.spring_cqrs.core.security.authorization;


import java.util.List;

public interface AuthorizableRequest {
    List<String> getRequiredRoles();
}