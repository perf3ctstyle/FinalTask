package com.epam.web.command;

import com.epam.web.entities.Application;
import com.epam.web.entities.Register;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.ApplicationService;
import com.epam.web.service.RegisterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ApproveApplicationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("mainLogger");

    private final RegisterService registerService;
    private final ApplicationService applicationService;

    private static final String ID = "id";
    private static final String APPROVE = "approve";
    private static final String GET_APPLICATIONS_PAGE = "getApplicationsPage";

    private static final String APPLICATION_WAS_NOT_FOUND = "Application with the following id wasn't found: ";
    private static final String ERROR_PAGE = "error";

    public ApproveApplicationCommand(RegisterService registerService, ApplicationService applicationService) {
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
            Long facultyId = application.getFacultyId();

            Integer certificateScore = application.getCertificateScore();
            List<Integer> subjectsScores = application.getSubjectsScores();
            Integer scoreSum = 0;
            for (Integer subjectScore : subjectsScores) {
                scoreSum += subjectScore;
            }
            scoreSum += certificateScore;

            Register register = new Register(null, userId, facultyId, scoreSum, isApproved);
            registerService.save(register);
        } else {
            LOGGER.info(APPLICATION_WAS_NOT_FOUND + applicationId);
            return CommandResult.redirect(ERROR_PAGE);
        }

        return CommandResult.redirect(GET_APPLICATIONS_PAGE);
    }
}
