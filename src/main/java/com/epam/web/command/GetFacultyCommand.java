package com.epam.web.command;

import com.epam.web.entities.Faculty;
import com.epam.web.service.FacultyService;
import com.epam.web.exception.ServiceException;
import org.apache.taglibs.standard.lang.jstl.test.PageContextImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class GetFacultyCommand implements Command {

    private final FacultyService facultyService;

    private static final String NAME = "name";
    private static final String FACULTY = "faculty";

    private static final String ERROR_PAGE = "error";

    private static final String SHOW_FACULTY_PAGE = "showFacultyPage";

    public GetFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String name = request.getParameter(NAME);

        Optional<Faculty> optionalFaculty = facultyService.findByName(name);

        HttpSession httpSession = request.getSession();

        if (optionalFaculty.isPresent()) {
            httpSession.setAttribute(FACULTY, optionalFaculty.get());
        } else {
            return CommandResult.redirect(ERROR_PAGE);
        }

        return CommandResult.redirect(SHOW_FACULTY_PAGE);
    }
}
