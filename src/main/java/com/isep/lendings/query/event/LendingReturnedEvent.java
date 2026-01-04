package com.isep.lendings.query.event;

import java.time.LocalDate;

public record LendingReturnedEvent(
        String lendingNumber,
        LocalDate returnedDate,
        int fineValueInCents
) {}
