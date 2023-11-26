package boundary;
import entity.*;
import utils.GenerateReports.GenerateAttendeeReport;

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
						9.  View Suggestions to My Camps
						10. Approve/Reject Suggestions
						11. Generate Attendee Report for a Camp
						12. Generate performance Report for commitee members
						13. Exit application
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
        this.getCampBoundary().createCamp();
        System.out.println("Camp successfully created");
    }

    public void viewAllCamps(){
        // View All Camps
        System.out.println("Printing all camps: ");
        this.getCampBoundary().printCampFormat();
        ArrayList<Camp> camps = this.getCampController().getAllCamps();
        int counter = 0;
        for (Camp camp: camps) {
            counter++;
            this.getCampBoundary().printCampLine(counter, camp);
        }
    }

    public void viewMyCamps(){
        // View my camps
        System.out.println("Printing my camps: ");
        this.getCampBoundary().printCampFormat();
        String StaffId = this.getStaffController().getCurrentStaff().getUserID();
        ArrayList<Camp> camps = this.getCampController().getCampsByStaffID(StaffId);
        int counter = 0;
        for (Camp camp: camps) {
            counter++;
            this.getCampBoundary().printCampLine(counter, camp);
        }
    }

    public void editMyCamps(){ //EDITTED
        viewMyCamps();
        Staff currentStaff = this.getStaffController().getCurrentStaff();
        ArrayList<Camp> myCamps = this.getCampController().getCampsByStaffID(currentStaff.getUserID());
        int index = getInt("Enter index of camp that you want to edit.");
        Camp campToEdit = myCamps.get(index -1);
        this.getCampBoundary().editCamp(campToEdit);
    }

    public void deleteCamp(){
        this.getCampBoundary().deleteCamps(this.getStaffController().getCurrentStaff().getUserID());
    }

    public void viewEnquiries(){
        System.out.println("...................Enquiries for my camps...................");
        ArrayList<Enquiry> interestedEnquiries = this.getEnquiryController().findEnquiriesByStaff(
                this.getStaffController().getCurrentStaff().getUserID(), false);
        this.getEnquiryBoundary().printEnquiries(interestedEnquiries);
    }

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

    public void viewSuggestions(){
        System.out.println("...............Suggestions for my camps...............");
        ArrayList<Suggestion> interestedSuggestions = this.getSuggestionController().findSugggestionsByStaff(this.getStaffController().getCurrentStaff().getUserID(), false);
        this.getSuggestionBoundary().printSuggestionList(interestedSuggestions);
    }

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
