package com.epam.web.entities;

public class Faculty {

    private final Long id;
    private final String name;
    private final String description;
    private final int admissionPlan;

    public Faculty(Long id, String name, String description, int admissionPlan) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.admissionPlan = admissionPlan;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAdmissionPlan() {
        return admissionPlan;
    }
}
