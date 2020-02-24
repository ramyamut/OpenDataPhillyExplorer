package edu.upenn.cis350.hw2.datamanagement;

//superclass that provides the csvSplit method
public class CSVReader {
    
    public String[] csvSplit(String line, int size) {
        String[] ret = new String[size];
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                if (line.indexOf("\"") == 0) {
                    int end = line.indexOf("\"", 1);
                    ret[i] = line.substring(1, end);
                } else {
                    ret[i] = line;
                }
            } else {
                if (line.indexOf("\"") == 0) {
                    int end = line.indexOf("\",", 1);
                    ret[i] = line.substring(1, end);
                    line = line.substring(end + 2);
                } else {
                    int end = line.indexOf(",");
                    ret[i] = line.substring(0, end);
                    line = line.substring(end + 1);
                }
            }
        }
        return ret;
    }

}
