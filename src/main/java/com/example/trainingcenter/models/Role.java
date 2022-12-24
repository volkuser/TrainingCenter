package com.example.trainingcenter.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYEE, HR, ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}

