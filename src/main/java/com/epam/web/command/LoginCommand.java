package com.epam.web.command;

import com.epam.web.entities.User;
import com.epam.web.enums.UserRole;
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
    private static final String IS_BLOCKED = "isBlocked";

    private static final String ID = "id";
    private static final String ROLE = "role";

    private static final String ADMIN = "ADMIN";

    private static final String USER_MAIN = "getFacultyListPage";
    private static final String ADMIN_MAIN = "getAdminMainPage";
    private static final String INVALID_LOGIN = "invalidLogin";

    private static final String LOGIN_PAGE = "/index.jsp";

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        Optional<User> optionalUser = userService.login(username, password);

        HttpSession httpSession = request.getSession(false);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.isBlocked()) {
                request.setAttribute(IS_BLOCKED, true);
                return CommandResult.forward(LOGIN_PAGE);
            }

            Long userId = user.getId();
            httpSession.setAttribute(ID, userId);

            UserRole userRole = user.getRole();
            String stringUserRole = userRole.toString();
            httpSession.setAttribute(ROLE, stringUserRole);

            if (stringUserRole.equals(ADMIN)) {
                return CommandResult.redirect(ADMIN_MAIN);
            } else {
                return CommandResult.redirect(USER_MAIN);
            }
        } else {
            request.setAttribute(INVALID_LOGIN, true);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
