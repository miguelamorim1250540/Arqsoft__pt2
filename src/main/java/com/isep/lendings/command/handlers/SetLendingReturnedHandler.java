package com.isep.lendings.command.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.isep.lendings.command.handlers.dto.SetLendingReturnedRequest;
import com.isep.lendings.command.model.Lending;
import com.isep.lendings.command.services.LendingServiceImpl;


@Component
@RequiredArgsConstructor
public class SetLendingReturnedHandler {

    private final LendingServiceImpl commandService;

    public Lending handle(String lendingNumber, long version, SetLendingReturnedRequest request) {
        return commandService.setReturned(lendingNumber, request, version);
    }
}
