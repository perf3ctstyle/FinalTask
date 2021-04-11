create table user (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login VARCHAR(50),
    password_hash VARCHAR(256),
    credential_id BIGINT,
    application_id BIGINT,
    role enum('ABITURIENT', 'ADMIN') DEFAULT 'ABITURIENT',
    FOREIGN KEY (credential_id) REFERENCES credential(id),
    FOREIGN KEY (application_id) REFERENCES application(id)
);

create table faculty (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name_en VARCHAR(255),
    name_ru VARCHAR(255),
    name_by VARCHAR(255),
    description_en VARCHAR(255),
    description_ru VARCHAR(255),
    description_by VARCHAR(255),
    admission_plan BIGINT
);

create table credential (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    name VARCHAR(255),
    surname VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

create table application (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    faculty_id BIGINT,
    certificate_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (faculty_id) REFERENCES faculty(id),
    FOREIGN KEY (certificate_id) REFERENCES certificate(id)
);

create table subject (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    name VARCHAR(255),
    score BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

create table certificate (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    score BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);