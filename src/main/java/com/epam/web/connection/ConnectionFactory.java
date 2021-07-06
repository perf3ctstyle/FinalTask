package com.epam.web.connection;

import com.epam.web.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String PROPERTIES = "database/database.properties";
    private static final String URL = "database.url";
    private static final String USERNAME = "database.username";
    private static final String PASSWORD = "database.password";
    private static final String DRIVER = "database.driver";

    public Connection createConnection() throws ConnectionPoolException {
        try (InputStream inputStream =
                     ConnectionFactory.class.getClassLoader().getResourceAsStream(PROPERTIES)) {

            Properties properties = new Properties();
            properties.load(inputStream);

            String dbUrl = properties.getProperty(URL);
            String dbUsername = properties.getProperty(USERNAME);
            String dbPassword = properties.getProperty(PASSWORD);
            String dbDriver = properties.getProperty(DRIVER);
            Class.forName(dbDriver);

            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }
}
