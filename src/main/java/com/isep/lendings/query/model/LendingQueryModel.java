package com.isep.lendings.query.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "lendings")
public class LendingQueryModel {

    @Id
    private String id;

    private String lendingNumber;

    private String bookId;
    private String bookTitle;

    private String readerId;
    private String readerNumber;

    private LocalDate startDate;
    private LocalDate limitDate;
    private LocalDate returnedDate;

    private Integer daysUntilReturn;
    private Integer daysOverdue;
    private Integer fineValueInCents;

    private boolean returned;
    private boolean overdue;

    private Integer rating;
    private String commentary;
}
