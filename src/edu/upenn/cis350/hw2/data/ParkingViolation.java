package edu.upenn.cis350.hw2.data;

// class that stores data on a parking violation
public class ParkingViolation {
    
    //data
    private double fine;
    private String licensePlateState;
    private String identifier;
    private int code;
    
    //constructor
    public ParkingViolation(double f, String state, String id, int zip) {
        this.fine = f;
        this.licensePlateState = state;
        this.identifier = id;
        this.code = zip;
    }
    
    //getter methods
    public double getFine() {
        return this.fine;
    }
    
    public String getState() {
        return this.licensePlateState;
    }
    
    public String getID() {
        return this.identifier;
    }
    
    public int getZIPCode() {
        return this.code;
    }

}
