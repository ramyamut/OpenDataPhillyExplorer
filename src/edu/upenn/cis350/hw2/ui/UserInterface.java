package edu.upenn.cis350.hw2.ui;

import edu.upenn.cis350.hw2.logging.Logger;
import edu.upenn.cis350.hw2.processor.*;
import java.util.*;
import java.math.*;

//class that provides for methods that interact with the user
public class UserInterface {
    
    protected AveragesProcessor processor;
    protected Scanner in;
    
    public UserInterface(AveragesProcessor p) {
        in = new Scanner(System.in);
        this.processor = p;
    }
    
    public void start() {
        System.out.println("Explore the OpenDataPhilly Dataset!");
        System.out.println("Select 0 to view the population for a zip code");
        System.out.println("Select 1 to view the total population for all zip code");
        System.out.println("Select 2 to view the parking fines per capita for all zip codes");
        System.out.println("Select 3 to view the average market value"
                + " per residence for a zip code");
        System.out.println("Select 4 to view the average total livable area"
                + " per residence for a zip code");
        System.out.println("Select 5 to view the residential market value"
                + " per capita for a zip code");
        System.out.println("Enter your selection:");
        String choice = in.nextLine();
        if (choice.equals("0")) {
            Logger.getInstance().log("selection: 0");
            System.out.println("Enter a zip code:");
            try {
                int zip = in.nextInt();
                Logger.getInstance().log("zip: " + zip);
                int pop = processor.getPopulation(zip);
                System.out.println(pop);
                System.exit(0);
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice");
                System.exit(4);
            }
        } else if (choice.equals("1")) {
            Logger.getInstance().log("selection: 1");
            int pop = processor.getTotalPopulation();
            System.out.println(pop);
            System.exit(0);
        } else if (choice.equals("2")) {
            Logger.getInstance().log("selection: 2");
            Map<Integer, Double> map = processor.getFinesPerCapita();
            for (Integer zipInt: map.keySet()) {
                double res = map.get(zipInt);
                BigDecimal bigD = new BigDecimal(res).setScale(4, RoundingMode.HALF_UP);
                System.out.println(zipInt + " " + bigD.toString());
            }
            System.exit(0);
        } else if (choice.equals("3")) {
            Logger.getInstance().log("selection: 3");
            System.out.println("Enter a zip code:");
            try {
                int zip = in.nextInt();
                Logger.getInstance().log("zip: " + zip);
                int res = (int) Math.round(processor.getAverageMarketValue(zip));
                System.out.println(res);
                System.exit(0);
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice");
                System.exit(4);
            }
        } else if (choice.equals("4")) {
            Logger.getInstance().log("selection: 4");
            System.out.println("Enter a zip code:");
            try {
                int zip = in.nextInt();
                Logger.getInstance().log("zip: " + zip);
                int res = (int) Math.round(processor.getAverageArea(zip));
                System.out.println(res);
                System.exit(0);
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice");
                System.exit(4);
            }
        } else if (choice.equals("5")) {
            Logger.getInstance().log("selection: 0");
            System.out.println("Enter a zip code:");
            try {
                int zip = in.nextInt();
                Logger.getInstance().log("zip: " + zip);
                int res = (int) Math.round(processor.getMarketValuePerCapita(zip));
                System.out.println(res);
                System.exit(0);
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice");
                System.exit(4);
            }
        } else {
            System.out.println("Invalid choice");
            System.exit(4);
        }
    }

}
