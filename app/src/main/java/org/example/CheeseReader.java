package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CheeseReader {
    public CheeseList read(File inputFile) throws IOException {
        CheeseList cheeses = new CheeseList();
        CheeseParser parser = new CheeseParser();
        try (BufferedReader input = Files.newBufferedReader(inputFile.toPath(), StandardCharsets.UTF_8)) {
            input.readLine(); // skip header
            String line;
            while ((line = input.readLine()) != null) {
                if (!line.isBlank()) {
                    cheeses.add(parser.parse(line));
                }
            }
        }
        return cheeses;
    }
}
