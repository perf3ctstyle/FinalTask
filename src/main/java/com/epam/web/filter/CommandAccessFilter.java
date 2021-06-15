package com.epam.web.filter;

import com.epam.web.enums.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class CommandAccessFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger("mainLogger");

    private final Map<UserRole, List<String>> accessMap = new HashMap<>();
    private final List<String> mutualCommands = new ArrayList<>();
    private final List<String> identificationCommands = new ArrayList<>();

    private static final String LOGIN = "login";
    private static final String GET_LOGIN = "getLoginPage";
    private static final String SIGN_UP = "signUp";
    private static final String GET_SIGN_UP = "getSignUpPage";
    private static final String LOGOUT = "logout";
    private static final String GET_FACULTY_LIST = "getFacultyListPage";
    private static final String GET_ADMIN_MAIN = "getAdminMainPage";
    private static final String GET_FACULTY_PAGE = "getFacultyPage";
    private static final String SHOW_FACULTY_PAGE = "showFacultyPage";
    private static final String GET_APPLICATION_DATA = "getApplicationData";
    private static final String SHOW_APPLY_PAGE = "showApplyPage";
    private static final String SAVE_APPLICATION = "saveApplication";
    private static final String GET_APPLICATIONS_PAGE = "getApplicationsPage";
    private static final String UPDATE_APPLICATION = "updateApplication";
    private static final String APPROVE_APPLICATION = "approveApplication";
    private static final String GET_ABITURIENT_INFO = "getAbiturientInfoPage";
    private static final String SHOW_ABITURIENT_INFO = "showAbiturientInfoPage";
    private static final String SHOW_APPLICATION_CHANGE = "showApplicationChangePage";
    private static final String GET_REGISTERS_PAGE = "getRegistersPage";
    private static final String DELETE_REGISTER = "deleteRegister";
    private static final String SAVE_ADMITTED_ABITURIENTS = "saveAdmittedAbiturients";
    private static final String GET_ADMITTED_ABITURIENTS = "getAdmittedAbiturientsPage";
    private static final String CHANGE_IS_USER_BLOCKED = "changeIsUserBlocked";
    private static final String DELETE_USER = "deleteUser";
    private static final String ERROR = "error";

    private static final String ABITURIENT = "ABITURIENT";
    private static final String ADMIN = "ADMIN";

    private static final String LANGUAGE_LOCALE = "lang";
    private static final String LANGUAGE_BUNDLE = "language";

    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_MESSAGE_AUTHORIZATION = "local.error.authorization";
    private static final String ERROR_MESSAGE_PERMISSION = "local.error.permission";
    private static final String ERROR_PAGE = "error.jsp";

    private static final String ID = "id";
    private static final String ROLE = "role";

    private static final String COMMAND = "command";

    private static final String UNKNOWN_USER_ROLE = "Unknown user role!";

    @Override
    public void init(FilterConfig filterConfig) {
        accessMap.put(UserRole.ABITURIENT, Arrays.asList(GET_FACULTY_PAGE,
                SHOW_FACULTY_PAGE, GET_APPLICATION_DATA, SHOW_APPLY_PAGE, SAVE_APPLICATION));

        accessMap.put(UserRole.ADMIN, Arrays.asList(GET_ADMIN_MAIN, GET_APPLICATIONS_PAGE, APPROVE_APPLICATION,
                GET_APPLICATION_DATA, SHOW_APPLICATION_CHANGE, UPDATE_APPLICATION, GET_ABITURIENT_INFO, SHOW_ABITURIENT_INFO,
                GET_REGISTERS_PAGE, DELETE_REGISTER, SAVE_ADMITTED_ABITURIENTS, GET_ADMITTED_ABITURIENTS, DELETE_USER,
                CHANGE_IS_USER_BLOCKED));

        mutualCommands.addAll(Arrays.asList(LOGOUT, GET_FACULTY_LIST, ERROR));

        identificationCommands.addAll(Arrays.asList(LOGIN, GET_LOGIN, SIGN_UP, GET_SIGN_UP));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpRequest.getSession();

        Locale currentLocale;
        if (httpSession.getAttribute(LANGUAGE_LOCALE) != null) {
            Object languageObject = httpSession.getAttribute(LANGUAGE_LOCALE);
            String language = languageObject.toString();
            currentLocale = new Locale(language);
        } else {
            currentLocale = httpRequest.getLocale();
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle(LANGUAGE_BUNDLE, currentLocale);

        if (checkCommandAccess(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (httpSession.getAttribute(ID) == null) {
            String errorNotLoggedIn = resourceBundle.getString(ERROR_MESSAGE_AUTHORIZATION);
            servletRequest.setAttribute(ERROR_MESSAGE, errorNotLoggedIn);

            servletRequest.getRequestDispatcher(ERROR_PAGE).forward(servletRequest, servletResponse);
        } else {
            String errorNoPermission = resourceBundle.getString(ERROR_MESSAGE_PERMISSION);
            servletRequest.setAttribute(ERROR_MESSAGE, errorNoPermission);

            servletRequest.getRequestDispatcher(ERROR_PAGE).forward(servletRequest, servletResponse);
        }
    }

    private boolean checkCommandAccess(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String command = request.getParameter(COMMAND);
        if (identificationCommands.contains(command)) {
            return true;
        }

        HttpSession httpSession = httpRequest.getSession(false);
        if (httpSession == null) {
            return false;
        }

        UserRole userRole;
        String stringUserRole = String.valueOf(httpSession.getAttribute(ROLE));
        switch (stringUserRole) {
            case ABITURIENT:
                userRole = UserRole.ABITURIENT;
                break;
            case ADMIN:
                userRole = UserRole.ADMIN;
                break;
            default:
                LOGGER.info(UNKNOWN_USER_ROLE);
                return false;
        }

        return accessMap.get(userRole).contains(command) || mutualCommands.contains(command);
    }

    @Override
    public void destroy() {
    }
}
