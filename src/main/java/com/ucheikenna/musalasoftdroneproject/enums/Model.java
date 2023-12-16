package com.ucheikenna.musalasoftdroneproject.enums;

import lombok.Getter;

@Getter
public enum Model {

    LIGHT_WEIGHT (100.0),
    MIDDLE_WEIGHT (200.0),
    CRUISER_WEIGHT (350.0),
    HEAVY_WEIGHT (500.0);

    private final double maxWeight;

    Model (double maxWeight){
        this.maxWeight = maxWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
}
