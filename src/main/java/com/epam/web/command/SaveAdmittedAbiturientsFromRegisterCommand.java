package com.epam.web.command;

import com.epam.web.entities.AdmittedAbiturient;
import com.epam.web.entities.Faculty;
import com.epam.web.entities.Register;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.AdmittedAbiturientService;
import com.epam.web.service.ApplicationService;
import com.epam.web.service.FacultyService;
import com.epam.web.service.RegisterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SaveAdmittedAbiturientsFromRegisterCommand implements Command {

    private final ApplicationService applicationService;
    private final RegisterService registerService;
    private final AdmittedAbiturientService admittedAbiturientService;
    private final FacultyService facultyService;

    private static final String APPLICATIONS_NOT_REVIEWED = "applicationsNotReviewed";
    private static final String REGISTERS_PAGE = "/WEB-INF/view/registers.jsp";
    private static final int OFFSET = 0;
    private static final String GET_REGISTERS_PAGE = "getRegistersPage";

    public SaveAdmittedAbiturientsFromRegisterCommand(ApplicationService applicationService,
                                                      RegisterService registerService,
                                                      AdmittedAbiturientService admittedAbiturientService,
                                                      FacultyService facultyService) {
        this.applicationService = applicationService;
        this.registerService = registerService;
        this.admittedAbiturientService = admittedAbiturientService;
        this.facultyService = facultyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int numberOfApplications = applicationService.countApplications();
        int numberOfRegisters = registerService.countRegisters();
        if (numberOfApplications != numberOfRegisters) {
            request.setAttribute(APPLICATIONS_NOT_REVIEWED, true);
            return CommandResult.forward(REGISTERS_PAGE);
        }

        int numberOfFaculties = facultyService.countFaculties();
        List<Faculty> facultyList = facultyService.findLimitedNumberOfFaculties(OFFSET, numberOfFaculties);

        for (Faculty faculty : facultyList) {
            long facultyId = faculty.getId();
            admittedAbiturientService.deleteByFacultyId(facultyId);

            int admissionPlan = faculty.getAdmissionPlan();
            List<Register> admittedAbiturientsRegistersForCurrentFaculty =
                    registerService.findAdmittedAbiturientsRegistersForFaculty(facultyId, admissionPlan);

            for (Register register : admittedAbiturientsRegistersForCurrentFaculty) {
                long userId = register.getUserId();

                AdmittedAbiturient admittedAbiturient = new AdmittedAbiturient(null, userId, facultyId);
                admittedAbiturientService.save(admittedAbiturient);
            }
        }

        return CommandResult.redirect(GET_REGISTERS_PAGE);
    }
}
