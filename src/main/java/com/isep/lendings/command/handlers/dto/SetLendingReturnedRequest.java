package com.isep.lendings.command.handlers.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetLendingReturnedRequest {

    @Size(max = 1024)
    private String commentary;

    private Integer rating;
}
