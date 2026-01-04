package com.isep.lendings.command.event;

import java.time.LocalDate;

public record LendingReturnedEvent(
        String lendingNumber,
        LocalDate returnedDate,
        int fineValueInCents
) {}
