package boundary;
import entity.CentralManager;
import utils.Base;
import utils.BaseInterface;
import utils.Input;

import java.util.Date;
/**
 * The BaseBoundary class serves as a base class for other boundary classes in the system.
 * It extends the Base class and implements the BaseInterface. This class provides common
 * methods for input retrieval and access to various boundaries within the system.
 * @author Group 2
 * @since 2023-11-26
 */

public class BaseBoundary extends Base implements BaseInterface {
    CentralManager centralManager;
    Input input;
    /**
     * Constructs a new BaseBoundary object with the specified CentralManager.
     * @param centralManager The CentralManager associated with this boundary.
     */

    public BaseBoundary(CentralManager centralManager) {
        super(centralManager);
        this.centralManager = centralManager;
        this.input = centralManager.getInput();
    }

    /**
     * Gets the CentralManager associated with this BaseBoundary.
     * @return The CentralManager object.
     */
    @Override
    public CentralManager getCentralManager() {
        return this.centralManager;
    }

    /**
     * Retrieves an integer input from the user with the specified prompt.
     * @param prompt The prompt to display to the user.
     * @return The integer input provided by the user.
     */
    public Integer getInt(String prompt) {
        return this.input.getInt(prompt);
    }

    /**
     * Retrieves a date input from the user with the specified string prompt
     * @param prompt The prompt to display to the user.
     * @return The date input provided by the user.
     */
    public Integer getDate(String prompt) {
        return this.input.getDate(prompt);
    }

    /**
     * Retrieves a double input from the user with the specified string prompt.
     * @param prompt The prompt to display to the user.
     * @return The double input provided by the user.
     */
    public Double getDouble(String prompt) {
        return this.input.getDouble(prompt);
    }

    /**
     * Retrieves a line of text input from the user with the specified prompt.
     * @param prompt The prompt to display to the user.
     * @return The line of text input provided by the user.
     */
    public String getLine(String prompt) {
        return this.input.getLine(prompt);
    }

    // get Boundaries
    /**
     * Gets the CampBoundary associated with this BaseBoundary.
     * @return The CampBoundary object.
     */
    public CampBoundary getCampBoundary() {
        return this.getCentralManager().getCampBoundary();
    }
    /**
     * Gets the CampCMBoundary associated with this BaseBoundary.
     * @return The CampCMBoundary object.
     */
    public CampCMBoundary getCampCMBoundary() {
        return this.getCentralManager().getCampCMBoundary();
    }

    /**
     * Gets the EnquiryBoundary associated with this BaseBoundary.
     * @return The EnquiryBoundary object.
     */
    public EnquiryBoundary getEnquiryBoundary() {
        return this.getCentralManager().getEnquiryBoundary();
    }
    /**
     * Gets the StaffBoundary associated with this BaseBoundary.
     * @return The StaffBoundary object.
     */
    public StaffBoundary getStaffBoundary() {
        return this.getCentralManager().getStaffBoundary();
    }
    /**
     * Gets the StudentBoundary associated with this BaseBoundary.
     * @return The StudentBoundary object.
     */
    public StudentBoundary getStudentBoundary() {
        return this.getCentralManager().getStudentBoundary();
    }
    /**
     * Gets the SuggestionBoundary associated with this BaseBoundary.
     * @return The SuggestionBoundary object.
     */
    public SuggestionBoundary getSuggestionBoundary() {
        return this.getCentralManager().getSuggestionBoundary();
    }
    /**
     * Gets the UserBoundary associated with this BaseBoundary.
     * @return The UserBoundary object.
     */
    public UserBoundary getUserBoundary() {
        return this.getCentralManager().getUserBoundary();
    }
}
