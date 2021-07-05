package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.service.*;
import com.epam.web.validator.ApplicationInfoValidator;
import com.epam.web.validator.CredentialsInfoValidator;

public class CommandFactory {

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

    private static final String INDEX_PAGE = "/index.jsp";
    private static final String SIGN_UP_PAGE = "/WEB-INF/view/sign-up.jsp";
    private static final String FACULTY_PAGE = "/WEB-INF/view/faculty.jsp";
    private static final String APPLY_PAGE = "/WEB-INF/view/apply.jsp";
    private static final String ABITURIENT_PAGE = "/WEB-INF/view/abiturient.jsp";
    private static final String APPLICATION_CHANGE_PAGE = "/WEB-INF/view/changeApplication.jsp";
    private static final String ERROR_PAGE = "/error.jsp";

    private static final String UNKNOWN_COMMAND = "Unknown command exception!";

    public Command create(String type) {
        switch (type) {
            case LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case GET_LOGIN:
                return new ShowPageCommand(INDEX_PAGE);
            case SIGN_UP:
                return new SignUpCommand(new UserService(new DaoHelperFactory()),
                        new CredentialService(new DaoHelperFactory()), new CredentialsInfoValidator());
            case GET_SIGN_UP:
                return new ShowPageCommand(SIGN_UP_PAGE);
            case LOGOUT:
                return new LogoutCommand();
            case GET_FACULTY_LIST:
                return new GetFacultyListByPageCommand(new FacultyService(new DaoHelperFactory()));
            case GET_ADMIN_MAIN:
                return new GetAdminMainPageInfoCommand(new UserService(new DaoHelperFactory()),
                        new CredentialService(new DaoHelperFactory()));
            case GET_FACULTY_PAGE:
                return new GetFacultyCommand(new FacultyService(new DaoHelperFactory()));
            case SHOW_FACULTY_PAGE:
                return new ShowPageCommand(FACULTY_PAGE);
            case GET_APPLICATION_DATA:
                return new GetApplicationDataCommand(new FacultyService(new DaoHelperFactory()));
            case SHOW_APPLY_PAGE:
                return new ShowPageCommand(APPLY_PAGE);
            case SAVE_APPLICATION:
                return new SaveApplicationCommand(new ApplicationService(new DaoHelperFactory()),
                        new FacultyService(new DaoHelperFactory()), new ApplicationInfoValidator());
            case GET_APPLICATIONS_PAGE:
                return new GetApplicationsByPageCommand(new ApplicationService(new DaoHelperFactory()),
                        new CredentialService(new DaoHelperFactory()),
                        new RegisterService(new DaoHelperFactory()));
            case UPDATE_APPLICATION:
                return new UpdateApplicationCommand(new ApplicationService(new DaoHelperFactory()),
                        new FacultyService(new DaoHelperFactory()), new ApplicationInfoValidator());
            case APPROVE_APPLICATION:
                return new ApproveApplicationCommand(new RegisterService(new DaoHelperFactory()),
                        new ApplicationService(new DaoHelperFactory()));
            case GET_ABITURIENT_INFO:
                return new GetAbiturientInfoCommand(new ApplicationService(new DaoHelperFactory()),
                        new FacultyService(new DaoHelperFactory()));
            case SHOW_ABITURIENT_INFO:
                return new ShowPageCommand(ABITURIENT_PAGE);
            case SHOW_APPLICATION_CHANGE:
                return new ShowPageCommand(APPLICATION_CHANGE_PAGE);
            case GET_REGISTERS_PAGE:
                return new GetRegistersByPageCommand(new ApplicationService(new DaoHelperFactory()),
                        new CredentialService(new DaoHelperFactory()),
                        new RegisterService(new DaoHelperFactory()));
            case DELETE_REGISTER:
                return new DeleteRegisterCommand(new RegisterService(new DaoHelperFactory()));
            case SAVE_ADMITTED_ABITURIENTS:
                return new SaveAdmittedAbiturientsFromRegisterCommand(new ApplicationService(new DaoHelperFactory()),
                        new RegisterService(new DaoHelperFactory()),
                        new AdmittedAbiturientService(new DaoHelperFactory()),
                        new FacultyService(new DaoHelperFactory()));
            case GET_ADMITTED_ABITURIENTS:
                return new GetAdmittedAbiturietsByPageCommand(new AdmittedAbiturientService(new DaoHelperFactory()),
                        new FacultyService(new DaoHelperFactory()),
                        new CredentialService(new DaoHelperFactory()));
            case CHANGE_IS_USER_BLOCKED:
                return new ChangeIsUserBlockedCommand(new UserService(new DaoHelperFactory()));
            case DELETE_USER:
                return new DeleteUserCommand(new UserService(new DaoHelperFactory()));
            case ERROR:
                return new ShowPageCommand(ERROR_PAGE);
            default:
                throw new IllegalArgumentException(UNKNOWN_COMMAND);
        }
    }
}
