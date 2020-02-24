package edu.upenn.cis350.hw2.datamanagement;

import edu.upenn.cis350.hw2.data.*;
import java.util.*;

//interface for classes that read property/residence data
public interface PropertyReader {
    
    List<Property> getAllProperties();

}
