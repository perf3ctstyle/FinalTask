package com.epam.web.command;

import com.epam.web.entities.Faculty;
import com.epam.web.service.FacultyService;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetFacultyListByPageCommand implements Command {

    private final FacultyService facultyService;

    private static final String PAGE = "page";
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 5;

    private static final String FACULTY_LIST = "facultyList";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private static final String ROLE = "role";
    private static final String ADMIN = "ADMIN";

    private static final String ADMIN_FACULTIES_PAGE = "/WEB-INF/view/faculties-admin.jsp";
    private static final String USER_FACULTIES_PAGE = "/WEB-INF/view/faculties-user.jsp";

    public GetFacultyListByPageCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int page = DEFAULT_CURRENT_PAGE;
        int recordsPerPage = DEFAULT_RECORDS_PER_PAGE;

        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }

        List<Faculty> facultyList = facultyService.findLimitedNumberOfFaculties((page-1)*recordsPerPage, recordsPerPage);

        int numberOfRecords = facultyService.countFaculties();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);

        request.setAttribute(FACULTY_LIST, facultyList);
        request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(CURRENT_PAGE, page);

        HttpSession httpSession = request.getSession();
        Object roleObject = httpSession.getAttribute(ROLE);
        String role = roleObject.toString();
        if (role.equals(ADMIN)) {
            return CommandResult.forward(ADMIN_FACULTIES_PAGE);
        } else {
            return CommandResult.forward(USER_FACULTIES_PAGE);
        }
    }
}
