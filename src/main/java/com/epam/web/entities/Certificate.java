package com.epam.web.entities;

public class Certificate {

    private final Long id;
    private final int score;

    public Certificate(Long id, int score) {
        this.id = id;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }
}
