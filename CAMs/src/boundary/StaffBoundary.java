package boundary;

/**
 * The StaffBoundary class represents a staff in the system.
 * A staff can be associated with many camps.
 * This class provides methods for handling staff operations such as viewing camps, creating a camp, 
 * editing a camp, deleting a camp, viewing enquiries, replying to enquiries, viewing suggestions, 
 * handling suggestions, generating an attendees report, and generating a performance report.
 * @author Group 2
 * @since 2023-11-26
 */

import entity.*;
import utils.GenerateReports.GenerateAttendeeReport;

import java.util.ArrayList;


public class StaffBoundary extends UserBoundary {
     /**
     * Constructor for StaffBoundary.
     * @param centralManager The central manager controlling the system.
     */
    public StaffBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    /**
     * Displays the menu choices for the staff.
     */
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
						9.  View Suggestions to My Camps
						10. Approve/Reject Suggestions
						11. Generate Attendee Report for a Camp
						12. Generate Performance Report for Committee Members
						13. Exit application
						========================================================================
																"""
        );
    }

    /**
     * This method handles the operations for the staff.
     * It displays a menu of choices and prompts the staff to enter their choice.
     * Depending on the staff's choice, it calls the appropriate method to perform the chosen operation.
     * If the staff enters a choice that is not within the valid range (1-13), it prints an error message and prompts the staff to enter their choice again.
     * If the staff chooses to exit the application, it prints a message indicating that the program has exited.
     */
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

    /**
     * This method allows the staff to change their password.
     * It first prompts the staff to enter a new password.
     * Then, it calls the `setPassword()` method of `StaffController` to update the staff's password.
     * Finally, it prints a confirmation message to inform the staff that their password has been successfully reset.
     */
    public void changePassword() {
        //Change password
        String newPassword = this.getLine("Please enter a new password:");
        this.getStaffController().setPassword(newPassword);
        System.out.println("Your password has been successfully reset.");
    }

    /**
     * This method allows the staff to create a camp.
     * It calls the `createCamp()` method of `CampBoundary` to create the camp.
     * Finally, it prints a confirmation message to inform the staff that the camp has been successfully created.
     */
   public void createCamp(){
        // Create Camp
        this.getCampBoundary().createCamp();
        System.out.println("Camp successfully created");
    }

    /**
     * This method displays all camps.
     * It first prints a header, then retrieves all camps.
     * Then, it prints each camp in the list.
     */
    public void viewAllCamps(){
        // View All Camps
        System.out.println("Printing all camps: ");
        this.getCampBoundary().printCampFormat("");
        ArrayList<Camp> camps = this.getCampController().getAllCamps();
        int counter = 0;
        for (Camp camp: camps) {
            counter++;
            this.getCampBoundary().printCampLine(counter, camp, "");
        }
    }

    /**
     * This method displays the camps that the staff is associated with.
     * It first prints a header, then retrieves the staff's ID and the camps associated with the staff.
     * Then, it prints each camp in the list.
     */
    public void viewMyCamps(){
        // View my camps
        System.out.println("Printing my camps: ");
        this.getCampBoundary().printCampFormat("");
        String StaffId = this.getStaffController().getCurrentStaff().getUserID();
        ArrayList<Camp> camps = this.getCampController().getCampsByStaffID(StaffId);
        int counter = 0;
        for (Camp camp: camps) {
            counter++;
            this.getCampBoundary().printCampLine(counter, camp, "");
        }
    }

    /**
     * This method allows the staff to edit their camps.
     * It first displays the camps that the staff is associated with.
     * Then, it prompts the staff to enter the index of the camp they want to edit.
     * The chosen camp is then edited.
     */
    public void editMyCamps(){ //EDITTED
        viewMyCamps();
        Staff currentStaff = this.getStaffController().getCurrentStaff();
        ArrayList<Camp> myCamps = this.getCampController().getCampsByStaffID(currentStaff.getUserID());
        int index = getInt("Enter index of camp that you want to edit.");
        Camp campToEdit = myCamps.get(index -1);
        this.getCampBoundary().editCamp(campToEdit);
    }

    /**
     * This method allows the staff to delete a camp.
     * It calls the `deleteCamps()` method of `CampBoundary` to delete the camp.
     */
    public void deleteCamp(){
        this.getCampBoundary().deleteCamps(this.getStaffController().getCurrentStaff().getUserID());
    }

    /**
     * This method displays enquiries for the staff's camps.
     * It first prints a header, then retrieves the staff's ID and the enquiries associated with the staff.
     * Then, it prints each enquiry in the list.
     */
    public void viewEnquiries(){
        System.out.println("...................Enquiries for my camps...................");
        ArrayList<Enquiry> interestedEnquiries = this.getEnquiryController().findEnquiriesByStaff(
                this.getStaffController().getCurrentStaff().getUserID(), false);
        this.getEnquiryBoundary().printEnquiries(interestedEnquiries);
    }

    /**
     * This method allows the staff to reply to enquiries.
     * It first retrieves the staff's ID, then prints a list of enquiries for the staff's camps.
     * Then, it prompts the staff to enter the index of the enquiry they want to reply to.
     * If the staff enters -1, the method returns and no reply is made.
     * If the staff enters a valid index, the method prompts the staff to enter their reply.
     * The reply is then added to the enquiry.
     */
    public void replyEnquiries(){
        String staffID = this.getStaffController().getCurrentStaff().getUserID();
        while (true) {
            System.out.println("...................Enquiries for my camps...................");
            ArrayList<Enquiry> interestedEnquiries = this.getEnquiryController().findEnquiriesByStaff(
                    staffID, true);
            this.getEnquiryBoundary().printEnquiries(interestedEnquiries);
            int index = this.getInt("Please enter the index of enquiry you would like to reply to, or enter -1 to exit.");
            if (index == -1) {
                return;
            }
            Enquiry chosenEnquiry = interestedEnquiries.get(index - 1);
            String reply = this.getLine("Please enter your reply for the enquiry.");
            this.getEnquiryController().replyEnquiry(chosenEnquiry, reply, staffID);
        }
    }

    /**
     * This method displays suggestions for the staff's camps.
     * It first prints a header, then retrieves the staff's ID and the suggestions associated with the staff.
     * Then, it prints each suggestion in the list.
     */
    public void viewSuggestions(){
        System.out.println("...............Suggestions for my camps...............");
        ArrayList<Suggestion> interestedSuggestions = this.getSuggestionController().findSugggestionsByStaff(this.getStaffController().getCurrentStaff().getUserID(), false);
        this.getSuggestionBoundary().printSuggestionList(interestedSuggestions);
    }

	/**
	 * This method allows the staff to handle suggestions.
	 * It first prints a header, then retrieves the staff's ID and the suggestions associated with the staff.
	 * Then, it prompts the staff to enter the index of the suggestion they want to process.
	 * If the staff enters -1, the method returns and no suggestion is processed.
	 * If the staff enters a valid index, the method prompts the staff to choose whether they want to approve the suggestion.
	 * The suggestion is then processed, and a confirmation message is printed.
	 */
    public void handleSuggestions(){
       // Approve/Disapprove Suggestions
        while (true) {
            System.out.println("...............Suggestions for my camps...............");
            ArrayList<Suggestion> interestedSuggestions = this.getSuggestionController().findSugggestionsByStaff(this.getStaffController().getCurrentStaff().getUserID(), false);
            this.getSuggestionBoundary().printSuggestionList(interestedSuggestions);

            int index = this.getInt("Please enter the index of the suggestion to process, or enter -1 to exit.");
            if (index == -1) {
                return;
            }
            Suggestion chosenSuggestion = interestedSuggestions.get(index - 1);
            boolean approval = this.input.getBoolean("Would you like to approve this suggestion?");
            this.getSuggestionController().processSuggestion(chosenSuggestion, approval);
            System.out.println("Suggestion approved.");
        }
    }

   /**
     * This method generates an attendees report.
     * It first prompts the staff to enter filters for student names, student roles, camp location, and camp name.
     * Then, it retrieves the staff's ID, the camps associated with the staff, and the attendees of these camps.
     * The report is then generated, and a confirmation message is printed.
     */
    public void generateAttendeesReport(){
        String nameFilter, roleFilter, locationFilter, campNameFilter;
        nameFilter = this.getLine("Enter filter for student names or just hit enter to skip");
        roleFilter = this.getLine("Enter filter for student roles or just hit enter to skip");
        locationFilter = this.getLine("Enter filter for camp location or just hit enter to skip");
        campNameFilter = this.getLine("Enter filter for camp name or just hit enter to skip");

        ArrayList<Camp> myCamps = this.getCampController().getCampsByStaffID(this.getStaffController().getCurrentStaff().getUserID());
        ArrayList<Student> attendees = this.getStudentController().getAttendeesFromCamps(myCamps);
        GenerateAttendeeReport generateReport = new GenerateAttendeeReport(attendees, myCamps, nameFilter, roleFilter,
                locationFilter, campNameFilter, false);
        String ext;
        boolean isCSV = this.input.getBoolean("Do you want to generate a CSV file? Otherwise a TXT file will be generated instead.");
        if (isCSV) {
            ext = ".csv";
        } else {
            ext = ".txt";
        }
        String fpath = System.getProperty("user.dir") + "/reports/AttendanceReport" + ext;
        generateReport.generateReport(fpath);
    }

   /**
     * This method generates a performance report.
     * It first prompts the staff to enter filters for student names, student roles, camp location, and camp name.
     * Then, it retrieves the staff's ID, the camps associated with the staff, and the attendees of these camps.
     * The report is then generated, and a confirmation message is printed.
     */
    public void generatePerformanceReport(){
        String nameFilter, roleFilter, locationFilter, campNameFilter;
        nameFilter = this.getLine("Enter filter for student names or just hit enter to skip");
        roleFilter = this.getLine("Enter filter for student roles or just hit enter to skip");
        locationFilter = this.getLine("Enter filter for camp location or just hit enter to skip");
        campNameFilter = this.getLine("Enter filter for camp name or just hit enter to skip");

        ArrayList<Camp> myCamps = this.getCampController().getCampsByStaffID(this.getStaffController().getCurrentStaff().getUserID());
        ArrayList<Student> attendees = this.getStudentController().getAttendeesFromCamps(myCamps);
        GenerateAttendeeReport generateReport = new GenerateAttendeeReport(attendees, myCamps, nameFilter, roleFilter,
                locationFilter, campNameFilter, true);
        String ext;
        boolean isCSV = this.input.getBoolean("Do you want to generate a CSV file? Otherwise a TXT file will be generated instead.");
        if (isCSV) {
            ext = ".csv";
        } else {
            ext = ".txt";
        }
        String fpath = System.getProperty("user.dir") + "/reports/PerformanceReport" + ext;
        generateReport.generateReport(fpath);
    }

}
