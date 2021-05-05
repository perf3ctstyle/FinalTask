package com.epam.web.command;

import com.epam.web.entities.Faculty;
import com.epam.web.entities.Subject;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.FacultyService;
import com.epam.web.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApplyPageCommand implements Command {

    private final FacultyService facultyService;
    private final SubjectService subjectService;

    public ApplyPageCommand(FacultyService facultyService, SubjectService subjectService) {
        this.facultyService = facultyService;
        this.subjectService = subjectService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int numberOfFaculties = facultyService.countFaculties();
        List<Faculty> facultyList = facultyService.findNumberOfFaculties(0, numberOfFaculties);
        List<Subject> subjectList = subjectService.findAll();
        request.setAttribute("facultyList", facultyList);
        request.setAttribute("subjectList", subjectList);
        return CommandResult.forward("/WEB-INF/view/apply.jsp");
    }
}
