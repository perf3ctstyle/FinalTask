package com.epam.web.command;

import com.epam.web.entities.Application;
import com.epam.web.entities.Faculty;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.ApplicationService;
import com.epam.web.service.FacultyService;
import com.epam.web.validator.ApplicationInfoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UpdateApplicationCommand implements Command {

    private final ApplicationService applicationService;
    private final FacultyService facultyService;
    private final ApplicationInfoValidator applicationInfoValidator;

    private boolean isSelectedFacultyValid;
    private boolean isCertificateScoreValid;
    private boolean areSubjectsScoresValid;

    private static final String ABITURIENT_ID = "abiturientId";
    private static final String IS_SELECTED_FACULTY_VALID = "isSelectedFacultyValid";
    private static final String IS_CERTIFICATE_SCORE_VALID = "isCertificateScoreValid";
    private static final String ARE_SUBJECTS_SCORES_VALID = "areSubjectsScoresValid";
    private static final String FIRST_SUBJECT_SCORE_INPUT = "firstSubjectScoreInput";
    private static final String SECOND_SUBJECT_SCORE_INPUT = "secondSubjectScoreInput";
    private static final String THIRD_SUBJECT_SCORE_INPUT = "thirdSubjectScoreInput";
    private static final String CERTIFICATE = "certificate";
    private static final String FACULTY = "faculty";
    private static final String APPLICATION_CHANGED = "applicationChanged";

    private static final String CHANGE_APPLICATION_PAGE = "/WEB-INF/view/changeApplication.jsp";

    private static final int MAXIMUM_CERTIFICATE_SCORE = 100;
    private static final int MAXIMUM_SUBJECT_SCORE = 200;

    public UpdateApplicationCommand(ApplicationService applicationService, FacultyService facultyService,
                                  ApplicationInfoValidator applicationInfoValidator) {
        this.applicationService = applicationService;
        this.facultyService = facultyService;
        this.applicationInfoValidator = applicationInfoValidator;
        isSelectedFacultyValid = true;
        isCertificateScoreValid = true;
        areSubjectsScoresValid = true;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession httpSession = request.getSession();

        Object userIdObject = httpSession.getAttribute(ABITURIENT_ID);
        String userIdString = userIdObject.toString();
        long userId = Long.parseLong(userIdString);

        Long facultyId = getSelectedFacultyId(request);
        if (!isSelectedFacultyValid) {
            request.setAttribute(IS_SELECTED_FACULTY_VALID, false);
            return CommandResult.forward(CHANGE_APPLICATION_PAGE);
        }

        Integer certificateScore = getCertificateScore(request);
        if (!isCertificateScoreValid) {
            request.setAttribute(IS_CERTIFICATE_SCORE_VALID, false);
            return CommandResult.forward(CHANGE_APPLICATION_PAGE);
        }

        List<Integer> subjectsScores = getSubjectsScores(request);
        if (!areSubjectsScoresValid) {
            request.setAttribute(ARE_SUBJECTS_SCORES_VALID, false);
            return CommandResult.forward(CHANGE_APPLICATION_PAGE);
        }

        Optional<Application> optionalApplicationToUpdate = applicationService.findByUserId(userId);
        long applicationId = 0L;
        if (optionalApplicationToUpdate.isPresent()) {
            Application applicationToUpdate = optionalApplicationToUpdate.get();
            applicationId = applicationToUpdate.getId();
        }

        Application updatedApplication = new Application(applicationId, userId, facultyId, certificateScore,
                subjectsScores);

        applicationService.save(updatedApplication);

        request.setAttribute(APPLICATION_CHANGED, true);

        return CommandResult.forward(CHANGE_APPLICATION_PAGE);
    }

    private List<Integer> getSubjectsScores(HttpServletRequest request) {
        String firstSubjectScoreString = request.getParameter(FIRST_SUBJECT_SCORE_INPUT);
        String secondSubjectScoreString = request.getParameter(SECOND_SUBJECT_SCORE_INPUT);
        String thirdSubjectScoreString = request.getParameter(THIRD_SUBJECT_SCORE_INPUT);

        List<String> subjectsScoresStringList = Arrays.asList(firstSubjectScoreString, secondSubjectScoreString,
                thirdSubjectScoreString);

        for (String subjectScoreString : subjectsScoresStringList) {
            if (!applicationInfoValidator.validateScore(subjectScoreString, MAXIMUM_SUBJECT_SCORE)) {
                areSubjectsScoresValid = false;
                return new ArrayList<>(); // returning an empty list if one of the scores is invalid
            }
        }

        Integer firstSubjectScore = Integer.parseInt(firstSubjectScoreString);
        Integer secondSubjectScore = Integer.parseInt(secondSubjectScoreString);
        Integer thirdSubjectScore = Integer.parseInt(thirdSubjectScoreString);

        return Arrays.asList(firstSubjectScore, secondSubjectScore, thirdSubjectScore);
    }

    private Integer getCertificateScore(HttpServletRequest request) {
        String certificateScoreString = request.getParameter(CERTIFICATE);

        if (!applicationInfoValidator.validateScore(certificateScoreString, MAXIMUM_CERTIFICATE_SCORE)) {
            isCertificateScoreValid = false;
            return null; // returning null if the certificate score is invalid
        } else {
            return Integer.parseInt(certificateScoreString);
        }
    }

    private Long getSelectedFacultyId(HttpServletRequest request) throws ServiceException {
        String selectedFacultyName = request.getParameter(FACULTY);
        Optional<Faculty> selectedFaculty = facultyService.findByName(selectedFacultyName);

        Long facultyId;
        if (selectedFaculty.isPresent()) {
            Faculty faculty = selectedFaculty.get();
            facultyId = faculty.getId();
            return facultyId;
        } else {
            isSelectedFacultyValid = false;
            return null; // returning null if the selected faculty wasn't found, i.e. it is invalid
        }
    }
}
