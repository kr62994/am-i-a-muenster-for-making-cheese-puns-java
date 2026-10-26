package org.example;

import java.util.ArrayList;

public class CheeseRowCleaner {
    public void clean(ArrayList<String> columns) {
        for (int i = 0; i < columns.size(); i++) {
            columns.set(i, columns.get(i).trim());
        }
    }
}
