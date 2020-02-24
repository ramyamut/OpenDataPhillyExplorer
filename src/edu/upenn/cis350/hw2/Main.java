package edu.upenn.cis350.hw2;

import edu.upenn.cis350.hw2.ui.*;
import edu.upenn.cis350.hw2.datamanagement.*;
import edu.upenn.cis350.hw2.processor.*;
import edu.upenn.cis350.hw2.logging.*;

// main class
public class Main {

    public static void main(String[] args) {
        testArgumentsForErrors(args);
        String violationFileFormat = args[0];
        String violationFileName = args[1];
        String propertyFileName = args[2];
        String populationFileName = args[3];
        
        //data management tier
        ZIPCodeReader zipReader = new ZIPCodeTxtReader(populationFileName);
        PropertyReader propertyReader = new PropertyCSVReader(propertyFileName);
        ParkingViolationReader violationReader = null;
        if (violationFileFormat.equals("csv")) {
            violationReader = new ParkingViolationCSVReader(violationFileName);
        } else {
            violationReader = new ParkingViolationJSONReader(violationFileName);
        }
        
        //processing tier
        DataProcessor dataProcessor = new DataProcessor(zipReader,
                violationReader, propertyReader);
        AveragesProcessor avgProcessor = new AveragesProcessor(dataProcessor);
        
        //User interface tier
        UserInterface ui = new UserInterface(avgProcessor);
        ui.start();
    }
    
    private static void testArgumentsForErrors(String[] arguments) {
        if (arguments.length != 4) {
            System.out.println("Incorrect number of arguments");
            Logger.getInstance().log("Error");
            System.exit(1);
        }
        if (!arguments[0].equals("csv") && !arguments[0].equals("json")) {
            System.out.println("First argument must be csv or json");
            Logger.getInstance().log("Error");
            System.exit(2);
        }
        if (arguments[0].equals("csv") && arguments[1].indexOf(".csv") == -1) {
            System.out.println("Unreadable file");
            Logger.getInstance().log("Error");
            System.exit(3);
        }
        if (arguments[0].equals("json") && arguments[1].indexOf(".json") == -1) {
            System.out.println("Unreadable file");
            Logger.getInstance().log("Error");
            System.exit(3);
        }
    }

}
