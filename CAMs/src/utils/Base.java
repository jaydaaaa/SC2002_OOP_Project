package utils;

import controller.*;
import entity.CentralManager;

public class Base {
    CentralManager centralManager;
    public Base(CentralManager centralManager) {
        this.centralManager = centralManager;
    }

    public CentralManager getCentralManager() { // central manager is one of the abstract methods defined in base interface hence when we implement BaseInterface have to concretize it
        return this.centralManager;
    }

    // get controllers
    public CampController getCampController() {
        return this.getCentralManager().getCampController();
    }

    public EnquiryController getEnquiryController() {
        return this.getCentralManager().getEnquiryController();
    }

    public SuggestionController getSuggestionController() {
        return this.getCentralManager().getSuggestionController();
    }

    public StudentController getStudentController() {
        return this.getCentralManager().getStudentController();
    }

    public StaffController getStaffController() {
        return this.getCentralManager().getStaffController();
    }
    public UserController getUserController() {
        return this.getCentralManager().getUserController();
    }
    public CampCMController getCampCMController() {
        return this.getCentralManager().getCampCMController();
    }
}
