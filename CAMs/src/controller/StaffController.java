package controller;

import entity.*;

import java.util.ArrayList;
import java.util.Objects;

public class StaffController extends UserController{
    ArrayList<Camp> masterCamps = this.centralManager.getMasterCamps();

    public StaffController(CentralManager centralManager) {
        super(centralManager);
    }

    public void setPassword(String newPassword) {
        this.getCurrentStaff().setPassword(newPassword);
    }

    public Staff getCurrentStaff() {
        return (Staff) this.currentUser;
    }

    public Staff getStaffByID(String staffID) {
        for (User user: this.getCentralManager().getMasterUsers()) {
            if (user.getUserID().equals(staffID)) {
                return (Staff) user;
            }
        }
        return null;
    }

    public boolean checkCampOwnership(String campName) {
        Staff currentStaff = this.getCurrentStaff();
        for (Camp camp: currentStaff.getCamps()) {
            if (Objects.equals(camp.getCampName(), campName)) {
                return true;
            }
        }
        return false;
    }

}
