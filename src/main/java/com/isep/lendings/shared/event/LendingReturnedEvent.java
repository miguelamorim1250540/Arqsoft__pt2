package com.isep.lendings.shared.event;

import java.time.LocalDate;

public record LendingReturnedEvent(
        String lendingNumber,
        LocalDate returnedDate,
        int fineValueInCents
) {}
