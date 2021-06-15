package com.epam.web.command;

import com.epam.web.entities.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ChangeIsUserBlockedCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("mainLogger");

    private final UserService userService;

    private static final String GET_ADMIN_MAIN = "getAdminMainPage";
    private static final String ID = "id";
    private static final String USER_WAS_NOT_FOUND = "User with the following userId wasn't found: ";
    private static final String ERROR_PAGE = "error";

    public ChangeIsUserBlockedCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String userIdString = request.getParameter(ID);
        long userId = Long.parseLong(userIdString);

        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Long updatedUserId = user.getId();
            boolean isUpdatedUserBlocked = !user.isBlocked();

            userService.changeIsBlocked(updatedUserId, isUpdatedUserBlocked);
        } else {
            LOGGER.info(USER_WAS_NOT_FOUND + userId);
            return CommandResult.redirect(ERROR_PAGE);
        }
        return CommandResult.redirect(GET_ADMIN_MAIN);
    }
}
