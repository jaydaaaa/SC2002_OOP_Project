package utils;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * An App.Input Object
 * <p>
 * An <code>App.Input</code> object contains methods to ask for user input
 * </p>
 */
public class Input {
    /**
     * Object of class Scanner
     */
    Scanner sc;

    /**
     * Creates a new object of class App.Input
     * @param sc Scanner object
     */
    public Input(Scanner sc) {
        this.sc = Objects.requireNonNullElseGet(sc, () -> new Scanner(System.in));

    }

    public Scanner getScanner() {
        return this.sc;
    }

    /**
     * Method to display a message and ask for integer input
     * @param msgToPrint Message to be displayed
     * @return ask for user input
     */
    public Integer getInt(String msgToPrint) {
        String res;
        System.out.println(msgToPrint);
        while (true) {
            if (sc.hasNextInt()) {
                res = sc.nextLine();
                break;
            } else {
                System.out.print("Please enter a valid integer.");
                res = sc.nextLine();
            }
        }
        int newInt = Integer.parseInt(res);
        return newInt;
    }


    /**
     * Method to display a message and ask for double input
     * @param msgToPrint Message to be displayed
     * @return ask for user input
     */
    public double getDouble(String msgToPrint) {
        do {
            System.out.print(msgToPrint);
            while (!sc.hasNextDouble()) {
                System.out.println("Please enter a valid double.");
                sc.next();
            }
            return sc.nextInt();
        } while (true);
    }


    /**
     * Method to display a message and ask for String input
     * @param msgToPrint Message to be displayed
     * @return ask for user input
     */
    public String getLine(String msgToPrint) {
        System.out.print(msgToPrint);
        return this.sc.nextLine();
    }

    public boolean getBoolean(String msgToPrint) {
        System.out.print(msgToPrint);
        System.out.print("\n");
        System.out.println();
        String res;
        while (true) {
            System.out.println("Please enter 'Y' or 'N' only");
            res = this.sc.nextLine();
            if (!(res.equals("Y") || res.equals("N"))) {
                System.out.print("Invalid input. ");
            } else {
                return res.equals("Y");
            }
        }
    }

    public Integer getDate(String msgToPrint) {
        System.out.print(msgToPrint);
        System.out.print("\n");
        System.out.println();
        int year;
        while (true) {
            year = this.getInt("Please enter year in YYYY format");
            if (year < 1000) {
                System.out.println("Invalid date. Please ensure year entered is in YYYY format.");
            } else {
                break;
            }
        }
        int month;
        while (true) {
            month = this.getInt("Please enter month in mm format");
            if (month < 1 || month > 12) {
                System.out.println("Invalid month. Please enter a month that is between 01 and 12.");
            } else {
                break;
            }
        }
        int day;
        while (true) {
            day = this.getInt("Please enter day in dd format");
            if (day < 1 || day > 31) {
                System.out.println("Invalid day. Please enter a day that is between 01 and 31.");
            } else {
                break;
            }
        }
        return year * 10000 + month * 100 + day;
    }

}
