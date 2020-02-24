package edu.upenn.cis350.hw2.processor;

import edu.upenn.cis350.hw2.data.*;

//class that returns the market value of a property as part of the strategy method
public class MarketValueTrait implements PropertyTrait {

    @Override
    public double value(Property p) {
        return p.getMarketValue();
    }

}
