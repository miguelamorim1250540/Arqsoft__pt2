package com.isep.lendings.query.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchLendingQuery {
    private String readerNumber;
    private String isbn;
    private Boolean returned;
    private String startDate; // YYYY-MM-DD
    private String endDate;   // YYYY-MM-DD
}
