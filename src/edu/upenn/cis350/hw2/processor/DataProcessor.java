package edu.upenn.cis350.hw2.processor;

import edu.upenn.cis350.hw2.datamanagement.*;
import edu.upenn.cis350.hw2.data.*;
import java.util.*;

//class that organizes the 3 datasets into one data structure
public class DataProcessor {
    
    protected ZIPCodeReader zipReader;
    protected ParkingViolationReader violationReader;
    protected PropertyReader propertyReader;
    
    private Set<ZIPCode> zipCodes;
    private List<ParkingViolation> violations;
    private List<Property> properties;
    
    public DataProcessor(ZIPCodeReader z, ParkingViolationReader v, PropertyReader p) {
        this.zipReader = z;
        this.violationReader = v;
        this.propertyReader = p;
        fillInDataStructures();
    }
    
    private void fillInDataStructures() {
        properties = propertyReader.getAllProperties();
        violations = violationReader.getAllParkingViolations();
        zipCodes = zipReader.getAllZipCodes();
        for (ZIPCode z: zipCodes) {
            int code = z.getCode();
            for (Property p: properties) {
                if (p.getZIPCode() == code) {
                    z.addProperty(p);
                }
            }
            for (ParkingViolation v: violations) {
                if (v.getZIPCode() == code) {
                    z.addViolation(v);
                }
            }
        }
    }
    
    public Set<ZIPCode> getProcessedZIPCodes() {
        return new TreeSet<ZIPCode>(zipCodes);
    }

}
