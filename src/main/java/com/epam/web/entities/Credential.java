package com.epam.web.entities;

public class Credential implements Identifiable {

    private final Long id;
    private final String name;
    private final String surname;

    public Credential(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
