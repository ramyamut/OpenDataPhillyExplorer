package edu.upenn.cis350.hw2.processor;

import edu.upenn.cis350.hw2.data.Property;

//class that returns the area of a property as part of the strategy method
public class AreaTrait implements PropertyTrait {

    @Override
    public double value(Property p) {
        return p.getArea();
    }

}
