package com.epam.web.entities;

import com.epam.web.enums.UserRoles;

public class User implements Identifiable {

    private final Long id;
    private final String login;
    private final String password;
    private final UserRoles role;
    private final boolean isBlocked;

    public User(Long id, String login, String password, UserRoles role, boolean isBlocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRoles getRole() {
        return role;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}
