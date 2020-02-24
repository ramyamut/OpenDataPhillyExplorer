package edu.upenn.cis350.hw2.data;

//class that stores data on a property/residence
public class Property {
    
    //data
    private double marketValue;
    private double area;
    private int code;
    
    //constructor
    public Property(double mVal, double a, int zip) {
        this.marketValue = mVal;
        this.area = a;
        this.code = zip;
    }
    
    //getter methods
    public double getMarketValue() {
        return this.marketValue;
    }
    
    public double getArea() {
        return this.area;
    }
    
    public int getZIPCode() {
        return this.code;
    }

}
