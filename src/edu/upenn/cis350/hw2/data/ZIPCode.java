package edu.upenn.cis350.hw2.data;

import java.util.*;

//class that stores data on a zip code/region
public class ZIPCode implements Comparable<ZIPCode> {
    
    //data
    private int code;
    private int population;
    private List<Property> properties;
    private List<ParkingViolation> violations;
    
    //constructor
    public ZIPCode(int c, int p) {
        this.code = c;
        this.population = p;
        properties = new LinkedList<Property>();
        violations = new LinkedList<ParkingViolation>();
    }
    
    //getter methods
    public int getCode() {
        return this.code;
    }
    
    public int getPopulation() {
        return this.population;
    }
    
    public List<Property> getProperties() {
        return new LinkedList<Property>(properties);
    }
    
    public List<ParkingViolation> getViolations() {
        return new LinkedList<ParkingViolation>(violations);
    }
    
    //methods to update properties or violations
    public void addProperty(Property p) {
        properties.add(p);
    }
    
    public void addViolation(ParkingViolation v) {
        violations.add(v);
    }
    
    //compareTo method
    public int compareTo(ZIPCode other) {
        if (this.code > other.getCode()) {
            return 1;
        } else if (this.code < other.getCode()) {
            return -1;
        } else {
            return 0;
        }
    }

}
