package entity;

import java.util.ArrayList;

/**
 * The Staff class represents a staff user in the system.
 * It is a subclass of the User class and inherits its properties and methods.
 * @author Group 2
 * @since 2023-11-26
 */
public class Staff extends User {
    /**
     * CreatedCamps specific to this Staff user.
     */
    private ArrayList<Camp> createdCamps;

    /**
     * Constructs a new Staff object with the specified details.
     *
     * @param name     The name of the staff.
     * @param userID   The userID for the staff.
     * @param email    The email address of the staff.
     * @param faculty  The faculty of the staff.
     * @param password The password of the staff.
     * @param userType The usertype of the staff
     */
    public Staff(String name, String userID, String email, String faculty, String password, String userType) {
        super(name, userID, email, faculty, password, userType);
        this.createdCamps = new ArrayList<>();
    }

    /**
     * Gets the list of camps created by the staff.
     * @return The list of camps created by the staff.
     */
    public ArrayList<Camp> getCamps() {
        return this.createdCamps;
    }

    /**
     * Adds a new camp to the list of camps created by the staff.
     * @param newCamp The new camp to be added.
     */
    public void addCamps(Camp newCamp) {
        this.createdCamps.add(newCamp);
    }
}
