package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return CommandResult.forward("index.jsp");
    }
}
