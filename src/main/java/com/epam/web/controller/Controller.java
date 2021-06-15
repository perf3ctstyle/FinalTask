package com.epam.web.controller;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger("mainLogger");

    private final CommandFactory commandFactory = new CommandFactory();

    private static final String COMMAND = "command";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_PAGE = "/error.jsp";
    private static final String CONTROLLER_COMMAND = "/controller?command=";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandType = request.getParameter(COMMAND);
        Command command = commandFactory.create(commandType);

        String page;
        boolean isRedirect = false;

        try {
            CommandResult result = command.execute(request, response);
            page = result.getPage();
            isRedirect = result.isRedirect();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            page = ERROR_PAGE;
        }

        if (isRedirect) {
            String contextPath = getServletContext().getContextPath();
            String pagePath = contextPath + CONTROLLER_COMMAND + page;
            response.sendRedirect(pagePath);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}