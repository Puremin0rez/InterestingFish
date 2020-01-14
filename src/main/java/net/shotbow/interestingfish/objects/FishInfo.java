package net.shotbow.interestingfish.objects;

/**
 * User: Austin
 * Date: 5/18/13
 * Time: 1:27 AM
 * (c) lazertester
 */
public class FishInfo
{
    private String name;
    private double weight;

    public FishInfo(String name, double weight)
    {
        this.name = name;
        this.weight = weight;
    }

    public String getName()
    {
        return name;
    }

    public double getWeight()
    {
        return weight;
    }
}
