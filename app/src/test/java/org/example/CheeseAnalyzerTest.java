package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheeseAnalyzerTest {
    @Test
    void zeroCheeses() {
        CheeseAnalyzer analyzer = new CheeseAnalyzer(new CheeseList());
        assertEquals(0, analyzer.countMilkTreatment("Pasteurized"));
        assertEquals(0, analyzer.countMilkTreatment("Raw Milk"));
        assertEquals(0, analyzer.countOrganicHighMoisture());
        assertEquals("No data", analyzer.mostCommonMilk());
    }

    @Test
    void oneCheese() {
        CheeseList cheeses = new CheeseList();
        cheeses.add(new Cheese(47.0, true, "Ewe", "Raw Milk"));
        CheeseAnalyzer analyzer = new CheeseAnalyzer(cheeses);
        assertEquals(0, analyzer.countMilkTreatment("Pasteurized"));
        assertEquals(1, analyzer.countMilkTreatment("Raw Milk"));
        assertEquals(1, analyzer.countOrganicHighMoisture());
        assertEquals("Ewe", analyzer.mostCommonMilk());
    }

    @Test
    void manyCheeses() {
        CheeseList cheeses = new CheeseList();
        cheeses.add(new Cheese(47.0, true, "Cow", "Raw Milk"));
        cheeses.add(new Cheese(48.0, false, "Goat", "Pasteurized"));
        cheeses.add(new Cheese(52.0, true, "Goat", "Pasteurized"));
        CheeseAnalyzer analyzer = new CheeseAnalyzer(cheeses);
        assertEquals(2, analyzer.countMilkTreatment("Pasteurized"));
        assertEquals(1, analyzer.countMilkTreatment("Raw Milk"));
        assertEquals(2, analyzer.countOrganicHighMoisture());
        assertEquals("Goat", analyzer.mostCommonMilk());
    }

    @Test
    void moistureMustBeAboveBoundaryAndOrganic() {
        CheeseList cheeses = new CheeseList();
        cheeses.add(new Cheese(40.9, true, "", ""));
        cheeses.add(new Cheese(41.0, true, "", ""));
        cheeses.add(new Cheese(41.1, true, "", ""));
        cheeses.add(new Cheese(50.0, false, "", ""));
        cheeses.add(new Cheese(null, true, "", ""));
        assertEquals(1, new CheeseAnalyzer(cheeses).countOrganicHighMoisture());
    }

    @Test
    void missingAndMixedMilkDoNotCountAsSingleAnimalMilk() {
        CheeseList cheeses = new CheeseList();
        cheeses.add(new Cheese(null, false, "", ""));
        cheeses.add(new Cheese(null, false, "Cow and Goat", "Thermised"));
        CheeseAnalyzer analyzer = new CheeseAnalyzer(cheeses);
        assertEquals("No data", analyzer.mostCommonMilk());
        assertEquals(0, analyzer.countMilkTreatment("Pasteurized"));
        assertEquals(0, analyzer.countMilkTreatment("Raw Milk"));
    }

    @Test
    void buffaloCanBeMostCommon() {
        CheeseList cheeses = new CheeseList();
        cheeses.add(new Cheese(47.0, false, "Cow", "Pasteurized"));
        cheeses.add(new Cheese(47.0, false, "Buffalo", "Pasteurized"));
        cheeses.add(new Cheese(47.0, false, "Buffalo", "Pasteurized"));
        assertEquals("Buffalo", new CheeseAnalyzer(cheeses).mostCommonMilk());
    }
}
