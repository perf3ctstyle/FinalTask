package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.service.UserService;

public class CommandFactory {

    private static final String LOGIN = "login";
    private static final String INVALID_LOGIN = "invalidLogin";
    private static final String LOGOUT = "logout";
    private static final String MAIN = "main";
    private static final String FACULTY = "faculty";
    private static final String APPLY = "apply";
    private static final String APPLICATIONS = "applications";
    private static final String ABITURIENT = "abiturient";
    private static final String ADMISSION_REGISTRY = "admissionRegistry";

    private static final String INDEX_PAGE = "/WEB-INF/index.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String FACULTY_PAGE = "/WEB-INF/view/faculty.jsp";
    private static final String APPLY_PAGE = "/WEB-INF/view/apply.jsp";
    private static final String APPLICATIONS_PAGE = "/WEB-INF/view/applications.jsp";
    private static final String ABITURIENT_PAGE = "/WEB-INF/view/abiturient.jsp";
    private static final String ADMISSION_REGISTRY_PAGE = "/WEB-INF/view/admissionRegistry.jsp";

    public Command create(String type) {
        switch (type) {
            case LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case INVALID_LOGIN:
                return new ShowPageCommand(INDEX_PAGE);
            case LOGOUT:
                return new LogoutCommand();
            case MAIN:
                return new ShowPageCommand(MAIN_PAGE);
            case FACULTY:
                return new ShowPageCommand(FACULTY_PAGE);
            case APPLY:
                return new ShowPageCommand(APPLY_PAGE);
            case APPLICATIONS:
                return new ShowPageCommand(APPLICATIONS_PAGE);
            case ABITURIENT:
                return new ShowPageCommand(ABITURIENT_PAGE);
            case ADMISSION_REGISTRY:
                return new ShowPageCommand(ADMISSION_REGISTRY_PAGE);
            default:
                throw new IllegalArgumentException("Unknown command!");
        }
    }
}
