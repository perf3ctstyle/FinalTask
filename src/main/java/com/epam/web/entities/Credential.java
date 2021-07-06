package com.epam.web.entities;

public class Credential implements Identifiable {

    private final Long id;
    private final Long userId;
    private final String name;
    private final String surname;

    public Credential(Long id, Long userId, String name, String surname) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
