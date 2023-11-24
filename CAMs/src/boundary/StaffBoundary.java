package boundary;
import controller.StaffController;
import entity.CentralManager;
import entity.Camp;
import entity.Enquiry;
import entity.Suggestion;
import entity.Staff;

import java.util.ArrayList;


public class StaffBoundary extends UserBoundary {

    public StaffBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    public void displayMenuChoices() {

        System.out.println(
                """
                        ========================= Welcome to Staff App =========================
                        1.  Change Password
                        2.  Create Camp
                        3.  View All Camps
                        4.  View My Camps
                        5.  Edit My Camps
						6.  Delete Camp
                        7.  View Enquires to My Camps
                        8.  Reply Enquires to My Camps
						9.	View Suggestions to My Camps
						10. Approve/Disapprove Suggestions
						11. Generate Attendee Report for a Camp
						12. Generate performance Report for commitee members
                        13.  Exit application
                        ========================================================================
                """
        );
    }

	public void staffOperations() {
        int choice = 0;
        while (choice != 13) {
            this.displayMenuChoices();
            choice = this.getInt("Enter your choice:");
            if (choice < 1 | choice > 13) {
                System.out.println("Enter choice between 1-13 values only");
                continue;
            }
            switch (choice) {
                case 1 -> this.changePassword();
                case 2 -> this.createCamp();
                case 3 -> this.viewAllCamps();
                case 4 -> this.viewMyCamps();
                case 5 -> this.editMyCamps();
                case 6 -> this.deleteCamp();
                case 7 -> this.viewEnquiries();
				case 8 -> this.replyEnquiries();
				case 9 -> this.viewSuggestions();
				case 10 -> this.handleSuggestions();
				case 11 -> this.generateAttendeesReport();
				case 12 -> this.generatePerformanceReport();
                case 13 -> System.out.println("Program exited.");
            }
        }
    }

    public void changePassword() {
        //Change password
        String newPassword = this.getLine("Please enter a new password:");
        this.getStaffController().setPassword(newPassword);
        System.out.println("Your password has been successfully reset.");
    }

   public void createCamp(){
        // Create Camp
        this.getStaffController().createCamp();
        System.out.println("Camp successfully created");
    }

    public void viewAllCamps(){
        // View All Camps
        System.out.println("Printing all camps: ");
        this.getCampBoundary().printCampFormat();
        ArrayList<Camp> camps = this.getCampController().getAllCamps();
        for (Camp camp: camps) {
            this.getCampBoundary().viewCampLine(camp);
        }
    }

    public void viewMyCamps(){
        // View my camps
        System.out.println("Printing my camps: ");
        this.getCampBoundary().printCampFormat();
        String StaffId = this.getStaffController().getCurrentStaff().getUserID();
        ArrayList<Camp> camps = this.getCampController().getCampsByStaffId(StaffId);
        for (Camp camp: camps) {
            this.getCampBoundary().viewCampLine(camp);
        }
    }

	public void editMyCamps(){
        //
    }

    public void deleteCamp(){
        //  Delete Camp
        String CampName = this.getLine("What is the name of the camp that you would like to delete: ");
        Camp camp = this.getStaffController().findCamp(CampName);
        if (camp == null) {
            System.out.println("Invalid camp name");
        }
        else {
            this.getCampController().deleteCamp(camp);
            System.out.println("Deleting camp");
        }

    }

	public void viewEnquiries(){
        //
    }

	public void replyEnquiries(){
        //
    }

	public void viewSuggestions(){
        //
    }

	public void handleSuggestions(){
       // Approve/Disapprove Suggestions
    }

	public void generateAttendeesReport(){
        //
    }

	public void generatePerformanceReport(){
        //
    }

}
