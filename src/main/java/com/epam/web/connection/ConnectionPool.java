package com.epam.web.connection;

import com.epam.web.exception.ConnectionPoolException;

import java.sql.Connection;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final Lock instanceLock = new ReentrantLock();

    private final Queue<ProxyConnection> availableConnections = new ArrayDeque<>();
    private final Set<ProxyConnection> usedConnections = new HashSet<>();

    private final Lock connectionLock = new ReentrantLock();
    private final Semaphore connectionSemaphore;

    private static final int POOL_SIZE = 10;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            try {
                instanceLock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    private ConnectionPool() {
        connectionSemaphore = new Semaphore(POOL_SIZE);
        createConnections();
    }

    private void createConnections() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        List<Connection> connections = new ArrayList<>();

        IntStream.range(0, POOL_SIZE).forEach(i -> {
            Connection connection = connectionFactory.createConnection();
            connections.add(connection);
        });

        List<ProxyConnection> proxyConnections = new ArrayList<>();

        connections.stream().forEach(connection -> {
            ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            proxyConnections.add(proxyConnection);
        });

        availableConnections.addAll(proxyConnections);
    }

    public ProxyConnection getConnection() {
        try {
            connectionSemaphore.acquire();
            connectionLock.lock();

            ProxyConnection connection = availableConnections.poll();
            usedConnections.add(connection);

            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        } finally {
            connectionLock.unlock();
        }
    }

    public void returnConnection(ProxyConnection connection) {
        try {
            connectionLock.lock();
            if (usedConnections.contains(connection)) {
                usedConnections.remove(connection);
                availableConnections.add(connection);
                connectionSemaphore.release();
            }
        } finally {
            connectionLock.unlock();
        }
    }
}
