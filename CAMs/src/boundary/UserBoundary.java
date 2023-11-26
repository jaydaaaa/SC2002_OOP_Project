// NOT COMPLETED
package boundary;
import entity.CentralManager;
import java.util.Objects;

// input getting and data display in boundary eg print to console

public class UserBoundary extends BaseBoundary{
    public UserBoundary(CentralManager centralManager) {
        super(centralManager);
    }

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
