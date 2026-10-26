package org.example;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheeseListTest {
    @Test
    void startsEmpty() {
        assertTrue(new CheeseList().getCheeses().isEmpty());
    }

    @Test
    void storesOneCheese() {
        CheeseList cheeses = new CheeseList();
        Cheese cheese = new Cheese(47.0, true, "Cow", "Raw Milk");
        cheeses.add(cheese);
        assertEquals(1, cheeses.getCheeses().size());
        assertSame(cheese, cheeses.getCheeses().get(0));
    }

    @Test
    void preservesOrderAndDuplicates() {
        CheeseList cheeses = new CheeseList();
        Cheese first = new Cheese(47.0, true, "Cow", "Raw Milk");
        Cheese second = new Cheese(50.0, false, "Goat", "Pasteurized");
        cheeses.add(first);
        cheeses.add(second);
        cheeses.add(first);
        assertEquals(Arrays.asList(first, second, first), cheeses.getCheeses());
    }
}
