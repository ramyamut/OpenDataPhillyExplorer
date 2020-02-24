package edu.upenn.cis350.hw2.datamanagement;

import edu.upenn.cis350.hw2.data.*;
import java.util.*;

//interface for objects that read files containing parking violation data
public interface ParkingViolationReader {
    
    List<ParkingViolation> getAllParkingViolations();

}
