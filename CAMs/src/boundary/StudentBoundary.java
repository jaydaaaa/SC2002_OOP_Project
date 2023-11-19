package boundary;
import entity.CentralManager;
import entity.Enquiry;
import entity.Student;

import java.util.ArrayList;

import controller.UserController;

public class StudentBoundary extends UserBoundary{
    public StudentBoundary(CentralManager centralManager){
        super(centralManager);
    }

	public void displayMenuChoices() {

        System.out.println(
                """
                        1.  Change password
                        2.  View available camps
                        3.  View my camps
                        4.  Register for a camp
                        5.  Submit an enquiry for a camp
						6.  Delete an enquiry for a camp
                        7.  View enquiries and replies
                        8.  Withdraw from a camp
                """
        );
    }

	public void studentOperations() {
        int choice = 0;
        while (choice != 9) {
			System.out.print(
                """
                        ========================= Welcome to Student App =========================
                """
            );
            this.displayMenuChoices();
			System.out.println("""
                            9.  Exit application
                            ========================================================================
                    """);
            choice = this.getInt("Enter your choice:");
            if (choice < 1 | choice > 9) {
                System.out.println("Enter choice between 1-9 values only");
                continue;
            }
            switch (choice) {
                case 1 -> this.changePassword();
                case 2 -> this.viewCamps();
                case 3 -> this.viewMyCamps();
                case 4 -> this.registerCamp();
                case 5 -> this.submitEnquiry();
                case 6 -> this.deleteEnquiry();
                case 7 -> this.viewEnquiries();
				case 8 -> this.withdrawCamp();
                case 9 -> System.out.println("Program exited.");
            }
        }
    }

	public void changePassword() {
        //Change password
        String newPassword = this.getLine("Please enter a new password:");
        this.getStudentController().setPassword(newPassword);
        System.out.println("Your password has been successfully reset.");
    }

    public void viewCamps() {
        // View available camps
		String faculty = /*Missing controller */.getFaculty();
        this.getCampBoundary().viewCamps("faculty");
    }

    public void viewMyCamps(){
		//View own camps
	}

	public void registerCamp() {
		//register for a camp
	}

	public void withdrawCamp(){
		//withdraw from camp
	}

	public void submitEnquiry(){
		// Submit an enquiry for a camp
	}

	public void deleteEnquiry(){
		//Delete an enquiry for a camp
	}

	public void viewEnquiries(){
		//View enquiries and replies
	}

}