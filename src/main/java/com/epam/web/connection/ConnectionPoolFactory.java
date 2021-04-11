package com.epam.web.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPoolFactory {

    private static final String PROPERTIES = "database/database.properties";
    private static final String URL = "database.url";
    private static final String USERNAME = "database.username";
    private static final String PASSWORD = "database.password";
    private static final String DRIVER = "database.driver";

    private static final int POOL_SIZE = 10;

    public ConnectionPool createPool() throws ConnectionPoolException {
        try (InputStream inputStream =
                     ConnectionPoolFactory.class.getClassLoader().getResourceAsStream(PROPERTIES)) {

            Properties properties = new Properties();
            properties.load(inputStream);

            String dbUrl = properties.getProperty(URL);
            String dbUsername = properties.getProperty(USERNAME);
            String dbPassword = properties.getProperty(PASSWORD);
            String dbDriver = properties.getProperty(DRIVER);
            Class.forName(dbDriver);

            List<ProxyConnection> connections = new ArrayList<>();

            for (int i=0; i<POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                connections.add(proxyConnection);
            }

            return new ConnectionPool(connections, POOL_SIZE);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new ConnectionPoolException(e);
        }
    }
}
