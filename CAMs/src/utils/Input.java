package utils;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * The Input class represents an object for handling user input operations.
 * It provides methods to interactively ask for various types of user input.
 */
public class Input {
    /**
     * Scanner object for reading user input.
     */
    Scanner sc;

    /**
     * Constructs a new Input object with the specified Scanner.
     *
     * @param sc The Scanner object for user input.
     */
    public Input(Scanner sc) {
        this.sc = Objects.requireNonNullElseGet(sc, () -> new Scanner(System.in));
    }

    /**
     * Gets the underlying Scanner object.
     *
     * @return The Scanner object.
     */
    public Scanner getScanner() {
        return this.sc;
    }

    /**
     * Asks the user for an integer input based on the provided message.
     *
     * @param msgToPrint The message to display to the user.
     * @return The user-entered integer.
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
     * Asks the user for a double input based on the provided message.
     *
     * @param msgToPrint The message to display to the user.
     * @return The user-entered double.
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
     * Asks the user for a String input based on the provided message.
     *
     * @param msgToPrint The message to display to the user.
     * @return The user-entered String.
     */
    public String getLine(String msgToPrint) {
        System.out.print(msgToPrint);
        return this.sc.nextLine();
    }

    /**
     * Asks the user for a boolean input based on the provided message.
     *
     * @param msgToPrint The message to display to the user.
     * @return The user-entered boolean.
     */
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

    /**
     * Asks the user for a date input in the specified format.
     *
     * @param msgToPrint The message to display to the user.
     * @return The user-entered date.
     */
    public Integer getDate(String msgToPrint) {
        System.out.print(msgToPrint);
        System.out.print("\n");
        System.out.println();
        int year;
        while (true) {
            year = this.getInt("Please enter year in YYYY format");
            if (year < 1000 || year>9999) {
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
