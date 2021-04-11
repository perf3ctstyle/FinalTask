package com.epam.web.enums;

public enum UserFields {

    ID("ID"),
    LOGIN("LOGIN"),
    PASSWORD("PASSWORD"),
    USER_ROLE("USER_ROLE");

    private final String name;

    UserFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
