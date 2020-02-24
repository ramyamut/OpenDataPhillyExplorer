package edu.upenn.cis350.hw2.processor;

import java.util.*;
import edu.upenn.cis350.hw2.data.*;

//class that contains the methods that calculate results based on the data
public class AveragesProcessor {
    
    private DataProcessor dataProcessor;
    
    public AveragesProcessor(DataProcessor p) {
        this.dataProcessor = p;
    }
    
    public int getPopulation(int zip) {
        int pop = 0;
        Set<ZIPCode> zipCodes = dataProcessor.getProcessedZIPCodes();
        for (ZIPCode z: zipCodes) {
            if (z.getCode() == zip) {
                pop = z.getPopulation();
            }
        }
        return pop;
    }
    
    public int getTotalPopulation() {
        int pop = 0;
        Set<ZIPCode> zipCodes = dataProcessor.getProcessedZIPCodes();
        for (ZIPCode z: zipCodes) {
            pop += z.getPopulation();
        }
        return pop;
    }
    
    public Map<Integer, Double> getFinesPerCapita() {
        Map<Integer, Double> out = new TreeMap<Integer, Double>();
        Set<ZIPCode> zipCodes = dataProcessor.getProcessedZIPCodes();
        for (ZIPCode z: zipCodes) {
            if (z.getCode() == -1 || z.getPopulation() == 0) {
                continue;
            }
            double sumFines = 0;
            List<ParkingViolation> zipCodeViolations = z.getViolations();
            for (ParkingViolation v: zipCodeViolations) {
                //System.out.println(v.getFine());
                if ((v.getState().equals("PA")) && v.getZIPCode() == z.getCode() &&
                        v.getFine() != -1) {
                    sumFines += v.getFine();
                }
            }
            if (sumFines != 0 && z.getPopulation() != 0) {
                double finesPerCapita = sumFines / z.getPopulation();
                out.put(z.getCode(), finesPerCapita);
            }
        }
        return out;
    }
    
    private double averagePropertyTrait(int zip, PropertyTrait pT) {
        Set<ZIPCode> zipCodes = dataProcessor.getProcessedZIPCodes();
        double result = 0;
        if (zip == 0) {
            return result;
        }
        double sum = 0;
        int num = 0;
        for (ZIPCode z: zipCodes) {
            if (z.getCode() == zip) {
                List<Property> properties = z.getProperties();
                for (Property p: properties) {
                    if (p.getZIPCode() == z.getCode() && pT.value(p) != -1) {
                        sum += pT.value(p);
                        num += 1;
                    }
                }
                if (num != 0) {
                    result = sum / num;
                }
                return result;
            }
        }
        return result;
    }
    
    public double getAverageMarketValue(int zip) {
        return averagePropertyTrait(zip, new MarketValueTrait());
    }
    
    public double getAverageArea(int zip) {
        return averagePropertyTrait(zip, new AreaTrait());
    }
    
    public double getMarketValuePerCapita(int zip) {
        double result = 0;
        Set<ZIPCode> zipCodes = dataProcessor.getProcessedZIPCodes();
        for (ZIPCode z: zipCodes) {
            if (z.getCode() == zip && z.getPopulation() != 0) {
                double sum = 0;
                List<Property> properties = z.getProperties();
                for (Property p: properties) {
                    if (p.getZIPCode() != -1 && p.getMarketValue() != -1) {
                        sum += p.getMarketValue();
                    }
                }
                if (sum != 0) {
                    result = sum / z.getPopulation();
                }
                return result;
            }
        }
        return result;
    }

}
