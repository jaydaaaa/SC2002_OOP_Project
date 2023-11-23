package boundary;

import controller.CampController;
import controller.StaffController;
import entity.CentralManager;
import entity.Camp;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class CampBoundary extends BaseBoundary {
    public CampBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    public void viewCamps(String faculty) {
		// need to check visibility of camp
		// need to check camps that are not full
		// need to check blacklist of student 
		// need to check faculty
        ArrayList<Camp> camps = this.getCampController().getCamps(faculty); 

    public void printCampFormat() {
        System.out.println("[Camp Name]");
    }

    public void viewCampLine(Camp camp) {
        System.out.println(camp.getCampName());
    }
}
