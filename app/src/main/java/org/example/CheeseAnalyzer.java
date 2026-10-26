package org.example;

public class CheeseAnalyzer {
    private final CheeseList cheeses;

    public CheeseAnalyzer(CheeseList cheeses) {
        this.cheeses = cheeses;
    }

    public int countMilkTreatment(String treatment) {
        int count = 0;
        for (Cheese cheese : cheeses.getCheeses()) {
            if (cheese.getMilkTreatment().equals(treatment)) {
                count++;
            }
        }
        return count;
    }

    public int countOrganicHighMoisture() {
        int count = 0;
        for (Cheese cheese : cheeses.getCheeses()) {
            Double moisture = cheese.getMoisturePercent();
            if (cheese.isOrganic() && moisture != null && moisture > 41.0) {
                count++;
            }
        }
        return count;
    }

    public String mostCommonMilk() {
        String[] milkTypes = {"Cow", "Goat", "Ewe", "Buffalo"};
        int[] milkCounts = new int[milkTypes.length];
        for (Cheese cheese : cheeses.getCheeses()) {
            for (int i = 0; i < milkTypes.length; i++) {
                if (cheese.getMilkType().equals(milkTypes[i])) {
                    milkCounts[i]++;
                }
            }
        }

        int mostCommon = 0;
        for (int i = 1; i < milkCounts.length; i++) {
            if (milkCounts[i] > milkCounts[mostCommon]) {
                mostCommon = i;
            }
        }
        if (milkCounts[mostCommon] == 0) {
            return "No data";
        }
        return milkTypes[mostCommon];
    }
}
