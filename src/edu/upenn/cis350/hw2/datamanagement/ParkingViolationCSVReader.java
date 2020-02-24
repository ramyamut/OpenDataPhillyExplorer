package edu.upenn.cis350.hw2.datamanagement;

import edu.upenn.cis350.hw2.data.*;
import edu.upenn.cis350.hw2.logging.*;
import java.util.*;
import java.io.*;

//class that reads and returns data from csv files containing violation data
public class ParkingViolationCSVReader extends CSVReader implements ParkingViolationReader {
    
    private String fileName;
    
    public ParkingViolationCSVReader(String name) {
        this.fileName = name;
    }
    
    public List<ParkingViolation> getAllParkingViolations() {
        List<ParkingViolation> violations = new LinkedList<ParkingViolation>();
        try {
            BufferedReader b = new BufferedReader(new FileReader(fileName));
            String line = b.readLine();
            Logger.getInstance().log("processing: " + fileName);
            while (line != null) {
                String[] splitLine = csvSplit(line, 7);
                double fine = -1;
                try {
                    fine = Double.parseDouble(splitLine[1]);
                } catch (Exception e) {
                    fine = -1;
                }
                String state = splitLine[4];
                String id = splitLine[5];
                int code = -1;
                if (splitLine[6] != null) {
                    if (!(splitLine[6].equals(""))) {
                        try {
                            code = Integer.parseInt(splitLine[6].substring(0, 5));
                            violations.add(new ParkingViolation(fine, state, id, code));
                        } catch (Exception e) {
                            code = -1;
                        }
                    }
                }
                line = b.readLine();
            }
            b.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unreadable file");
            Logger.getInstance().log("Error");
            System.exit(3);
        } catch (IOException e) {
            System.out.println("Unreadable file");
            Logger.getInstance().log("Error");
            System.exit(3);
        }
        return violations;
    }

}
