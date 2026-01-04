package com.isep.lendings.command.handlers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLendingCommand {

    @NotNull
    @NotBlank
    @Size(min = 10, max = 13)
    private String isbn;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 16)
    private String readerNumber;
}
