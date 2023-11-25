package boundary;

import entity.CentralManager;
import entity.Enquiry;
import entity.Suggestion;

import java.util.ArrayList;
import java.util.Objects;

public class CampCMBoundary extends StudentBoundary{
    public CampCMBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    public void campCMOperations() {
        int choice = 0;
        while (choice != 16) {
            System.out.print(
                    """
                            ========================= Welcome to Camp Committee Member App =========================
                    """
            );
            this.displayMenuChoices();
            choice = this.getInt("Enter your choice:");
            if (choice < 1 | choice > 17) {
                System.out.println("Enter choice between 1-17 values only");
                continue;
            }
            switch (choice) {
                case 1 -> this.changePassword();
                case 2 -> this.viewCamps();
                case 3 -> this.viewMyCamps();
                case 4 -> this.registerCamp();
                case 5 -> this.submitEnquiry();
                case 6 -> this.editEnquiry();
                case 7 -> this.deleteEnquiry();
                case 8 -> this.viewMyEnquiries();
				case 9 -> this.withdrawCamp();
                case 10 -> this.submitSuggestions();
                case 11 -> this.viewCampEnquiries();
                case 12 -> this.replyEnquiries();
                case 13 -> this.viewMySuggestions();
                case 14 -> this.editMySuggestions();
                case 15 -> this.deleteMySuggestions();
                case 16 -> this.generateAttendeeList();
                case 17 -> System.out.println("System is exiting...");
            }
        }
    }

	public void displayMenuChoices() {
        super.displayMenuChoices();
        System.out.print(
                """
                        9. Submit Suggestions
                        10. View Enquiries
                        11. Reply Enquiries
                        12. View my Suggestions
                        13. Edit my Suggestions
                        14. Delete my Suggestions
                        15. Generate Attendees List
                        16. Exit app
                        ========================================================================
                """
        );
    }

	public void submitSuggestions() {

    }

	public void viewCampEnquiries() {
        //view all equiries to camp this student is campCM for
    }

	public void replyEnquiries() {

    }

	public void viewMySuggestions() {

    }

	public void editMySuggestions() {

    }

	public void deleteMySuggestions() {

    }

	public void generateAttendeeList() {

    }

}
