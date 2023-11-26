package entity;

/**
 * The Student class represents a student user in the system.
 * It is a subclass of the User class and inherits its properties and methods.
 * @author Group 2
 * @since 2023-11-26
 */
public class Student extends User {

    /**
     * Constructs a new Student object with the specified details.
     *
     * @param name     The name of the student.
     * @param userID   The userID of the student.
     * @param email    The email address of the student.
     * @param password The password of the student.
     * @param faculty  The faculty of the student.
     * @param userType The type of the student (e.g., student, faculty, etc.).
     */
    public Student(String name, String userID, String email, String password, String faculty, String userType) {
        super(name, userID, email, password, faculty, userType);
    }

    /**
     * Gets the user ID of the student.
     * @return The user ID of the student.
     * Overrides the getUserID method in the parent class.
     */
    @Override
    public String getUserID() {
        return super.getUserID();
    }
}
