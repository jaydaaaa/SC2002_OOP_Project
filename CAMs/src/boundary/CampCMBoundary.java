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
            if (choice < 1 | choice > 16) {
                System.out.println("Enter choice between 1-16 values only");
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
                case 9 -> this.submitSuggestions();
                case 10 -> this.viewEnquiries();
                case 11 -> this.replyEnquiries();
                case 12 -> this.viewMySuggestions();
                case 13 -> this.editMySuggestions();
                case 14 -> this.deleteMySuggestions();
                case 15 -> this.generateAttendeeList();
                case 16 -> System.out.println("System is exiting...");
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
}