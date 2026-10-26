package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CheeseWriter {
    public void write(File outputFile, CheeseAnalyzer analyzer) throws IOException {
        try (PrintWriter output = new PrintWriter(outputFile, StandardCharsets.UTF_8)) {
            output.println("Cheeses using pasteurized milk: " + analyzer.countMilkTreatment("Pasteurized"));
            output.println("Cheeses using raw milk: " + analyzer.countMilkTreatment("Raw Milk"));
            output.println("Organic cheeses with moisture greater than 41.0%: "
                    + analyzer.countOrganicHighMoisture());
            output.println("Most common animal milk: " + analyzer.mostCommonMilk());
        }
    }
}
