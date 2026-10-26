package org.example;

import java.util.ArrayList;

public class CheeseParser {
    public Cheese parse(String line) {
        ArrayList<String> columns = splitColumns(line);
        CheeseRowCleaner cleaner = new CheeseRowCleaner();
        cleaner.clean(columns);

        Double moisture = null;
        if (!columns.get(3).isEmpty()) {
            moisture = Double.parseDouble(columns.get(3));
        }
        boolean organic = columns.get(6).equals("1");
        return new Cheese(moisture, organic, columns.get(8), columns.get(9));
    }

    private ArrayList<String> splitColumns(String line) {
        ArrayList<String> columns = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == '"') {
                if (quoted && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    value.append('"');
                    i++;
                } else {
                    quoted = !quoted;
                }
            } else if (character == ',' && !quoted) {
                columns.add(value.toString());
                value.setLength(0);
            } else {
                value.append(character);
            }
        }
        columns.add(value.toString());
        return columns;
    }
}
