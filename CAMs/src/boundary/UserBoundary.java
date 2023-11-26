package boundary;
import entity.CentralManager;
import java.util.Objects;

/**
 * The UserBoundary class represents a user in the system.
 * A user can be a student, staff, or camp committee member.
 * This class provides methods for handling user operations such as login.
 * @author Group 2
 * @since 2023-11-26
 */

 /**
 * This class represents the boundary for a User in the system.
 * It extends the BaseBoundary class and provides methods for user operations.
 */
public class UserBoundary extends BaseBoundary{

    public UserBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    /**
    * This method handles the login operation for the user.
    * It prompts the user to enter their UserID and Password.
    * If the UserID or Password is invalid, it prints an error message and prompts the user to enter their credentials again.
    * Once the user is authenticated, it directs the user to the appropriate screen based on their user type (Student, Staff, or CampCM).
    * Note: This method does not perform data manipulation. It checks for a valid user and password in the controller.
    */
    public void login(){
        // invalid user is just not registered user in the system
        String userID = this.getLine("Input UserID: ");
        String password = this.getLine("Input Password: ");
        String userType = this.getUserController().login(userID, password);
        while (Objects.equals(userType, "InvalidUser")) {
            System.out.println("UserID or Password was invalid. Please try again.");
            userID = this.getLine("Input UserID: ");
            password = this.getLine("Input Password: ");
            userType = this.getUserController().login(userID, password);
        }

        if (password.equals("password")) {
            while (true) {
                String newPassword = this.getLine("You currently have the default password, please enter a new password:");
                if (newPassword.equals("password")) {
                    System.out.print("Invalid input, ");
                } else {
                    this.getUserController().setPassword(userID, newPassword);
                    System.out.println("Password successfully changed!");
                    break;
                }
            }
        }

        if (Objects.equals(userType, "Student")){
            System.out.println("Directing to student screen...");
            this.getStudentController().setCurrentUser(userID);
            this.getStudentBoundary().studentOperations();
        }
        else if (Objects.equals(userType, "Staff")) {
            System.out.println("Directing to staff screen...");
            this.getStaffController().setCurrentUser(userID);
            this.getStaffBoundary().staffOperations();
        }
        else if (Objects.equals(userType, "CampCM")) {
            System.out.println("Directing to Camp Committee Member screen...");
            this.getCampCMController().setCurrentUser(userID);
            this.getCampCMBoundary().campCMOperations();
        }
    }

    // boundary doesnt do data manipulation
    // check for valid user and pw in the controller
    // initialise password to password
}
