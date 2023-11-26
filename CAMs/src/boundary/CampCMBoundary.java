package boundary;

import entity.*;
import utils.GenerateReports.GenerateAttendeeReport;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The CampCMBoundary class represents a camp committee member in the system.
 * A camp committee member can be associated with many camps.
 * This class provides methods for handling camp committee member operations such as viewing camps, registering for a camp, 
 * submitting an enquiry, editing an enquiry, deleting an enquiry, and changing password.
 * @author Group 2
 * @since 2023-11-26
 */
public class CampCMBoundary extends StudentBoundary{
    /**
     * Constructor for CampCMBoundary.
     * @param centralManager The central manager controlling the system.
     */
    public CampCMBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    /**
     * This method handles the operations for the camp committee member.
     * It displays a menu of choices and prompts the camp committee member to enter their choice.
     * Depending on the camp committee member's choice, it calls the appropriate method to perform the chosen operation.
     * If the camp committee member enters a choice that is not within the valid range (1-17), it prints an error message and prompts the camp committee member to enter their choice again.
     * If the camp committee member chooses to exit the application, it prints a message indicating that the system is exiting.
     */
    public void campCMOperations() {
        int choice = 0;
        while (choice != 17) {
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
                case 10 -> this.submitSuggestion();
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

    /**
     * Displays the menu choices for the camp committee member.
     */
    public void displayMenuChoices() {
        super.displayMenuChoices();
        System.out.print(
                """
                10. Submit Suggestions
                11. View Enquiries
                12. Reply Enquiries
                13. View my Suggestions
                14. Edit my Suggestions
                15. Delete my Suggestions
                16. Generate Attendees List
                17. Exit app
                ========================================================================
                """
        );
    }

    /**
     * Displays enquiries for the camp committee member's camp.
     */
    public void viewCampEnquiries() {
        //view all enquiries to camp this student is campCM for
        ArrayList<Enquiry> relevantEnquiries = this.getEnquiryController().getEnquiryByCamp(this.getCampCMController().getCurrentCampCM().getCampID());
        System.out.println("....................Relevant Enquiries....................");
        this.getEnquiryBoundary().printEnquiries(relevantEnquiries);
    }

    /**
     * Allows the camp committee member to reply to enquiries.
     */
    public void replyEnquiries() {
        while (true) {
            //view all enquiries to camp this student is campCM for
            ArrayList<Enquiry> relevantEnquiries = this.getEnquiryController().getEnquiryByCamp(this.getCampCMController().getCurrentCampCM().getCampID());
            System.out.println("....................Relevant Enquiries....................");
            this.getEnquiryBoundary().printEnquiries(relevantEnquiries);
            int index = this.getInt("Please enter the index of the enquiry that you would like to reply to, otherwise enter -1 to exit.");
            if (index == -1) {
                return;
            }
            Enquiry chosenEnquiry = relevantEnquiries.get(index - 1);
            String replyText = this.getLine("Please enter your reply to the selected enquiry");

            this.getCampCMController().replyEnquiry(chosenEnquiry, replyText);
        }
    }

    /**
     * This method allows the camp committee member to submit a suggestion.
     * It first retrieves the camp that the camp committee member is associated with.
     * Then, it calls the `addSuggestion()` method of `SuggestionBoundary` to add the suggestion.
     */
    public void submitSuggestion() {
        Camp camp = this.getCampController().getCampByID(this.getCampCMController().getCurrentCampCM().getCampID());
        this.getSuggestionBoundary().addSuggestion(camp, this.getCampCMController().getCurrentCampCM());
    }

    /**
     * Displays the camp committee member's suggestions.
     */
    public void viewMySuggestions() {
        ArrayList<Suggestion> mySuggestions = this.getSuggestionController().getSuggestionsbyCampCM(this.getCampCMController().getCurrentCampCM().getUserID());
        System.out.println(".....................My Suggestions.....................");
        this.getSuggestionBoundary().printSuggestionList(mySuggestions);
    }

    /**
     * Allows the camp committee member to edit their suggestions.
     */
    public void editMySuggestions() {
        while (true) {
            ArrayList<Suggestion> mySuggestions = this.getSuggestionController().getSuggestionsbyCampCM(this.getCampCMController().getCurrentCampCM().getUserID());
            System.out.println(".....................My Suggestions.....................");
            this.getSuggestionBoundary().printSuggestionList(mySuggestions);

            int index = this.getInt("Please enter the index of suggestion which you would like to edit, or enter -1 to exit.");
            if (index == -1) {
                return;
            }
            Suggestion chosenSuggestion = mySuggestions.get(index - 1);
            this.getSuggestionBoundary().editSuggestion(chosenSuggestion);
        }
    }

    /**
     * Allows the camp committee member to delete their suggestions.
     */
    public void deleteMySuggestions() {
        while (true) {
            ArrayList<Suggestion> mySuggestions = this.getSuggestionController().getSuggestionsbyCampCM(this.getCampCMController().getCurrentCampCM().getUserID());
            System.out.println(".....................My Suggestions.....................");
            this.getSuggestionBoundary().printSuggestionList(mySuggestions);

            int index = this.getInt("Please enter the index of suggestion which you would like to delete, or enter -1 to exit.");
            if (index == -1) {
                return;
            }
            Suggestion chosenSuggestion = mySuggestions.get(index - 1);
            int success = this.getSuggestionController().deleteSuggestion(chosenSuggestion);
            if (success == 0) {
                System.out.println("Unable to delete suggestion as it has been processed already.");
            } else {
                System.out.println("Suggestion deleted successfully.");
            }
        }
    }

    /**
     * Generates an attendee list.
     */
    public void generateAttendeeList() {
        String nameFilter, roleFilter;
        nameFilter = this.getLine("Enter filter for student names or just hit enter to skip");
        roleFilter = this.getLine("Enter filter for student roles or just hit enter to skip");

        ArrayList<Camp> myCamps = new ArrayList<>();
        myCamps.add(this.getCampController().getCampByID(this.getCampCMController().getCurrentCampCM().getCampID()));
        ArrayList<Student> attendees = this.getStudentController().getAttendeesFromCamps(myCamps);
        GenerateAttendeeReport generateReport = new GenerateAttendeeReport(attendees, myCamps, nameFilter, roleFilter,
                "", "", false);
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
     * Displays the camps that the camp committee member is eligible for.
     */
    public void viewCamps() {
        // View available camps
        String studentID = this.getCampCMController().getCurrentCampCM().getUserID();
        this.getCampBoundary().viewCamps(studentID);
    }

    /**
     * Displays the camps that the camp committee member is associated with.
     */
    public void viewMyCamps(){
        //View own camps
        ArrayList<Camp> myCamps = this.getCampCMController().getAttendedCamps();
        System.out.println("..........................My registered camps..........................");
        if (myCamps.size() == 0) {
            System.out.println("------------------------- No camp registered -------------------------");
        } else {
            this.getCampBoundary().printCampList(myCamps, this.getCampCMController().getCurrentStudent().getUserID());
        }
    }

    /**
     * Allows the camp committee member to register for a camp.
     */
    public void registerCamp() {
        // print all available camps
        while (true) {
            this.viewCamps();
            Student currentStudent = this.getCampCMController().getCurrentStudent();
            ArrayList<Camp> camps = this.getCampController().getAvailCamps(currentStudent.getUserID());
            // register for a camp
            int index = this.getInt("Enter the index of the camp you want to register for, or -1 to exit:");

            if (index == -1) {
                return;
            }

            Camp chosenCamp = camps.get(index - 1);
            int success = this.getCampCMController().registerForCamp(chosenCamp.getCampID());
            if (success == 1) {
                System.out.println("Camp successfully registered!");
            } else if (success == -1) {
                System.out.println("Camp is full, unable to register.");
            } else if (success == -2) {
                System.out.println("Deadline is over, unable to register.");
            } else if (success == -3) {
                System.out.println("No empty camp committee slots, unable to register.");
            }
            else { // success == 0
                System.out.println("Camp period clashes with one of your registered camps, unable to register.");
            }
        }
    }

    /**
     * This method allows the camp committee member to withdraw from a camp.
     * It first displays the camps that the camp committee member is associated with.
     * Then, it prompts the camp committee member to enter the index of the camp they want to withdraw from.
     * If the camp committee member enters -1, the method returns and no camp is withdrawn from.
     * If the camp committee member enters a valid index, the chosen camp is withdrawn from, and a confirmation message is printed.
     * If the camp cannot be withdrawn from because the camp committee member is the MainComm for the camp, an error message is printed.
     */
    public void withdrawCamp(){
        while (true) {
            System.out.println("..........................My registered camps..........................");
            // get my camps
            ArrayList<Camp> myCamps = this.getCampCMController().getAttendedCamps();
            if (myCamps.size() == 0) {
                System.out.println("------------------------- No camp registered -------------------------");
                return;
            }
            this.getCampBoundary().printCampList(myCamps, this.getCampCMController().getCurrentStudent().getUserID());

            // get choice
            int index = this.getInt("Enter the index of the camp you want to withdraw from, enter -1 to exit:");
            if (index == -1) {
                return;
            }
            Camp chosenCamp = myCamps.get(index - 1);

            //withdraw from camp
            int success = this.getCampCMController().withdrawFromCamp(chosenCamp.getCampID());
            if (success == 0) {
                System.out.println("Unable to withdraw from a camp you are MainComm for");
            } else if (success == 1) {
                System.out.println("Successfully withdrawed from camp.");
            } else {  // success == -1
                System.out.println("Unable to withdraw from a camp you are not an participant of");
            }
        }
    }

    /**
     * Allows the camp committee member to edit an enquiry.
     */
    public void editEnquiry() {
        //Delete an enquiry for a camp
        while (true) {
            System.out.println("..............................My Enquiries..............................");
            Student currentStudent = this.getCampCMController().getCurrentStudent();
            ArrayList<Enquiry> enquiries = this.getEnquiryController().findEnquiriesBySender(currentStudent.getUserID());
            if (enquiries.size() > 0) {
                this.getEnquiryBoundary().printEnquiries(enquiries);
                int index = this.getInt("Enter the index of the enquiry you want to edit, -1 to exit:");
                if (index == -1) {
                    return;
                }
                // Edit enquiry
                String newText = this.getLine("Enter new enquiry text");
                Enquiry chosenEnquiry = enquiries.get(index - 1);
                int success = this.getEnquiryController().editEnquiry(currentStudent.getUserID(),
                        chosenEnquiry.getEnquiryID(), newText);
                if (success == 0) {
                    System.out.println("Unable to edit enquiry as you were not the sender.");
                } else if (success == 1) {
                    System.out.println("Enquiry successfully edited");
                } else {
                    System.out.println("Unable to edit enquiry as it was already answered.");
                }
            } else {
                System.out.println("-------------------------No enquiries submitted-------------------------");
                return;
            }
        }
    }

    /**
     * This method allows the camp committee member to submit an enquiry.
     * It first displays the camps that the camp committee member is associated with.
     * Then, it prompts the camp committee member to enter the index of the camp they want to enquire for.
     * If the camp committee member enters -1, the method returns and no enquiry is submitted.
     * If the camp committee member enters a valid index, the method prompts the camp committee member to enter their enquiry.
     * The enquiry is then submitted, and a confirmation message is printed.
     */
    public void submitEnquiry(){
        // Submit an enquiry for a camp
        while (true) {
            ArrayList<Camp> myCamps = this.getCampCMController().getAttendedCamps();
            this.getCampBoundary().printCampList(myCamps, this.getCampCMController().getCurrentStudent().getUserID());
            int index = this.getInt("Enter the index of the camp you are enquiring for, enter -1 to exit:");
            if (index == -1) {
                return;
            }
            Camp chosenCamp = myCamps.get(index - 1);
            String enquiryText = this.getLine("Enter your enquiry:");
            this.getEnquiryController().submitEnquiry(this.getCampCMController().getCurrentStudent(), enquiryText,
                    chosenCamp.getCampID());
            System.out.println("Enquiry for camp successfully submitted.");
        }
    }

    /**
     * This method allows the camp committee member to delete an enquiry.
     * It first prints a header, then retrieves the camp committee member's enquiries.
     * If there are no enquiries, it prints a message indicating this and returns.
     * Otherwise, it prints each enquiry in the list and prompts the camp committee member to enter the index of the enquiry they want to delete.
     * If the camp committee member enters -1, the method returns and no enquiry is deleted.
     * If the camp committee member enters a valid index, the chosen enquiry is deleted, and a confirmation message is printed.
     * If the enquiry cannot be deleted because the camp committee member was not the sender or because it was already answered, an error message is printed.
     */
    public void deleteEnquiry(){
        //Delete an enquiry for a camp
        while (true) {
            System.out.println("..............................My Enquiries..............................");
            Student currentStudent = this.getCampCMController().getCurrentStudent();
            ArrayList<Enquiry> enquiries = this.getEnquiryController().findEnquiriesBySender(currentStudent.getUserID());
            if (enquiries.size() > 0) {
                this.getEnquiryBoundary().printEnquiries(enquiries);
                int index = this.getInt("Enter the index of the enquiry you want to delete, -1 to exit:");
                if (index == -1) {
                    return;
                }
                // Remove enquiry
                Enquiry chosenEnquiry = enquiries.get(index - 1);
                int success = this.getEnquiryController().deleteEnquiry(currentStudent.getUserID(),
                        chosenEnquiry.getEnquiryID());
                if (success == 0) {
                    System.out.println("Unable to delete enquiry as you were not the sender.");
                } else if (success == 1){
                    System.out.println("Enquiry successfully removed");
                } else {
                    System.out.println("Unable to delete enquiry as it was already answered.");
                }
            } else {
                System.out.println("-------------------------No enquiries submitted-------------------------");
                return;
            }
        }
    }

    /**
     * This method displays the camp committee member's enquiries.
     * It first prints a header, then retrieves the camp committee member's enquiries.
     * If there are no enquiries, it prints a message indicating this.
     * Otherwise, it prints each enquiry in the list.
     */
    public void viewMyEnquiries(){
        System.out.println("..............................My Enquiries..............................");
        ArrayList<Enquiry> myEnquiries = this.getCampCMController().getMyEnquiries();
        if (myEnquiries.size() == 0) {
            System.out.println("-------------------------No enquiries submitted-------------------------");
        } else {
            // TODO: View replies
            this.getEnquiryBoundary().printEnquiries(myEnquiries);
        }
    }

}
