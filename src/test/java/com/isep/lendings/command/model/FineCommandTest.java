// package com.isep.lendings.command.model;

// import org.junit.jupiter.api.Test;

// import java.time.LocalDate;

// import static org.junit.jupiter.api.Assertions.*;

// class FineCommandTest {

//     @Test
//     void fineCommandShouldCalculateCentsValueCorrectly() {
//         // Arrange: criar um Lending com atraso de 3 dias e fineValuePerDayInCents = 100
//         Lending lending = new Lending(
//                 "book-123",
//                 "reader-456",
//                 "2026/1",
//                 7,     // duração do empréstimo em dias
//                 100    // multa por dia em cents
//         );

//         // Simular atraso: returnedDate 3 dias depois do limitDate
//         lending.setReturnedDate(lending.getLimitDate().plusDays(3));

//         // Act
//         FineCommand fineCommand = new FineCommand(lending);

//         // Assert
//         assertEquals(100, fineCommand.getFineValuePerDayInCents());
//         assertEquals(300, fineCommand.getCentsValue()); // 100 * 3 dias de atraso
//         assertEquals(lending, fineCommand.getLending());
//         assertNotNull(fineCommand.getId()); // ID ainda pode ser null se não persistido
//     }

//     @Test
//     void fineCommandWithNoDelayShouldHaveZeroCentsValue() {
//         Lending lending = new Lending(
//                 "book-789",
//                 "reader-987",
//                 "2026/2",
//                 7,
//                 50
//         );

//         // returnedDate dentro do limite
//         lending.setReturnedDate(lending.getLimitDate());

//         FineCommand fineCommand = new FineCommand(lending);

//         assertEquals(0, fineCommand.getCentsValue());
//         assertEquals(50, fineCommand.getFineValuePerDayInCents());
//     }
// }
