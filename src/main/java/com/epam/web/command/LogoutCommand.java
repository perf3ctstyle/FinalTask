package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private static final String INDEX = "/index.jsp";

    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return CommandResult.forward(INDEX);
    }
}
