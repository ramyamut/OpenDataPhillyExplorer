package edu.upenn.cis350.hw2.datamanagement;

import edu.upenn.cis350.hw2.data.*;
import edu.upenn.cis350.hw2.logging.*;
import java.util.*;
import java.io.*;

//class that reads and returns property data from a CSV file
public class PropertyCSVReader extends CSVReader implements PropertyReader {
    
    private String fileName;
    
    public PropertyCSVReader(String name) {
        this.fileName = name;
    }

    public List<Property> getAllProperties() {
        List<Property> properties = new LinkedList<Property>();
        try {
            BufferedReader b = new BufferedReader(new FileReader(fileName));
            String firstLine = b.readLine();
            String[] fields = firstLine.split(",");
            int indMV = -1;
            int indArea = -1;
            int indCode = -1;
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].equals("market_value")) {
                    indMV = i;
                } else if (fields[i].equals("total_livable_area")) {
                    indArea = i;
                } else if (fields[i].equals("zip_code")) {
                    indCode = i;
                }
            }
            String line = b.readLine();
            Logger.getInstance().log("processing: " + fileName);
            while (line != null) {
                String[] splitLine = csvSplit(line, fields.length);
                double marketValue = -1;
                double area = -1;
                int code = -1;
                if (splitLine[indMV] != null) {
                    if (!splitLine[indMV].equals("")) {
                        try {
                            marketValue = Double.parseDouble(splitLine[indMV]);
                        } catch (NumberFormatException e) {
                            marketValue = -1;
                        }
                    }
                }
                if (splitLine[indArea] != null) {
                    if (!splitLine[indArea].equals("")) {
                        try {
                            area = Double.parseDouble(splitLine[indArea]);
                        } catch (NumberFormatException e) {
                            area = -1;
                        }
                    }
                }
                if (splitLine[indCode] != null) {
                    if (!splitLine[indCode].equals("")) {
                        try {
                            code = Integer.parseInt(splitLine[indCode].substring(0, 5));
                        } catch (Exception e) {
                            code = -1;
                        }
                    }
                }
                if (code != -1) {
                    properties.add(new Property(marketValue, area, code));
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
        return properties;
    }

}
