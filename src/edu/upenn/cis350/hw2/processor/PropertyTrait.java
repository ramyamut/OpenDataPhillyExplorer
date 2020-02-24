package edu.upenn.cis350.hw2.processor;

import edu.upenn.cis350.hw2.data.*;

//interface with a method that returns a trait of a property/residence,
//as part of the strategy method
public interface PropertyTrait {
    
    double value(Property p);

}
