package boundary;

import entity.CentralManager;
import entity.Camp;

import java.util.ArrayList;

public class CampBoundary extends BaseBoundary {
    public CampBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    public void viewCamps(String studentID) {
        // need to check visibility of camp
        // need to check camps that are not full
        // need to check blacklist of student
        // need to check faculty
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

        Integer registrationDeadline = this.getInt("Enter Registration Deadline of camp:");
        String userGroup = this.getLine("Enter user group of camp:");
        String location = this.getLine("Enter location of camp:");
        String description = this.getLine("Enter description of camp:");
        int totalSlots = this.getInt("Enter total slots of camp:");

        int campCommSlots = this.getInt("Enter camp committee slots:");
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
        System.out.println("[CampIndex]. [Camp Name] | [Start to End Dates] | [Faculty] | [Slots Avail]");
    }

    public void printCampLine(int idx, Camp camp) {
        System.out.print(idx);
        System.out.print(". ");
        System.out.print(camp.getCampName());
        System.out.print(" | ");
        ArrayList<Integer> dates = camp.getDates();
        System.out.print(dates.get(0));
        System.out.print(" to ");
        System.out.print(dates.get(1));
        System.out.print(" | ");
        System.out.print(camp.getFaculty());
        System.out.print(" | ");
        System.out.print(camp.getTotalSlots());
        System.out.println();
    }

    public void editCamp() {
        // Options list of what edit
            // for each option, run edit fn
                // eg. have a prompt for edit campName --> calls campController.editCamp() :| all other variables will be retained so you will have to get those vars and save it locally first before passing it back into the edit fn in camp controller
    }
}
