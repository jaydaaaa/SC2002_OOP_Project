package controller;

import entity.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
/**
 * The CampCMController class is a controller for managing CampCM (Camp Committee Member) entities.
 * It extends the StudentController class and provides additional functionality specific to camp committee members.
 * @author Group 2
 * @since 2023-11-26
 */
public class CampCMController extends StudentController {

    /**
     * Constructs a CampCMController with the specified CentralManager.
     *
     * @param centralManager The CentralManager instance to associate with the CampCMController.
     */
    public CampCMController(CentralManager centralManager) {
        super(centralManager);
    }

    /**
     * Gets the current CampCM user associated with this controller.
     *
     * @return The current CampCM user.
     */
    public CampCM getCurrentCampCM() {
        return (CampCM) this.currentUser;
    }

    /**
     * Retrieves a CampCM user by their student ID.
     *
     * @param studentID The student ID of the desired CampCM user.
     * @return The CampCM user with the specified student ID, or null if not found.
     */
    public CampCM getStudentByID(String studentID) {
        for (User user : this.getCentralManager().getMasterUsers()) {
            if (user.getUserID().equals(studentID)) {
                return (CampCM) user;
            }
        }
        return null;
    }

    /**
     * Replies to an enquiry and updates the associated CampCM's points and calling the EnquiryController's replyEnquiry method.
     *
     * @param enquiry    The enquiry to reply to.
     * @param replyText  The text of the reply.
     */
    public void replyEnquiry(Enquiry enquiry, String replyText) {
        String studentID = this.getCampCMController().getCurrentCampCM().getUserID();
        this.getEnquiryController().replyEnquiry(enquiry, replyText, studentID);
        CampCM currentCM = this.getCampCMController().getCurrentCampCM();

        // increment points
        currentCM.setPoints(currentCM.getPoints() + 1);
    }

    /**
     * Registers the current CampCM user for a camp.
     *
     * @param campID The ID of the camp to register for.
     * @return 1 if registration is successful, 0 if there is a clash, -1 if the camp is full, -2 if the deadline is over.
     */
    public int registerForCamp(String campID) {
        // Find the camp
        Camp newCamp = this.getCampController().getCampByID(campID);
        // Check if deadlines overlap
        for (Camp camp : this.getAttendedCamps()) {
            if (this.doesClash(camp, newCamp)) {
                return 0; // clash
            }
        }
        if (newCamp.getNumberAttendees() >= newCamp.getTotalSlots()) {
            return -1; // full
        }
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Format the current date using the formatter
        String formattedDate = currentDate.format(formatter);

        // Cast date to integer
        int date = Integer.parseInt(formattedDate);
        System.out.println("currentDate: " + date);

        if (newCamp.getRegistrationDeadline() < date) {
            return -2; // deadline over
        }
        String userID;
        CampCM currentStudent = this.getCurrentCampCM();
        userID = currentStudent.getUserID();
        newCamp.addAttendee(userID);
        return 1;
    }
}
