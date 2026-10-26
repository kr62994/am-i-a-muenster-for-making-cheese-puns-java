package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheeseParserTest {
    @Test
    void parsesNormalRow() {
        Cheese cheese = new CheeseParser().parse(
                "228,NB,Farmstead,47.0,,,1,Firm Cheese,Ewe,Raw Milk,,,");
        assertEquals(47.0, cheese.getMoisturePercent());
        assertTrue(cheese.isOrganic());
        assertEquals("Ewe", cheese.getMilkType());
        assertEquals("Raw Milk", cheese.getMilkTreatment());
    }

    @Test
    void handlesQuotedCommasAndEscapedQuotes() {
        Cheese cheese = new CheeseParser().parse(
                "228,NB,Farmstead,47.0,\"Sharp, lactic\",\"Called \"\"cheddar\"\"\",1,Firm Cheese,Ewe,Raw Milk,,,");
        assertEquals(47.0, cheese.getMoisturePercent());
        assertTrue(cheese.isOrganic());
        assertEquals("Ewe", cheese.getMilkType());
        assertEquals("Raw Milk", cheese.getMilkTreatment());
    }

    @Test
    void cleansWhitespaceAndMissingFields() {
        Cheese cheese = new CheeseParser().parse(
                "242,NB,Farmstead, ,,,,Firm Cheese, Cow , Pasteurized ,,,");
        assertNull(cheese.getMoisturePercent());
        assertFalse(cheese.isOrganic());
        assertEquals("Cow", cheese.getMilkType());
        assertEquals("Pasteurized", cheese.getMilkTreatment());
    }
}
