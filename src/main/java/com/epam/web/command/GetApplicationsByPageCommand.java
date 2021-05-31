package com.epam.web.command;

import com.epam.web.entities.Application;
import com.epam.web.entities.Credential;
import com.epam.web.entities.Register;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.ApplicationService;
import com.epam.web.service.CredentialService;
import com.epam.web.service.RegisterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetApplicationsByPageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("mainLogger");

    private final ApplicationService applicationService;
    private final CredentialService credentialService;
    private final RegisterService registerService;

    private static final String PAGE = "page";
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 5;

    private static final String APPLICATION_ID_LIST = "applicationIdList";
    private static final String CREDENTIAL_LIST = "credentialList";
    private static final String IS_APPLICATION_REVIEWED_LIST = "isApplicationReviewedList";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private static final String APPLICATIONS_PAGE = "/WEB-INF/view/applications.jsp";

    private static final String CREDENTIAL_WAS_NOT_FOUND_BY_USER_ID = "Credential with such userId wasn't found: ";

    public GetApplicationsByPageCommand(ApplicationService applicationService, CredentialService credentialService,
                                        RegisterService registerService) {
        this.applicationService = applicationService;
        this.credentialService = credentialService;
        this.registerService = registerService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int page = DEFAULT_CURRENT_PAGE;
        int recordsPerPage = DEFAULT_RECORDS_PER_PAGE;

        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }

        List<Application> receivedApplicationsList = applicationService.findLimitedNumberOfApplications((page-1)*recordsPerPage, recordsPerPage);

        List<Long> applicationIdList = new ArrayList<>();
        List<Credential> credentialList = new ArrayList<>();
        List<Boolean> isApplicationReviewedList = new ArrayList<>();
        for (Application application : receivedApplicationsList) {
            long userId = application.getUserId();

            Optional<Credential> optionalCredential = credentialService.findByUserId(userId);
            if (optionalCredential.isPresent()) {
                Credential credential = optionalCredential.get();
                credentialList.add(credential);

                Boolean isCurrentApplicationReviewed = isApplicationReviewed(application);
                isApplicationReviewedList.add(isCurrentApplicationReviewed);

                long applicationId = application.getId();
                applicationIdList.add(applicationId);
            } else {
                LOGGER.info(CREDENTIAL_WAS_NOT_FOUND_BY_USER_ID + userId);
            }
        }

        int numberOfRecords = applicationService.countApplications();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);

        request.setAttribute(APPLICATION_ID_LIST, applicationIdList);
        request.setAttribute(CREDENTIAL_LIST, credentialList);
        request.setAttribute(IS_APPLICATION_REVIEWED_LIST, isApplicationReviewedList);
        request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(CURRENT_PAGE, page);

        return CommandResult.forward(APPLICATIONS_PAGE);
    }

    private Boolean isApplicationReviewed(Application application) throws ServiceException {
        Long applicationId = application.getId();
        Optional<Register> optionalRegister = registerService.findByApplicationId(applicationId);

        return optionalRegister.isPresent();
    }
}
