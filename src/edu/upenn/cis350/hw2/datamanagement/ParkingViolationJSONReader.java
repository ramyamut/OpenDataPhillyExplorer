package edu.upenn.cis350.hw2.datamanagement;

import edu.upenn.cis350.hw2.data.*;
import edu.upenn.cis350.hw2.logging.*;
import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

//class that reads and returns data from a json file on parking violations
public class ParkingViolationJSONReader implements ParkingViolationReader {
    
    private String fileName;
    
    public ParkingViolationJSONReader(String name) {
        this.fileName = name;
    }
    
    public List<ParkingViolation> getAllParkingViolations() {
        List<ParkingViolation> violations = new LinkedList<ParkingViolation>();
        try {
            JSONParser parser = new JSONParser();
            JSONArray arr = (JSONArray) parser.parse(new FileReader(fileName));
            Logger.getInstance().log("processing: " + fileName);
            Iterator<JSONObject> iter = arr.iterator();
            while (iter.hasNext()) {
                JSONObject obj = (JSONObject) iter.next();
                double fine = -1;
                String state = "";
                String id = "";
                String codeString = "";
                try {
                    fine = (Long) obj.get("fine");
                } catch (Exception e) {
                    fine = -1;
                }
                try {
                    state = (String) obj.get("state");
                } catch (Exception e) {
                    state = "";
                }
                try {
                    id = (String) obj.get("plate_id");
                } catch (Exception e) {
                    id = "";
                }
                try {
                    codeString = (String) obj.get("zip_code");
                } catch (Exception e) {
                    codeString = "";
                }
                int code = -1;
                if (codeString != null) {
                    if (!(codeString.equals(""))) {
                        try {
                            code = Integer.parseInt(codeString.substring(0, 5));
                            violations.add(new ParkingViolation(fine, state, id, code));
                        } catch (NumberFormatException e) {
                            code = -1;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unreadable file");
            Logger.getInstance().log("Error");
            System.exit(3);
        } catch (ParseException e) {
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
