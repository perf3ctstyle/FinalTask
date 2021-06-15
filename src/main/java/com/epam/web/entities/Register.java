package com.epam.web.entities;

public class Register implements Identifiable {

    private final Long id;
    private final Long userId;
    private final Long facultyId;
    private final Integer scoreSum;
    private final Boolean isApproved;

    public Register(Long id, Long userId, Long facultyId, Integer scoreSum, Boolean isApproved) {
        this.id = id;
        this.userId = userId;
        this.facultyId = facultyId;
        this.scoreSum = scoreSum;
        this.isApproved = isApproved;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public Integer getScoreSum() {
        return scoreSum;
    }

    public Boolean isApproved() {
        return isApproved;
    }
}
