package com.epam.web.connection;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final Lock instanceLock = new ReentrantLock();

    private final Queue<ProxyConnection> availableConnections = new ArrayDeque<>();
    private final Set<ProxyConnection> usedConnections = new HashSet<>();

    private final Lock connectionLock = new ReentrantLock();
    private final Semaphore connectionSemaphore;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            try {
                instanceLock.lock();
                if (instance == null) {
                    ConnectionPoolFactory connectionPoolFactory = new ConnectionPoolFactory();
                    instance = connectionPoolFactory.createPool();
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    ConnectionPool(List<ProxyConnection> connectionsWithoutPools, int poolSize) {
        connectionSemaphore = new Semaphore(poolSize);

        List<ProxyConnection> connectionsWithPools = new ArrayList<>();
        connectionsWithoutPools.stream().forEach(connection -> {
            connection.setConnectionPool(this);
            connectionsWithPools.add(connection);
        });

        availableConnections.addAll(connectionsWithPools);
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
