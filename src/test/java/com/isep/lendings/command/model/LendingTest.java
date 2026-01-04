package com.isep.lendings.command.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LendingTest {

    @Test
    void testDaysDelayed_whenReturnedOnTime() {
        Lending lending = new Lending("book1", "reader1", "2026/1", 10, 100);
        lending.setReturnedDate(lending.getLimitDate());
        assertEquals(0, lending.getDaysDelayed(), "No delay expected");
    }

    @Test
    void testDaysDelayed_whenReturnedLate() {
        Lending lending = new Lending("book1", "reader1", "2026/2", 10, 100);
        lending.setReturnedDate(lending.getLimitDate().plusDays(3));
        assertEquals(3, lending.getDaysDelayed(), "3 days delay expected");
    }

    @Test
    void testSetReturned_whenAlreadyReturned_thenThrows() {

        Lending lending = new Lending("book1", "reader1", "2026/3", 10, 100);

        lending.setReturned(0L, "ok");

        Exception exception = assertThrows(
                IllegalStateException.class,
                () -> lending.setReturned(0L, "ok2")
        );

        assertTrue(exception.getMessage().contains("Book already returned"));
    }
}

class FineCommandTest {

    @Test
    void testFineCalculation() {
        Lending lending = new Lending("book1", "reader1", "2026/4", 5, 100);
        lending.setReturnedDate(lending.getLimitDate().plusDays(2));
        FineCommand fine = new FineCommand(lending);

        assertEquals(2, lending.getDaysDelayed());
        assertEquals(200, fine.getCentsValue());
    }

    @Test
    void testFineValuePerDayCopied() {
        Lending lending = new Lending("book1", "reader1", "2026/5", 5, 150);
        FineCommand fine = new FineCommand(lending);
        assertEquals(150, fine.getFineValuePerDayInCents());
    }
}
