package com.isep.lendings.query.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FineQueryModelTest {

    @Test
    void testFineQueryModelGettersAndSetters() {

        FineQueryModel fine = new FineQueryModel();

        fine.setId(1L);
        fine.setLendingNumber("L001");
        fine.setFineValuePerDayInCents(50);
        fine.setTotalCentsValue(300);

        assertEquals(1L, fine.getId());
        assertEquals("L001", fine.getLendingNumber());
        assertEquals(50, fine.getFineValuePerDayInCents());
        assertEquals(300, fine.getTotalCentsValue());
    }

    @Test
    void testEqualsAndHashCode() {

        FineQueryModel fine1 = new FineQueryModel();
        fine1.setId(1L);

        FineQueryModel fine2 = new FineQueryModel();
        fine2.setId(1L);

        assertEquals(fine1, fine2);
        assertEquals(fine1.hashCode(), fine2.hashCode());
    }
}
