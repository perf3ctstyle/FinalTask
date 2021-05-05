package com.epam.web.command;

import com.epam.web.entities.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService userService;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MAIN = "main";
    private static final String INVALID_LOGIN = "invalidLogin";
    private static final String IS_BLOCKED = "isBlocked";

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        Optional<User> optionalUser = userService.login(username, password);

        HttpSession httpSession = request.getSession();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isBlocked()) {
                httpSession.setAttribute(IS_BLOCKED, true);
                return CommandResult.redirect(INVALID_LOGIN);
            }

            httpSession.setAttribute("user", user);
            return CommandResult.redirect(MAIN);
        } else {
            httpSession.setAttribute(INVALID_LOGIN, true);
            return CommandResult.redirect(INVALID_LOGIN);
        }
    }
}
