package com.epam.web.command;

import com.epam.web.entities.Application;
import com.epam.web.entities.Register;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class DeleteUserCommand implements Command {

    private final UserService userService;

    private static final String ID = "id";
    private static final String GET_ADMIN_MAIN = "getAdminMainPage";

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String userIdString = request.getParameter(ID);
        long userId = Long.parseLong(userIdString);

        userService.deleteById(userId);

        return CommandResult.redirect(GET_ADMIN_MAIN);
    }
}
