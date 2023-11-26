package boundary;

import entity.CentralManager;
import entity.Camp;

import java.util.ArrayList;

public class CampBoundary extends BaseBoundary {
    public CampBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    public void viewCamps(String studentID) {
        ArrayList<Camp> camps = this.getCampController().getAvailCamps(studentID);
        this.printCampList(camps);
    }

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
        int _visibility = this.getInt("Enter visibility of camp (1 for visible, 0 for hidden):");
        boolean visibility = _visibility == 1;

        this.getCampController().createCamp(campName, dates, registrationDeadline, userGroup, location, description,
                totalSlots, this.getStaffController().getCurrentStaff().getUserID(), campCommSlots, visibility);
    }

    public void printCampList(ArrayList<Camp> camps) {
        this.printCampFormat();
        int counter = 0;
        for (Camp camp: camps) {
            counter += 1;
            this.printCampLine(counter, camp);
        }
    }

    public void printCampFormat() {
        System.out.println("[CampIndex]. [Camp Name] | [Description] | [Location] | [Start to End Dates] | [Faculty] | [Slots Avail] / [Total Slots]");
    }

    public void printCampLine(int idx, Camp camp) {
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
        System.out.println();
    }

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

    public void deleteCamps(String staffID) {
        while (true) {
            ArrayList<Camp> myCamps = this.getCampController().getCampsByStaffID(staffID);
            this.printCampList(myCamps);

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
