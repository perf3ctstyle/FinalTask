package com.epam.web.command;

import com.epam.web.entities.Faculty;
import com.epam.web.service.FacultyService;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllFacultiesCommand implements Command {

    private final FacultyService facultyService;

    public ShowAllFacultiesCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Faculty> facultyList = facultyService.findNumberOfFaculties((page-1)*recordsPerPage, recordsPerPage);
        int numberOfRecords = facultyService.countFaculties();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("facultyList", facultyList);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        return CommandResult.forward("/WEB-INF/view/main.jsp");
    }
}
