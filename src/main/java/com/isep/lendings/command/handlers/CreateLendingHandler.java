package com.isep.lendings.command.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.isep.lendings.command.handlers.dto.CreateLendingCommand;
import com.isep.lendings.command.model.Lending;
import com.isep.lendings.command.services.LendingServiceImpl;


@Component
@RequiredArgsConstructor
public class CreateLendingHandler {

    private final LendingServiceImpl commandService;

    public Lending handle(CreateLendingCommand command) {
        return commandService.create(command.getIsbn(), command.getReaderNumber());
    }
}
