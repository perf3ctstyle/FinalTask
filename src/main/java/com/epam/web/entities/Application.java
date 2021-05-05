package com.epam.web.entities;

public class Application implements Identifiable {

    private final Long id;
    private final Faculty faculty;
    private final Integer[] subjectsScore;
    private final Integer certificateScore;

    public Application(Long id, Faculty faculty, Integer[] subjectsScore, Integer certificateScore) {
        this.id = id;
        this.faculty = faculty;
        this.subjectsScore = subjectsScore;
        this.certificateScore = certificateScore;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Integer[] getSubjectsScore() {
        return subjectsScore;
    }

    public Integer getCertificate() {
        return certificateScore;
    }
}
