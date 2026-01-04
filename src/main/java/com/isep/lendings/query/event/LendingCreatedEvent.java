package com.isep.lendings.query.event;

import java.time.LocalDate;

public record LendingCreatedEvent(
        String lendingNumber,
        String bookId,
        String bookTitle,
        String readerId,
        String readerNumber,
        LocalDate startDate,
        LocalDate limitDate
) {}
