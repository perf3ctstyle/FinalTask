package com.epam.web.entities;

public class Subject implements Identifiable {

    private final Long id;
    private final String name;

    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
