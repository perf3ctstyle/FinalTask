package com.epam.web.command;

import com.epam.web.entities.Application;
import com.epam.web.entities.Faculty;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.ApplicationService;
import com.epam.web.service.FacultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class GetAbiturientInfoCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("mainLogger");

    private final ApplicationService applicationService;
    private final FacultyService facultyService;

    private static final String ID = "id";
    private static final String ABITURIENT_ID = "abiturientId";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String SUBJECTS_SCORE_LIST = "subjectsScoreList";
    private static final String CERTIFICATE_SCORE = "certificateScore";
    private static final String FACULTY_NAME = "facultyName";

    private static final String ERROR_PAGE = "error";
    private static final String SHOW_ABITURIENT_INFO = "showAbiturientInfoPage";

    public GetAbiturientInfoCommand(ApplicationService applicationService, FacultyService facultyService) {
        this.applicationService = applicationService;
        this.facultyService = facultyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String userIdString = request.getParameter(ID);
        long userId = Long.parseLong(userIdString);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);

        Optional<Application> optionalApplication = applicationService.findByUserId(userId);

        if (optionalApplication.isPresent()) {
            Application application = optionalApplication.get();
            long facultyId = application.getFacultyId();

            Optional<Faculty> optionalFaculty = facultyService.findById(facultyId);
            if (optionalFaculty.isPresent()) {
                Faculty faculty = optionalFaculty.get();

                String facultyName = faculty.getName();
                List<Integer> subjectsScoreList = application.getSubjectsScores();
                Integer certificateScore = application.getCertificateScore();

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute(ABITURIENT_ID, userId);
                httpSession.setAttribute(NAME, name);
                httpSession.setAttribute(SURNAME, surname);
                httpSession.setAttribute(SUBJECTS_SCORE_LIST, subjectsScoreList);
                httpSession.setAttribute(CERTIFICATE_SCORE, certificateScore);
                httpSession.setAttribute(FACULTY_NAME, facultyName);
            } else {
                return CommandResult.redirect(ERROR_PAGE);
            }
        } else {
            return CommandResult.redirect(ERROR_PAGE);
        }

        return CommandResult.redirect(SHOW_ABITURIENT_INFO);
    }
}
