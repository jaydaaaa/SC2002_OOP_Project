package entity;

/**
 * The User class represents a user in the system.
 * A user can access the CAMs system
 * @author Group 2
 * @since 2023-11-26
 */
public class User {

    /**
     * The name of user
     */
    private String name;
    /**
     * The email of user
     */
    private String email;
    /**
     * The userID of user
     */
    private String userID;
    /**
     * The password of user
     */
    private String password;
    /**
     * The faculty of user
     */
    private String faculty;
    /**
     * The type of user
     */
    private String type;

    /**
     * Constructs a new User object with the specified details.
     *
     * @param name     The name of the user.
     * @param userID   The unique identifier for the user.
     * @param email    The email address of the user.
     * @param password The password for the user.
     * @param faculty  The faculty to which the user belongs.
     * @param userType The type of the user (e.g., student, faculty, etc.).
     */
    public User(String name, String userID, String email, String password, String faculty, String userType) {
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.faculty = faculty;
        this.type = userType;
    }

    /**
     * Default constructor for the User class.
     */
    public User() {
    }

    /**
     * Gets the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the email address of the user.
     * @return The email address of the user.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the user ID of the user.
     * @return The user ID of the user.
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Gets the password of the user.
     * @return The password of the user.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the faculty to which the user belongs.
     * @return The faculty of the user.
     */
    public String getFaculty() {
        return this.faculty;
    }

    /**
     * Gets the type of the user (e.g., student, faculty, etc.).
     * @return The type of the user.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the user ID for the user.
     * @param userID The new user ID to set.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Sets the password for the user.
     * @param password The new password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the faculty for the user.
     * @param faculty The new faculty to set.
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
