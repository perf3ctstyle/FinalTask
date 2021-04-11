package com.epam.web.entities;

public class Application {

    private final Long id;
    private final Faculty faculty;
    private final Subject[] subjects;
    private final Certificate certificate;

    public Application(Long id, Faculty faculty, Subject[] subjects, Certificate certificate) {
        this.id = id;
        this.faculty = faculty;
        this.subjects = subjects;
        this.certificate = certificate;
    }

    public Long getId() {
        return id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public Certificate getCertificate() {
        return certificate;
    }
}
