package com.epam.web.command;

import com.epam.web.entities.Application;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.ApplicationService;
import com.epam.web.service.CredentialService;
import com.epam.web.service.RegisterService;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class DeleteUserCommand implements Command {

    private final UserService userService;
    private final CredentialService credentialService;
    private final ApplicationService applicationService;
    private final RegisterService registerService;

    private static final String ID = "id";
    private static final String GET_ADMIN_MAIN = "getAdminMainPage";

    public DeleteUserCommand(UserService userService, CredentialService credentialService,
                             ApplicationService applicationService, RegisterService registerService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.applicationService = applicationService;
        this.registerService = registerService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String userIdString = request.getParameter(ID);
        long userId = Long.parseLong(userIdString);

        Optional<Application> optionalApplication = applicationService.findByUserId(userId);
        if (optionalApplication.isPresent()) {
            Application application = optionalApplication.get();
            Long applicationId = application.getId();

            registerService.deleteByApplicationId(applicationId);
        }
        applicationService.deleteByUserId(userId);
        credentialService.deleteByUserId(userId);
        userService.deleteById(userId);

        return CommandResult.redirect(GET_ADMIN_MAIN);
    }
}
