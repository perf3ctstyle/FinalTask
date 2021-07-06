package com.epam.web.command;

import com.epam.web.entities.Credential;
import com.epam.web.entities.User;
import com.epam.web.enums.UserRole;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.CredentialService;
import com.epam.web.service.UserService;
import com.epam.web.validator.CredentialsInfoValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignUpCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("mainLogger");

    private final UserService userService;
    private final CredentialService credentialService;
    private final CredentialsInfoValidator credentialsInfoValidator;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String REPEATED_PASSWORD = "repeatedPassword";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String ID = "id";
    private static final String ROLE = "role";

    private static final String LOGIN_EXISTS = "loginExists";
    private static final String PASSWORDS_DO_NOT_MATCH = "passwordsDoNotMatch";
    private static final String INVALID_CREDENTIALS = "invalidCredentials";

    private static final String USER_MAIN = "getFacultyListPage";
    private static final String SIGN_UP_PAGE = "/WEB-INF/view/sign-up.jsp";
    private static final String ERROR_PAGE = "error";

    private static final String USER_WAS_NOT_FOUND = "User with the following login wasn't found: ";

    public SignUpCommand(UserService userService, CredentialService credentialService,
                         CredentialsInfoValidator credentialsInfoValidator) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.credentialsInfoValidator = credentialsInfoValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String repeatedPassword = request.getParameter(REPEATED_PASSWORD);

        Optional<User> optionalUser = userService.findByLogin(login);
        if (optionalUser.isPresent()) {
            request.setAttribute(LOGIN_EXISTS, true);
            return CommandResult.forward(SIGN_UP_PAGE);
        }

        if (!password.equals(repeatedPassword)) {
            request.setAttribute(PASSWORDS_DO_NOT_MATCH, true);
            return CommandResult.forward(SIGN_UP_PAGE);
        }

        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        if (!credentialsInfoValidator.validateCredential(name) || !credentialsInfoValidator.validateCredential(surname)) {
            request.setAttribute(INVALID_CREDENTIALS, true);
            return CommandResult.forward(SIGN_UP_PAGE);
        }

        boolean isBlocked = false;
        User user = new User(null, login, password, UserRole.ABITURIENT, isBlocked);
        userService.save(user);

        Optional<User> optionalSavedUser = userService.findByLogin(login);
        long userId = 0;
        if (optionalSavedUser.isPresent()) {
            User savedUser = optionalSavedUser.get();
            userId = savedUser.getId();
        } else {
            LOGGER.info(USER_WAS_NOT_FOUND + login);
            return CommandResult.redirect(ERROR_PAGE);
        }

        Credential credential = new Credential(null, userId, name, surname);
        credentialService.save(credential);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(ID, userId);
        httpSession.setAttribute(ROLE, UserRole.ABITURIENT);

        return CommandResult.redirect(USER_MAIN);
    }
}
