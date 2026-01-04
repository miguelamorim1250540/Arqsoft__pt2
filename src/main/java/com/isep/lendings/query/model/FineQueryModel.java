package com.isep.lendings.query.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "fines")
public class FineQueryModel {

    @Id
    private Long id;

    private String lendingNumber;

    private int fineValuePerDayInCents;
    private int totalCentsValue;
}
