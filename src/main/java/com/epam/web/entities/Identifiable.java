package com.epam.web.entities;

/**
 * This is an interface that indicates that the object of a class that implements this interface
 * has an id field.
 *
 * @author Nikita Torop
 */
public interface Identifiable {

    /**
     * Returns the id of this object.
     *
     * @return the value of the id field of this object.
     */
    Long getId();
}
