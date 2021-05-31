package com.epam.web.command;

import com.epam.web.entities.Application;
import com.epam.web.entities.Register;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.ApplicationService;
import com.epam.web.service.RegisterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ApproveDeclineApplicationCommand implements Command {

    private final RegisterService registerService;
    private final ApplicationService applicationService;

    private static final String ID = "id";
    private static final String APPROVE = "approve";
    private static final String APPLICATIONS_PAGE = "/WEB-INF/view/applications.jsp";

    public ApproveDeclineApplicationCommand(RegisterService registerService, ApplicationService applicationService) {
        this.registerService = registerService;
        this.applicationService = applicationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String applicationIdString = request.getParameter(ID);
        long applicationId = Long.parseLong(applicationIdString);
        String approve = request.getParameter(APPROVE);
        Boolean isApproved = Boolean.parseBoolean(approve);

        Optional<Application> optionalApplication = applicationService.findById(applicationId);
        if (optionalApplication.isPresent()) {
            Application application = optionalApplication.get();
            Long userId = application.getUserId();

            Register register = new Register(null, userId, applicationId, isApproved);
            registerService.save(register);
        }

        return CommandResult.forward(APPLICATIONS_PAGE);
    }
}
