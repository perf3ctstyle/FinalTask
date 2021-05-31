package com.epam.web.command;

import com.epam.web.entities.Credential;
import com.epam.web.entities.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.CredentialService;
import com.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class GetAdminMainPageInfoCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger("mainLogger");

    private final UserService userService;
    private final CredentialService credentialService;

    private static final String PAGE = "page";
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int DEFAULT_RECORDS_PER_PAGE = 5;

    private static final String CREDENTIAL_LIST = "credentialList";
    private static final String IS_USER_BLOCKED_LIST = "isUserBlockedList";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private static final String ADMIN_MAIN_PAGE = "/WEB-INF/view/users.jsp";

    private static final String USER_WAS_NOT_FOUND_BY_ID = "User with such id wasn't found: ";

    public GetAdminMainPageInfoCommand(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int page = DEFAULT_CURRENT_PAGE;
        int recordsPerPage = DEFAULT_RECORDS_PER_PAGE;

        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }

        List<Credential> receivedCredentialsList = credentialService.findLimitedNumberOfCredentials((page-1)*recordsPerPage, recordsPerPage);

        List<Boolean> isUserBlockedList = new ArrayList<>();
        List<Credential> credentialList = new ArrayList<>();
        for (Credential credential : receivedCredentialsList) {
            long userId = credential.getUserId();

            Optional<User> optionalUser = userService.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                Boolean isUserBlocked = user.isBlocked();

                isUserBlockedList.add(isUserBlocked);
                credentialList.add(credential);
            } else {
                LOGGER.info(USER_WAS_NOT_FOUND_BY_ID + userId);
            }
        }

        int numberOfRecords = credentialService.countCredentials();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);

        request.setAttribute(CREDENTIAL_LIST, credentialList);
        request.setAttribute(IS_USER_BLOCKED_LIST, isUserBlockedList);
        request.setAttribute(NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(CURRENT_PAGE, page);

        return CommandResult.forward(ADMIN_MAIN_PAGE);
    }
}
