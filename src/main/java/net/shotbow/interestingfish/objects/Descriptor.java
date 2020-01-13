package net.shotbow.interestingfish.objects;

import java.io.Serializable;

/**
 * User: Austin
 * Date: 5/18/13
 * Time: 1:06 AM
 * (c) lazertester
 */
public class Descriptor implements Serializable {

    private static final long serialVersionUID = 1;

    private String text;
    private double minWeightModifier;
    private double maxWeightModifier;
    private int rollWeight;

    public String getText() {
        return text;
    }

    public Descriptor setText(String text) {
        this.text = text;
        return this;
    }

    public double getMinWeightModifier() {
        return minWeightModifier;
    }

    public Descriptor setMinWeightModifier(double minWeightModifier) {
        this.minWeightModifier = minWeightModifier;
        return this;
    }

    public double getMaxWeightModifier() {
        return maxWeightModifier;
    }

    public Descriptor setMaxWeightModifier(double maxWeightModifier) {
        this.maxWeightModifier = maxWeightModifier;
        return this;
    }

    public int getRollWeight() {
        return rollWeight;
    }

    public Descriptor setRollWeight(int rollWeight) {
        this.rollWeight = rollWeight;
        return this;
    }
}
