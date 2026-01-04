package com.isep.lendings.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetLendingReturnedRequest {
    private String commentary;
    private Integer rating;
}
