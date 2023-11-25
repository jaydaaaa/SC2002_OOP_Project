package boundary;
import entity.CentralManager;
import utils.Base;
import utils.BaseInterface;
import utils.Input;

import java.util.Date;

public class BaseBoundary extends Base implements BaseInterface {
    CentralManager centralManager;
    Input input;

    public BaseBoundary(CentralManager centralManager) {
        super(centralManager);
        this.centralManager = centralManager;
        this.input = centralManager.getInput();
    }

    @Override
    public CentralManager getCentralManager() {
        return this.centralManager;
    }

    public Integer getInt(String prompt) {
        return this.input.getInt(prompt);
    }

    public Integer getDate(String prompt) {
        return this.input.getDate(prompt);
    }

    public Double getDouble(String prompt) {
        return this.input.getDouble(prompt);
    }

    public String getLine(String prompt) {
        return this.input.getLine(prompt);
    }

    // get Boundaries
    public CampBoundary getCampBoundary() {
        return this.getCentralManager().getCampBoundary();
    }

    public CampCMBoundary getCampCMBoundary() {
        return this.getCentralManager().getCampCMBoundary();
    }

    public EnquiryBoundary getEnquiryBoundary() {
        return this.getCentralManager().getEnquiryBoundary();
    }

    public StaffBoundary getStaffBoundary() {
        return this.getCentralManager().getStaffBoundary();
    }

    public StudentBoundary getStudentBoundary() {
        return this.getCentralManager().getStudentBoundary();
    }

    public SuggestionBoundary getSuggestionBoundary() {
        return this.getCentralManager().getSuggestionBoundary();
    }

    public UserBoundary getUserBoundary() {
        return this.getCentralManager().getUserBoundary();
    }
}
