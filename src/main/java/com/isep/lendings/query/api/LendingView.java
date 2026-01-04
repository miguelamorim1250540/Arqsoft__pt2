package com.isep.lendings.query.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LendingView {
    private String lendingNumber;
    private String bookId;
    private String bookTitle;
    private String readerNumber;
    private LocalDate startDate;
    private LocalDate limitDate;
    private LocalDate returnedDate;
    private Integer daysUntilReturn;
    private Integer daysOverdue;
    private Integer fineInCents;
    private String commentary;
    private Integer rating;
}
