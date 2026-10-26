package org.example;

import java.util.Arrays;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheeseWriterTest {
    @TempDir
    Path directory;

    @Test
    void writesAllResultsAndReplacesPreviousContents() throws IOException {
        Path output = directory.resolve("output.txt");
        Files.writeString(output, "Old results that should be replaced");
        CheeseList cheeses = new CheeseList();
        cheeses.add(new Cheese(47.0, true, "Cow", "Raw Milk"));
        cheeses.add(new Cheese(41.0, true, "Cow", "Pasteurized"));
        new CheeseWriter().write(output.toFile(), new CheeseAnalyzer(cheeses));
        assertEquals(Arrays.asList(
                "Cheeses using pasteurized milk: 1",
                "Cheeses using raw milk: 1",
                "Organic cheeses with moisture greater than 41.0%: 1",
                "Most common animal milk: Cow"), Files.readAllLines(output));
    }

    @Test
    void createsOutputForEmptyList() throws IOException {
        Path output = directory.resolve("output.txt");
        new CheeseWriter().write(output.toFile(), new CheeseAnalyzer(new CheeseList()));
        assertEquals(Arrays.asList(
                "Cheeses using pasteurized milk: 0",
                "Cheeses using raw milk: 0",
                "Organic cheeses with moisture greater than 41.0%: 0",
                "Most common animal milk: No data"), Files.readAllLines(output));
    }

    @Test
    void missingOutputDirectory() {
        Path output = directory.resolve("missing").resolve("output.txt");
        assertThrows(IOException.class,
                () -> new CheeseWriter().write(output.toFile(), new CheeseAnalyzer(new CheeseList())));
    }
}
