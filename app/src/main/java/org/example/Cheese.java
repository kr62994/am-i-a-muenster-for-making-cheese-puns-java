package org.example;

public class Cheese {
    private final Double moisturePercent;
    private final boolean organic;
    private final String milkType;
    private final String milkTreatment;

    public Cheese(Double moisturePercent, boolean organic, String milkType, String milkTreatment) {
        this.moisturePercent = moisturePercent;
        this.organic = organic;
        this.milkType = milkType;
        this.milkTreatment = milkTreatment;
    }

    public Double getMoisturePercent() {
        return moisturePercent;
    }

    public boolean isOrganic() {
        return organic;
    }

    public String getMilkType() {
        return milkType;
    }

    public String getMilkTreatment() {
        return milkTreatment;
    }
}
