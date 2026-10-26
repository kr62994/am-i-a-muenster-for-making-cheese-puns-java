package org.example;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("cheese_data.csv");
        if (!inputFile.exists()) {
            inputFile = new File("../cheese_data.csv"); // current dir
        }
        File outputFile = new File(inputFile.getAbsoluteFile().getParentFile(), "output.txt"); // save to main dir

        CheeseReader reader = new CheeseReader();
        CheeseList cheeses = reader.read(inputFile);
        CheeseAnalyzer analyzer = new CheeseAnalyzer(cheeses);
        CheeseWriter writer = new CheeseWriter();
        writer.write(outputFile, analyzer);
    }
}
