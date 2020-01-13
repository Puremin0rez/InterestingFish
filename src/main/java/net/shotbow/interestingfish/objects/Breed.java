package net.shotbow.interestingfish.objects;

import java.io.Serializable;

/**
 * User: Austin
 * Date: 5/18/13
 * Time: 1:06 AM
 * (c) lazertester
 */
public class Breed implements Serializable {

    private static final long serialVersionUID = 1;

    private String text;
    private double minWeightModifier;
    private double maxWeightModifier;
    private int rollWeight;

    public String getText() {
        return text;
    }

    public Breed setText(String text) {
        this.text = text;
        return this;
    }

    public double getMinWeightModifier() {
        return minWeightModifier;
    }

    public Breed setMinWeightModifier(double minWeightModifier) {
        this.minWeightModifier = minWeightModifier;
        return this;
    }

    public double getMaxWeightModifier() {
        return maxWeightModifier;
    }

    public Breed setMaxWeightModifier(double maxWeightModifier) {
        this.maxWeightModifier = maxWeightModifier;
        return this;
    }

    public int getRollWeight() {
        return rollWeight;
    }

    public Breed setRollWeight(int rollWeight) {
        this.rollWeight = rollWeight;
        return this;
    }
}
