package boundary;

/**
 * The CampBoundary class represents a camp in the system.
 * A camp can have many students.
 * This class provides methods for handling camp operations such as viewing camps, creating a camp, 
 * editing a camp, deleting a camp, and printing camp details.
 * @author Group 2
 * @since 2023-11-26
 */

import entity.CentralManager;
import entity.Camp;

import java.util.ArrayList;

public class CampBoundary extends BaseBoundary {
     /**
    * Constructor for CampBoundary.
    * @param centralManager The central manager controlling the system.
    */
    public CampBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    /**
     * Displays the camps that the student is eligible for.
     * @param studentID The ID of the student.
     */
    public void viewCamps(String studentID) {
        ArrayList<Camp> camps = this.getCampController().getAvailCamps(studentID);
        this.printCampList(camps, "");
    }

    /**
     * This method allows the staff to create a camp.
     * It first prompts the staff to enter various details about the camp, such as name, dates, registration deadline, user group, location, description, total slots, and camp committee slots.
     * It also prompts the staff to choose the visibility of the camp.
     * The camp is then created and added to the system.
     */
    public void createCamp() {
        String campName = this.getLine("Enter name of camp:");
        ArrayList<Integer> dates = new ArrayList<>();

        Integer startDate = this.getDate("Enter start date");
        System.out.println(startDate);
        Integer endDate = this.getDate("Enter end date");
        System.out.println(endDate);

        dates.add(startDate);
        dates.add(endDate);

        int registrationDeadline = this.getDate("Enter Registration Deadline of camp:");
        String userGroup = this.getLine("Enter user group of camp:");
        String location = this.getLine("Enter location of camp:");
        String description = this.getLine("Enter description of camp:");
        int totalSlots = this.getInt("Enter total slots of camp:");

        int campCommSlots;
        while (true) {
            campCommSlots = this.getInt("Enter camp committee slots:");
            if (campCommSlots < 0 || campCommSlots > 10) {
                System.out.println("Invalid input, camp comm slots must be between 0 and 10.");
            } else {
                break;
            }
        }

        int _visibility;
        while(true){
             _visibility = this.getInt("Enter visibility of camp (1 for visible, 0 for hidden):");
            if (_visibility != 0 && _visibility != 1 ) {
                    System.out.println("Invalid input, visibility of camp must be either 1 or 0");
            } else {
                break;
            }
        }
        boolean visibility = _visibility == 1;

        this.getCampController().createCamp(campName, dates, registrationDeadline, userGroup, location, description,
                totalSlots, this.getStaffController().getCurrentStaff().getUserID(), campCommSlots, visibility);
    }

    /**
     * This method prints a list of camps.
     * It first prints the format for displaying camps.
     * Then, it prints each camp in the list.
     * @param camps The list of camps to print.
     */
    public void printCampList(ArrayList<Camp> camps, String userID) {
        this.printCampFormat(userID);
        int counter = 0;
        for (Camp camp: camps) {
            counter += 1;
            this.printCampLine(counter, camp, userID);
        }
    }

    /**
     * This method prints the format for displaying camps.
     */
    public void printCampFormat(String role) {
        System.out.print("[CampIndex]. [Camp Name] | [Description] | [Location] | [Start to End Dates] | [Faculty] | [Slots Avail] / [Total Slots]");
        if (!role.equals("")) {
            System.out.print(" | [Role]");
        }
        System.out.println();

    }

    /**
     * This method prints a single camp line.
     * It retrieves the camp name, description, location, dates, faculty, and slots from the camp.
     * Then, it formats and prints this information.
     * @param idx The index of the camp.
     * @param camp The camp to print.
     */
    public void printCampLine(int idx, Camp camp, String userID) {
        System.out.print(idx);
        System.out.print(". ");
        System.out.print(camp.getCampName());
        System.out.print(" | ");
        System.out.print(camp.getDescription());
        System.out.print(" | ");
        System.out.print(camp.getLocation());
        System.out.print(" | ");
        ArrayList<Integer> dates = camp.getDates();
        System.out.print(dates.get(0));
        System.out.print(" to ");
        System.out.print(dates.get(1));
        System.out.print(" | ");
        System.out.print(camp.getFaculty());
        System.out.print(" | ");
        System.out.print(camp.getTotalSlots() - camp.getNumberAttendees());
        System.out.print("/" + camp.getTotalSlots());
        if (!userID.equals("")) {
            String role;
            if (camp.getCommitteeMembers().contains(userID)) {
                role = "CampCommitteeMember";
            } else if (camp.getAttendees().contains(userID)) {
                role = "Student";
            } else {
                role = "-";
            }
            System.out.print(" | " + role);
        }
        System.out.println();
    }

    /**
     * This method allows the staff to edit a camp.
     * It first prompts the staff to choose the detail of the camp they want to edit (description, location, visibility, or registration deadline).
     * Depending on the staff's choice, it prompts the staff to enter the new detail and updates the camp.
     * If the staff chooses to cancel the edit, it prints a message and returns.
     * @param camp The camp to edit.
     */
    public void editCamp(Camp camp) {
        while(true){
            int choice = getInt("Which detail of the camp would you like to edit?\n 1. Description\n 2. Location\n 3. Visibility\n 4. Registration Deadline\n 5. Cancel Edit\n");
            switch(choice){
                case 1:
                    String newDescription = this.getLine("Enter new description:");
                    this.getCampController().editCamp(camp, camp.getLocation(), newDescription, camp.getRegistrationDeadline());
                    System.out.println("Description updated.");
                    break;
                case 2:
                    String newLocation = this.getLine("Enter new location:");
                    this.getCampController().editCamp(camp, newLocation, camp.getDescription(), camp.getRegistrationDeadline());
                    System.out.println("Location updated.");
                    break;
                case 3:
                    this.getCampController().toggleVisibility(camp);
                    break;
                case 4:
                    int newDate = this.getDate("Enter new registration deadline");
                    this.getCampController().editCamp(camp, camp.getLocation(), camp.getDescription(), newDate);
                    break;
                case 5:
                    System.out.println("Leaving edit screen...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, 4 or 5.");
            }
        }
    }

    /**
     * This method allows the staff to delete a camp.
     * It first displays the camps that the staff is associated with.
     * Then, it prompts the staff to enter the index of the camp they want to delete.
     * If the staff enters -1, the method returns and no camp is deleted.
     * If the staff enters a valid index, the chosen camp is deleted, and a confirmation message is printed.
     * If the camp cannot be deleted because there are participants who have signed up already, an error message is printed.
     * @param staffID The ID of the staff.
     */
    public void deleteCamps(String staffID) {
        while (true) {
            ArrayList<Camp> myCamps = this.getCampController().getCampsByStaffID(staffID);
            this.printCampList(myCamps, "Staff In-Charge");

            int index = this.getInt("Please enter the camp index for the camp you would like to delete, or enter -1 to exit.");
            if (index == -1) {
                return;
            }
            Camp chosenCamp = myCamps.get(index - 1);
            int success = this.getCampController().deleteCamp(chosenCamp.getCampID());
            if (success == 0) {
                System.out.println("Unable to delete camp as there are participants who have signed up already.");
            } else {  // success == 1
                System.out.println("Camp deletion is successful");
            }
        }

    }
}
