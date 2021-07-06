package com.epam.web.entities;

import java.util.List;

public class Application implements Identifiable {

    private final Long id;
    private final Long userId;
    private final Long facultyId;
    private final Integer certificateScore;
    private final List<Integer> subjectsScores;

    public Application(Long id, Long userId, Long facultyId, Integer certificateScore, List<Integer> subjectsScores) {
        this.id = id;
        this.userId = userId;
        this.facultyId = facultyId;
        this.certificateScore = certificateScore;
        this.subjectsScores = subjectsScores;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public Integer getCertificateScore() {
        return certificateScore;
    }

    public List<Integer> getSubjectsScores() {
        return subjectsScores;
    }
}
