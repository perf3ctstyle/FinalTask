package com.epam.web.entities;

public class Register implements Identifiable {

    private final Long id;
    private final Long userId;
    private final Long applicationId;
    private final Boolean isApproved;

    public Register(Long id, Long userId, Long applicationId, Boolean isApproved) {
        this.id = id;
        this.userId = userId;
        this.applicationId = applicationId;
        this.isApproved = isApproved;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public Boolean isApproved() {
        return isApproved;
    }
}
