package com.epam.web.entities;

public class Subject {

    private final Long id;
    private final String name;
    private final int score;

    public Subject(Long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
