package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheeseTest {
    @Test
    void storesCheeseValues() {
        Cheese cheese = new Cheese(47.0, true, "Ewe", "Raw Milk");
        assertEquals(47.0, cheese.getMoisturePercent());
        assertTrue(cheese.isOrganic());
        assertEquals("Ewe", cheese.getMilkType());
        assertEquals("Raw Milk", cheese.getMilkTreatment());
    }

    @Test
    void allowsMissingValuesAndNonorganicCheese() {
        Cheese cheese = new Cheese(null, false, "", "");
        assertNull(cheese.getMoisturePercent());
        assertFalse(cheese.isOrganic());
        assertEquals("", cheese.getMilkType());
        assertEquals("", cheese.getMilkTreatment());
    }
}
