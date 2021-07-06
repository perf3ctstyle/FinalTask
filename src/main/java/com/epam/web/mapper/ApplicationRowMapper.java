package com.epam.web.mapper;

import com.epam.web.entities.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationRowMapper implements Mapper<Application> {

    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String FACULTY_ID = "FACULTY_ID";
    private static final String CERTIFICATE_SCORE = "CERTIFICATE_SCORE";
    private static final String FIRST_SUBJECT_SCORE = "FIRST_SUBJECT_SCORE";
    private static final String SECOND_SUBJECT_SCORE = "SECOND_SUBJECT_SCORE";
    private static final String THIRD_SUBJECT_SCORE = "THIRD_SUBJECT_SCORE";

    @Override
    public Application map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);
        Long facultyId = resultSet.getLong(FACULTY_ID);

        Integer certificateScore = resultSet.getInt(CERTIFICATE_SCORE);
        List<Integer> subjectsScores = new ArrayList<>();

        Integer firstSubjectScore = resultSet.getInt(FIRST_SUBJECT_SCORE);
        Integer secondSubjectScore = resultSet.getInt(SECOND_SUBJECT_SCORE);
        Integer thirdSubjectScore = resultSet.getInt(THIRD_SUBJECT_SCORE);

        subjectsScores.add(firstSubjectScore);
        subjectsScores.add(secondSubjectScore);
        subjectsScores.add(thirdSubjectScore);

        return new Application(id, userId, facultyId, certificateScore, subjectsScores);
    }
}
