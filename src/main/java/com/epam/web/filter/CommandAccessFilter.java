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

    private static final String LOGIN = "login";
    private static final String INVALID_LOGIN = "invalidLogin";
    private static final String LOGOUT = "logout";
    private static final String GET_FACULTY_LIST = "getFacultyListPage";
    private static final String GET_ADMIN_MAIN = "getAdminMainPage";
    private static final String GET_FACULTY_PAGE = "getFacultyPage";
    private static final String SHOW_FACULTY_PAGE = "showFacultyPage";
    private static final String GET_APPLY_PAGE = "getApplyPage";
    private static final String SHOW_APPLY_PAGE = "showApplyPage";
    private static final String SAVE_APPLICATION = "saveApplication";
    private static final String GET_APPLICATIONS_PAGE = "getApplicationsPage";
    private static final String APPROVE_DECLINE_APPLICATION = "approveDeclineApplication";
    private static final String GET_ABITURIENT_INFO = "getAbiturientInfoPage";
    private static final String SHOW_ABITURIENT_INFO = "showAbiturientInfoPage";
    private static final String GET_REGISTERS_PAGE = "getRegistersPage";
    private static final String DELETE_REGISTER = "deleteRegister";
    private static final String GET_ADMITTED_ABITURIENTS = "getAdmittedAbiturientsPage";
    private static final String BLOCK_UNBLOCK_USER = "blockUnblockUser";
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
    public void init(FilterConfig filterConfig) throws ServletException {
        accessMap.put(UserRole.ABITURIENT, Arrays.asList(GET_FACULTY_PAGE,
                SHOW_FACULTY_PAGE, GET_APPLY_PAGE, SHOW_APPLY_PAGE, SAVE_APPLICATION));

        accessMap.put(UserRole.ADMIN, Arrays.asList(GET_ADMIN_MAIN, GET_APPLICATIONS_PAGE, APPROVE_DECLINE_APPLICATION,
                GET_ABITURIENT_INFO, SHOW_ABITURIENT_INFO, GET_REGISTERS_PAGE, DELETE_REGISTER, GET_ADMITTED_ABITURIENTS,
                BLOCK_UNBLOCK_USER, DELETE_USER));

        mutualCommands.addAll(Arrays.asList(INVALID_LOGIN, LOGOUT, GET_FACULTY_LIST, ERROR));
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
        if (command.equals(LOGIN)) {
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
