package utils;

import controller.*;
import entity.CentralManager;

/**
 * The Base class provides a base implementation for accessing controllers and the central manager
 * within the Camp Attendance Management (CAM) system.
 * It contains a reference to the central manager and offers convenient methods to retrieve various controllers.
 * @author Group 2
 * @since 2023-11-26
 */
public class Base {
	
	/**
     * The centralManager field holds an instance of CentralManager for managing system data and components.
     */
	CentralManager centralManager;
	
	/**
     * Constructs a Base with the specified CentralManager.
     *
     * @param centralManager The CentralManager instance to associate with the Base.
     */
    public Base(CentralManager centralManager) {
        this.centralManager = centralManager;
    }

    /**
     * Gets the central manager associated with the Base.
     *
     * @return The CentralManager instance associated with the Base.
     */
    public CentralManager getCentralManager() { // central manager is one of the abstract methods defined in base interface hence when we implement BaseInterface have to concretize it
        return this.centralManager;
    }

    /**
     * Gets the CampController associated with the Base.
     *
     * @return The CampController instance associated with the Base.
     */
    public CampController getCampController() {
        return this.getCentralManager().getCampController();
    }

    /**
     * Gets the EnquiryController associated with the Base.
     *
     * @return The EnquiryController instance associated with the Base.
     */
    public EnquiryController getEnquiryController() {
        return this.getCentralManager().getEnquiryController();
    }

    /**
     * Gets the SuggestionController associated with the Base.
     *
     * @return The SuggestionController instance associated with the Base.
     */
    public SuggestionController getSuggestionController() {
        return this.getCentralManager().getSuggestionController();
    }

    /**
     * Gets the StudentController associated with the Base.
     *
     * @return The StudentController instance associated with the Base.
     */
    public StudentController getStudentController() {
        return this.getCentralManager().getStudentController();
    }

    /**
     * Gets the StaffController associated with the Base.
     *
     * @return The StaffController instance associated with the Base.
     */
    public StaffController getStaffController() {
        return this.getCentralManager().getStaffController();
    }
    
    /**
     * Gets the UserController associated with the Base.
     *
     * @return The UserController instance associated with the Base.
     */
    public UserController getUserController() {
        return this.getCentralManager().getUserController();
    }
    
    /**
     * Gets the CampCMController associated with the Base.
     *
     * @return The CampCMController instance associated with the Base.
     */
    public CampCMController getCampCMController() {
        return this.getCentralManager().getCampCMController();
    }
}
