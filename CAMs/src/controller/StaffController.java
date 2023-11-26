package controller;

import entity.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The StaffController class is a controller for managing Staff entities.
 * It extends the UserController class and provides additional functionality specific to staff members.
 * @author Group 2
 * @since 2023-11-26
 */
public class StaffController extends UserController{
	/**
     * The masterCamps field holds a reference to the master list of camps managed by the system.
     */
	ArrayList<Camp> masterCamps = this.centralManager.getMasterCamps();

	/**
     * Constructs a StaffController with the specified CentralManager.
     *
     * @param centralManager The CentralManager instance to associate with the StaffController.
     */
    public StaffController(CentralManager centralManager) {
        super(centralManager);
    }

    /**
     * Sets a new password for the current staff member.
     *
     * @param newPassword The new password to set.
     */
    public void setPassword(String newPassword) {
        this.getCurrentStaff().setPassword(newPassword);
    }

    /**
     * Gets the current staff member associated with this controller.
     *
     * @return The current Staff member.
     */
    public Staff getCurrentStaff() {
        return (Staff) this.currentUser;
    }

    /**
     * Retrieves a staff member by their staff ID.
     *
     * @param staffID The staff ID of the desired staff member.
     * @return The Staff member with the specified staff ID, or null if not found.
     */
    public Staff getStaffByID(String staffID) {
        for (User user: this.getCentralManager().getMasterUsers()) {
            if (user.getUserID().equals(staffID)) {
                return (Staff) user;
            }
        }
        return null;
    }

    /**
     * Checks if the current staff member owns a camp with the specified camp name.
     *
     * @param campName The name of the camp to check ownership for.
     * @return True if the current staff member owns the camp, false otherwise.
     */
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
