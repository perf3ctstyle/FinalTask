create table user (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login VARCHAR(50),
    password VARCHAR(256),
    role ENUM('ABITURIENT', 'ADMIN') DEFAULT 'ABITURIENT',
    is_blocked BOOLEAN
);

create table faculty (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    description VARCHAR(1000),
    admission_plan INT
);

create table user_credential (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    name VARCHAR(255),
    surname VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

create table user_application (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    faculty_id BIGINT,
    certificate_score INT,
    first_subject_score INT,
    second_subject_score INT,
    third_subject_score INT,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (faculty_id) REFERENCES faculty(id)
);

create table register (
    id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT,
    application_id BIGINT,
    is_approved BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (application_id) REFERENCES user_application(id)
);