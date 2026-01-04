package com.isep.lendings.query.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LendingQueryModelTest {

    @Test
    void testLendingQueryModelGettersAndSetters() {

        LendingQueryModel lending = new LendingQueryModel();

        lending.setId("123");
        lending.setLendingNumber("L001");
        lending.setBookId("B01");
        lending.setBookTitle("Clean Code");
        lending.setReaderId("R01");
        lending.setReaderNumber("1001");

        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(7);

        lending.setStartDate(today);
        lending.setLimitDate(limit);
        lending.setReturnedDate(null);

        lending.setDaysUntilReturn(5);
        lending.setDaysOverdue(2);
        lending.setFineValueInCents(500);

        lending.setReturned(true);
        lending.setOverdue(false);

        lending.setRating(4);
        lending.setCommentary("Great book!");

        assertEquals("123", lending.getId());
        assertEquals("L001", lending.getLendingNumber());
        assertEquals("B01", lending.getBookId());
        assertEquals("Clean Code", lending.getBookTitle());
        assertEquals("R01", lending.getReaderId());
        assertEquals("1001", lending.getReaderNumber());
        assertEquals(today, lending.getStartDate());
        assertEquals(limit, lending.getLimitDate());
        assertNull(lending.getReturnedDate());
        assertEquals(5, lending.getDaysUntilReturn());
        assertEquals(2, lending.getDaysOverdue());
        assertEquals(500, lending.getFineValueInCents());
        assertTrue(lending.isReturned());
        assertFalse(lending.isOverdue());
        assertEquals(4, lending.getRating());
        assertEquals("Great book!", lending.getCommentary());
    }

    @Test
    void testEqualsAndHashCode() {

        LendingQueryModel l1 = new LendingQueryModel();
        l1.setId("123");

        LendingQueryModel l2 = new LendingQueryModel();
        l2.setId("123");

        assertEquals(l1, l2);
        assertEquals(l1.hashCode(), l2.hashCode());
    }

    @Test
    void testDefaultBooleanValues() {

        LendingQueryModel lending = new LendingQueryModel();

        assertFalse(lending.isReturned());
        assertFalse(lending.isOverdue());
    }
}
