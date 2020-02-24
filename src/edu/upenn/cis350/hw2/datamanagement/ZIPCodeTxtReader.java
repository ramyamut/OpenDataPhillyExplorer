package edu.upenn.cis350.hw2.datamanagement;

import java.util.*;
import edu.upenn.cis350.hw2.logging.*;
import java.io.*;
import edu.upenn.cis350.hw2.data.*;

//class that reads and returns zip code/population data from a .txt file
public class ZIPCodeTxtReader implements ZIPCodeReader {
    
    private String fileName;
    
    public ZIPCodeTxtReader(String name) {
        this.fileName = name;
    }

    public Set<ZIPCode> getAllZipCodes() {
        Set<ZIPCode> zipCodes = new TreeSet<ZIPCode>();
        try {
            BufferedReader b = new BufferedReader(new FileReader(fileName));
            String line = b.readLine();
            Logger.getInstance().log("processing: " + fileName);
            while (line != null) {
                String[] splitLine = line.split(" ");
                try {
                    int code = Integer.parseInt(splitLine[0].substring(0, 5));
                    int pop = Integer.parseInt(splitLine[1]);
                    zipCodes.add(new ZIPCode(code, pop));
                } catch (NumberFormatException e) {
                    // do nothing if this exception is caught
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
        return zipCodes;
    }

}
