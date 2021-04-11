package com.epam.web.entities;

import com.epam.web.enums.UserRoles;

public class User implements Identifiable {

    private final Long id;
    private final String login;
    private final String password;
    private final UserRoles userRoles;

    public User(Long id, String login, String password, UserRoles userRoles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userRoles = userRoles;
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

    public UserRoles getUserRole() {
        return userRoles;
    }
}
