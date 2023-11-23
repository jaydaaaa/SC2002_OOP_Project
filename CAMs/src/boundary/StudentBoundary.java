package boundary;
import entity.Camp;
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
        String faculty = this.getStudentController().getFaculty();
        this.getCampBoundary().viewCamps(faculty);
    }

    public void viewMyCamps(){
		//View own camps
        ArrayList<Camp> myCamps = this.getStudentController().getMyCamps();
        for (Camp camp : myCamps) {
            System.out.println(camp.getCampName());
        }
	}

	public void registerCamp() {
		//register for a camp
        String campName = this.getLine("Enter the name of the camp you want to register for:");
        this.getStudentController().registerForCamp(campName);
	}

	public void withdrawCamp(){
		//withdraw from camp
        String campName = this.getLine("Enter the name of the camp you want to withdraw from:");
        this.getStudentController().withdrawFromCamp(campName);
	}

	public void submitEnquiry(){
		// Submit an enquiry for a camp
        String enquiryText = this.getLine("Enter your enquiry:");
        String campName = this.getLine("Enter the name of the camp you have the enquiry for:");
        this.getEnquiryController().submitEnquiry(this.getStudentController().getCurrentStudent(), enquiryText, campName);
	}

	public void deleteEnquiry(){
		//Delete an enquiry for a camp
        String enquiryText = this.getLine("Enter the enquiry you want to delete:");
        Student currentStudent = this.getStudentController().getCurrentStudent();
        for (Enquiry enquiry : currentStudent.getMyEnquiries()) {
            if (enquiry.getEnquiryText().equals(enquiryText)) {
                this.getEnquiryController().deleteEnquiry(currentStudent, enquiry);
                break;
            }
        }
	}

	public void viewEnquiries(){
		//View enquiries and replies
        ArrayList<Enquiry> myEnquiries = this.getStudentController().getMyEnquiries();
        for (Enquiry enquiry : myEnquiries) {
            System.out.println(enquiry.getEnquiryText());
        }
	}

}