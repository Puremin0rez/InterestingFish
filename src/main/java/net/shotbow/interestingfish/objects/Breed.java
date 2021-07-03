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

    public void setText(String text) {
        this.text = text;
    }

    public double getMinWeightModifier() {
        return minWeightModifier;
    }

    public void setMinWeightModifier(double minWeightModifier) {
        this.minWeightModifier = minWeightModifier;
    }

    public double getMaxWeightModifier() {
        return maxWeightModifier;
    }

    public void setMaxWeightModifier(double maxWeightModifier) {
        this.maxWeightModifier = maxWeightModifier;
    }

    public int getRollWeight() {
        return rollWeight;
    }

    public void setRollWeight(int rollWeight) {
        this.rollWeight = rollWeight;
    }
}
