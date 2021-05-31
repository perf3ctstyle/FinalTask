package com.epam.web.command;

import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is an interface that specifies the behaviour of all commands as part of the Front Controller pattern.
 *
 * @author Nikita Torop
 */
public interface Command {
    /**
     * Handles the request processing delegated by the {@link com.epam.web.controller.Controller}.
     *
     * @param  request  {@link HttpServletRequest} object
     * @param  response {@link HttpServletResponse} object
     * @throws ServiceException in the case of a service-level exception
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
