package com.epam.web.mapper;

import com.epam.web.entities.Certificate;
import com.epam.web.enums.CertificateFields;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateRowMapper implements Mapper<Certificate> {
    @Override
    public Certificate map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(CertificateFields.ID.toString());

        int score = resultSet.getInt(CertificateFields.SCORE.toString());

        return new Certificate(id, score);
    }
}
