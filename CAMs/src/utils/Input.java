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
        do {
            System.out.println(msgToPrint);
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid integer.");
                sc.next();
            }
            int newInt = Integer.parseInt(sc.nextLine());
            return newInt;
        } while (true);
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

    public Integer getDate(String msgToPrint) {
        System.out.print(msgToPrint);
        System.out.print("\n");
        System.out.println("Please enter year in YYYY format");
        Integer year = Integer.valueOf(this.sc.nextLine());
        System.out.println("Please enter month in mm format");
        Integer month = Integer.valueOf(this.sc.nextLine());
        System.out.println("Please enter day in dd format");
        Integer day = Integer.valueOf(this.sc.nextLine());
        return year * 10000 + month * 100 + day;
    }

}
