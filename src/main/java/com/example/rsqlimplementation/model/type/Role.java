package com.example.rsqlimplementation.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_USER("Пользователь"),
    ROLE_ADMIN("Администратор"),
    ROLE_ORGANIZER("Организатор");

    private final String title;
    @Override
    public String getAuthority() {
        return name();
    }
}
