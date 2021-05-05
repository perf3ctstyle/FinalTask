package com.epam.web.command;

import com.epam.web.entities.Faculty;
import com.epam.web.service.FacultyService;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ShowFacultyPageCommand implements Command {

    private final FacultyService facultyService;

    public ShowFacultyPageCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter("id");
        long idValue = Long.parseLong(idString);

        Optional<Faculty> optionalFaculty = facultyService.findById(idValue);

        if (optionalFaculty.isPresent()) {
            request.setAttribute("faculty", optionalFaculty.get());
        } else {
            return CommandResult.redirect("error");
        }

        return CommandResult.redirect("/WEB-INF/view/faculty.jsp");
    }
}
