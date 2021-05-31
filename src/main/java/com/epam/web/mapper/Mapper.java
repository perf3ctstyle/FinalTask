package com.epam.web.mapper;

import com.epam.web.entities.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is an interface that is implemented by classes which create objects based on the data
 * received from the {@link ResultSet}.
 *
 * @param <T> must implement {@link Identifiable} interface
 * @author Nikita Torop
 */
public interface Mapper<T extends Identifiable> {

    /**
     * Creates an {@link Identifiable} object based on the data received from the {@link ResultSet}.
     *
     * @param resultSet {@link ResultSet} object
     * @return {@link Identifiable} object
     * @throws SQLException due to {@link ResultSet} behaviour
     */
    T map(ResultSet resultSet) throws SQLException;
}