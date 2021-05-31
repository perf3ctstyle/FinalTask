package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.enums.DaoType;
import com.epam.web.service.*;
import com.epam.web.validator.ApplicationInfoValidator;

public class CommandFactory {

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

    private static final String INDEX_PAGE = "/index.jsp";
    private static final String FACULTY_PAGE = "/WEB-INF/view/faculty.jsp";
    private static final String APPLY_PAGE = "/WEB-INF/view/apply.jsp";
    private static final String ABITURIENT_PAGE = "/WEB-INF/view/abiturient.jsp";
    private static final String ERROR_PAGE = "/error.jsp";

    private static final String UNKNOWN_COMMAND = "Unknown command exception!";

    public Command create(String type) {
        switch (type) {
            case LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory(), DaoType.USER));
            case INVALID_LOGIN:
                return new ShowPageCommand(INDEX_PAGE);
            case LOGOUT:
                return new LogoutCommand();
            case GET_FACULTY_LIST:
                return new GetFacultyListByPageCommand(new FacultyService(new DaoHelperFactory(), DaoType.FACULTY));
            case GET_ADMIN_MAIN:
                return new GetAdminMainPageInfoCommand(new UserService(new DaoHelperFactory(), DaoType.USER),
                        new CredentialService(new DaoHelperFactory(), DaoType.CREDENTIAL));
            case GET_FACULTY_PAGE:
                return new GetFacultyCommand(new FacultyService(new DaoHelperFactory(), DaoType.FACULTY));
            case SHOW_FACULTY_PAGE:
                return new ShowPageCommand(FACULTY_PAGE);
            case GET_APPLY_PAGE:
                return new GetApplyPageInfoCommand(new FacultyService(new DaoHelperFactory(), DaoType.FACULTY));
            case SHOW_APPLY_PAGE:
                return new ShowPageCommand(APPLY_PAGE);
            case SAVE_APPLICATION:
                return new SaveApplicationCommand(new ApplicationService(new DaoHelperFactory(), DaoType.APPLICATION),
                        new FacultyService(new DaoHelperFactory(), DaoType.FACULTY), new ApplicationInfoValidator());
            case GET_APPLICATIONS_PAGE:
                return new GetApplicationsByPageCommand(new ApplicationService(new DaoHelperFactory(), DaoType.APPLICATION),
                        new CredentialService(new DaoHelperFactory(), DaoType.CREDENTIAL),
                        new RegisterService(new DaoHelperFactory(), DaoType.REGISTER));
            case APPROVE_DECLINE_APPLICATION:
                return new ApproveDeclineApplicationCommand(new RegisterService(new DaoHelperFactory(), DaoType.REGISTER),
                        new ApplicationService(new DaoHelperFactory(), DaoType.APPLICATION));
            case GET_ABITURIENT_INFO:
                return new GetAbiturientInfoCommand(new ApplicationService(new DaoHelperFactory(), DaoType.APPLICATION),
                        new FacultyService(new DaoHelperFactory(), DaoType.FACULTY));
            case SHOW_ABITURIENT_INFO:
                return new ShowPageCommand(ABITURIENT_PAGE);
            case GET_REGISTERS_PAGE:
                return new GetRegistersByPageCommand(new ApplicationService(new DaoHelperFactory(), DaoType.APPLICATION),
                        new CredentialService(new DaoHelperFactory(), DaoType.CREDENTIAL),
                        new RegisterService(new DaoHelperFactory(), DaoType.REGISTER));
            case DELETE_REGISTER:
                return new DeleteRegisterCommand(new RegisterService(new DaoHelperFactory(), DaoType.REGISTER));
            case GET_ADMITTED_ABITURIENTS:
                return new GetAdmittedAbiturietsByFacultyCommand();
            case BLOCK_UNBLOCK_USER:
                return new BlockUnblockUserCommand(new UserService(new DaoHelperFactory(), DaoType.USER));
            case DELETE_USER:
                return new DeleteUserCommand(new UserService(new DaoHelperFactory(), DaoType.USER),
                        new CredentialService(new DaoHelperFactory(), DaoType.CREDENTIAL),
                        new ApplicationService(new DaoHelperFactory(), DaoType.APPLICATION),
                        new RegisterService(new DaoHelperFactory(), DaoType.REGISTER));
            case ERROR:
                return new ShowPageCommand(ERROR_PAGE);
            default:
                throw new IllegalArgumentException(UNKNOWN_COMMAND);
        }
    }
}
