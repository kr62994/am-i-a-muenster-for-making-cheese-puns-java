package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheeseRowCleanerTest {
    @Test
    void cleansEmptyRow() {
        ArrayList<String> columns = new ArrayList<>();
        new CheeseRowCleaner().clean(columns);
        assertTrue(columns.isEmpty());
    }

    @Test
    void trimsFieldsAndPreservesEmptyColumns() {
        ArrayList<String> columns = new ArrayList<>(
                Arrays.asList(" Cow ", " ", "", "\t41.0\t", "Raw Milk"));
        new CheeseRowCleaner().clean(columns);
        assertEquals(Arrays.asList("Cow", "", "", "41.0", "Raw Milk"), columns);
    }
}
