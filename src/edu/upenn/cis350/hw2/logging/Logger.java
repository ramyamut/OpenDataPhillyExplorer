package edu.upenn.cis350.hw2.logging;

import java.io.*;

//Logger class that uses the Singleton method and is used to log user activity
public class Logger {
    
    private BufferedWriter out;
    
    private Logger(String fileName) {
        try {
            out = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println("Logging error");
            System.exit(5);
        }
    }
    
    private static Logger instance = new Logger("log.txt");
    
    public static Logger getInstance() {
        return instance;
    }
    
    public void log(String msg) {
        try {
            out.write(msg);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.out.println("Logging error");
            System.exit(5);
        }
    }

}
