package com.epam.web.command;

import com.epam.web.entities.Faculty;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetApplicationDataCommand implements Command {

    private final FacultyService facultyService;

    private static final String SCORE_LENGTH_STRING = "scoreLength";
    private static final int SCORE_LENGTH_VALUE = 3;
    private static final String FACULTY_LIST = "facultyList";
    private static final int OFFSET = 0;

    private static final String ROLE = "role";
    private static final String ADMIN = "ADMIN";

    private static final String SHOW_APPLICATION_CHANGE = "showApplicationChangePage";
    private static final String SHOW_APPLY_PAGE = "showApplyPage";

    private static final String ERROR_PAGE = "error";

    public GetApplicationDataCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int numberOfFaculties = facultyService.countFaculties();
        List<Faculty> facultyList = facultyService.findLimitedNumberOfFaculties(OFFSET, numberOfFaculties);

        HttpSession httpSession = request.getSession();

        if (!facultyList.isEmpty()) {
            httpSession.setAttribute(SCORE_LENGTH_STRING, SCORE_LENGTH_VALUE);
            httpSession.setAttribute(FACULTY_LIST, facultyList);
        } else {
            return CommandResult.redirect(ERROR_PAGE);
        }

        Object roleObject = httpSession.getAttribute(ROLE);
        String role = roleObject.toString();

        if (role.equals(ADMIN)) {
            return CommandResult.redirect(SHOW_APPLICATION_CHANGE);
        } else {
            return CommandResult.redirect(SHOW_APPLY_PAGE);
        }
    }
}
