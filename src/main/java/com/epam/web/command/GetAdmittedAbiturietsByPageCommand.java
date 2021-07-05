package com.epam.web.command;

import com.epam.web.entities.AdmittedAbiturient;
import com.epam.web.entities.Credential;
import com.epam.web.entities.Faculty;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetAdmittedAbiturietsByPageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("mainLogger");

    private final AdmittedAbiturientService admittedAbiturientService;
    private final FacultyService facultyService;
    private final CredentialService credentialService;

    private static final String PAGE = "page";
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 5;

    private static final String CREDENTIAL_LIST = "credentialList";
    private static final String FACULTY_NAME_LIST = "facultyNameList";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private static final String ROLE = "role";
    private static final String ABITURIENT = "ABITURIENT";

    private static final String ADMITTED_ABITURIENTS_ADMIN_PAGE = "/WEB-INF/view/admittedAbiturientsAdmin.jsp";
    private static final String ADMITTED_ABITURIENTS_USER_PAGE = "/WEB-INF/view/admittedAbiturientsUser.jsp";

    private static final String CREDENTIAL_WAS_NOT_FOUND_BY_USER_ID = "Credential with such userId wasn't found: ";
    private static final String FACULTY_WAS_NOT_FOUND_BY_ID = "Faculty with such id wasn't found: ";

    public GetAdmittedAbiturietsByPageCommand(AdmittedAbiturientService admittedAbiturientService,
                                              FacultyService facultyService, CredentialService credentialService) {
        this.admittedAbiturientService = admittedAbiturientService;
        this.facultyService = facultyService;
        this.credentialService = credentialService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int page = DEFAULT_CURRENT_PAGE;
        int recordsPerPage = DEFAULT_RECORDS_PER_PAGE;

        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }

        List<AdmittedAbiturient> receivedAdmittedAbiturientsList = admittedAbiturientService.findLimitedNumberOfAdmittedAbiturients((page-1)*recordsPerPage, recordsPerPage);

        List<Credential> credentialList = new ArrayList<>();
        List<String> facultyNameList = new ArrayList<>();
        for (AdmittedAbiturient admittedAbiturient : receivedAdmittedAbiturientsList) {
            long userId = admittedAbiturient.getUserId();
            long facultyId = admittedAbiturient.getFacultyId();

            Optional<Faculty> optionalFaculty = facultyService.findById(facultyId);
            if (optionalFaculty.isPresent()) {
                Faculty faculty = optionalFaculty.get();
                String facultyName = faculty.getName();

                facultyNameList.add(facultyName);
            } else {
                LOGGER.info(FACULTY_WAS_NOT_FOUND_BY_ID + facultyId);
                continue;
            }

            Optional<Credential> optionalCredential = credentialService.findByUserId(userId);
            if (optionalCredential.isPresent()) {
                Credential credential = optionalCredential.get();

                credentialList.add(credential);
            } else {
                LOGGER.info(CREDENTIAL_WAS_NOT_FOUND_BY_USER_ID + userId);
            }
        }

        int numberOfRecords = admittedAbiturientService.countAdmittedAbiturients();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);

        request.setAttribute(CREDENTIAL_LIST, credentialList);
        request.setAttribute(FACULTY_NAME_LIST, facultyNameList);
        request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(CURRENT_PAGE, page);

        HttpSession httpSession = request.getSession();
        String userRole = String.valueOf(httpSession.getAttribute(ROLE));
        if (userRole.equals(ABITURIENT)) {
            return CommandResult.forward(ADMITTED_ABITURIENTS_USER_PAGE);
        } else {
            return CommandResult.forward(ADMITTED_ABITURIENTS_ADMIN_PAGE);
        }
    }
}
