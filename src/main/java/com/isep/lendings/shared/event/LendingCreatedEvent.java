package com.isep.lendings.shared.event;

import java.time.LocalDate;

public record LendingCreatedEvent(
        String lendingNumber,
        String bookId,
        String readerNumber,
        LocalDate startDate,
        LocalDate limitDate
) {}
