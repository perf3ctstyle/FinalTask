package com.epam.web.entities;

public class AdmittedAbiturient implements Identifiable {

    private final Long id;
    private final Long userId;
    private final Long facultyId;

    public AdmittedAbiturient(Long id, Long userId, Long facultyId) {
        this.id = id;
        this.userId = userId;
        this.facultyId = facultyId;
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
}
