package com.epam.web.command;

import com.epam.web.exception.ServiceException;
import com.epam.web.service.RegisterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteRegisterCommand implements Command {

    private final RegisterService registerService;

    private static final String ID = "id";
    private static final String GET_REGISTERS_PAGE = "getRegistersPage";

    public DeleteRegisterCommand(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String registerIdString = request.getParameter(ID);
        long registerId = Long.parseLong(registerIdString);

        registerService.deleteById(registerId);

        return CommandResult.redirect(GET_REGISTERS_PAGE);
    }
}
