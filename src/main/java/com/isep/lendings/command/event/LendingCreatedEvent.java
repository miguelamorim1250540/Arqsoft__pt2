package com.isep.lendings.command.event;

import java.time.LocalDate;

public record LendingCreatedEvent(
        String lendingNumber,
        String bookId,
        String readerDetailsId,
        LocalDate startDate,
        LocalDate limitDate
) {}
