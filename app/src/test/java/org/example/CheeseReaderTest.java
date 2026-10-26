package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheeseReaderTest {
    @TempDir
    Path directory;

    private static final String HEADER = "CheeseId,ManufacturerProvCode,ManufacturingTypeEn,"
            + "MoisturePercent,FlavourEn,CharacteristicsEn,Organic,CategoryTypeEn,MilkTypeEn,"
            + "MilkTreatmentTypeEn,RindTypeEn,CheeseName,FatLevel\n";

    @Test
    void readsHeaderOnlyFile() throws IOException {
        Path input = directory.resolve("cheese.csv");
        Files.writeString(input, HEADER);
        assertTrue(new CheeseReader().read(input.toFile()).getCheeses().isEmpty());
    }

    @Test
    void readsOneRow() throws IOException {
        Path input = directory.resolve("cheese.csv");
        Files.writeString(input, HEADER
                + "228,NB,Farmstead,47.0,,,1,Firm Cheese,Ewe,Raw Milk,,,\n");
        CheeseList cheeses = new CheeseReader().read(input.toFile());
        assertEquals(1, cheeses.getCheeses().size());
        assertEquals("Ewe", cheeses.getCheeses().get(0).getMilkType());
        assertTrue(cheeses.getCheeses().get(0).isOrganic());
    }

    @Test
    void readsManyRowsWithIdGapsAndBlankLines() throws IOException {
        Path input = directory.resolve("cheese.csv");
        Files.writeString(input, HEADER
                + "228,NB,Farmstead,47.0,,,1,Firm Cheese,Cow,Raw Milk,,,\n\n"
                + "900,NB,Farmstead,48.0,,,0,Firm Cheese,Goat,Pasteurized,,,\n");
        CheeseList cheeses = new CheeseReader().read(input.toFile());
        assertEquals(2, cheeses.getCheeses().size());
        assertEquals("Cow", cheeses.getCheeses().get(0).getMilkType());
        assertEquals("Goat", cheeses.getCheeses().get(1).getMilkType());
    }

    @Test
    void missingInputFile() {
        assertThrows(IOException.class,
                () -> new CheeseReader().read(directory.resolve("missing.csv").toFile()));
    }
}
