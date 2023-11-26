package controller;

import entity.CentralManager;
import entity.Camp;
import entity.Enquiry;
import entity.Student;

import java.util.ArrayList;

/**
 * The EnquiryController class is a controller for managing Enquiry entities.
 * It extends the BaseController class and provides functionality for handling user enquiries.
 * @author Group 2
 * @since 2023-11-26
 */
public class EnquiryController extends BaseController{

	 /**
     * Constructs an EnquiryController with the specified CentralManager.
     *
     * @param centralManager The CentralManager instance to associate with the EnquiryController.
     */
     public EnquiryController(CentralManager centralManager) {
        super(centralManager);
    }

     /**
      * Edits an existing enquiry based on the student's ID, enquiry ID, and new enquiry text.
      *
      * @param studentID       The ID of the student making the edit request.
      * @param enquiryID       The ID of the enquiry to be edited.
      * @param newEnquiryText  The new text for the enquiry.
      * @return 1 if edit is successful, 0 if the student ID does not match the enquiry's creator, -1 if the enquiry has been answered.
      */
    public int editEnquiry(String studentID, String enquiryID, String newEnquiryText) {
        Enquiry enquiry = this.findEnquiryByID(enquiryID);
        if (!enquiry.getStatus()) {
            if (enquiry.getEnquiryBy().equals(studentID)) {
                enquiry.setEnquiryText(newEnquiryText);
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1; // means enquiry has been answered, cannot edit anymore
        }
    }

    /**
     * Deletes an existing enquiry based on the student's ID and enquiry ID.
     *
     * @param studentID The ID of the student making the delete request.
     * @param enquiryID The ID of the enquiry to be deleted.
     * @return 1 if deletion is successful, 0 if the student ID does not match the enquiry's creator, -1 if the enquiry has been answered.
     */
    public int deleteEnquiry(String studentID, String enquiryID) {
        Enquiry enquiry = this.findEnquiryByID(enquiryID);
        if (!enquiry.getStatus()) {
            if (enquiry.getEnquiryBy().equals(studentID)) {
                this.getCentralManager().getMasterEnquiries().remove(enquiry);
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1; // means delete has been answered, cannot edit anymore
        }
    }

    /**
     * Replies to an enquiry by updating the reply text and reply-by information.
     *
     * @param enquiry    The enquiry to reply to.
     * @param replyText  The text of the reply.
     * @param replyBy    The ID of the user replying to the enquiry.
     */
    public void replyEnquiry(Enquiry enquiry, String replyText, String replyBy) {
        enquiry.setReplyText(replyText, replyBy);
    }

    /**
     * Finds an enquiry by its enquiry ID.
     *
     * @param enquiryID The ID of the enquiry to find.
     * @return The Enquiry object with the specified ID, or null if not found.
     */
    public Enquiry findEnquiryByID(String enquiryID) {
         for (Enquiry enquiry: this.getCentralManager().getMasterEnquiries()) {
             if (enquiry.getEnquiryID().equals(enquiryID)) {
                 return enquiry;
             }
         }
        return null;
    }

    /**
     * Finds all enquiries submitted by a specific user.
     *
     * @param userID The ID of the specified user.
     * @return An ArrayList of enquiries submitted by the specified user.
     */
    public ArrayList<Enquiry> findEnquiriesBySender(String userID) {
         ArrayList<Enquiry> enquiries = new ArrayList<>();
         for (Enquiry enquiry: this.getCentralManager().getMasterEnquiries()) {
             if (enquiry.getEnquiryBy().equals(userID)) {
                 enquiries.add(enquiry);
             }
         }
         return enquiries;
     }

    /**
     * Submits a new enquiry by a student for a specific camp.
     *
     * @param student      The student submitting the enquiry.
     * @param enquiryText  The text of the enquiry.
     * @param campID       The ID of the camp associated with the enquiry.
     */
    public void submitEnquiry(Student student, String enquiryText, String campID) {
        // Create a new enquiry
        Enquiry newEnquiry = new Enquiry(enquiryText, campID, student.getUserID(), "", "",
                false, "");
        // Find the camp and add the enquiry to the camp's list of enquiries
        Camp camp = this.getCampController().getCampByID(campID);
        camp.addEnquiry(newEnquiry.getEnquiryID());
        // Add to masterEnquiries
        this.getCentralManager().getMasterEnquiries().add(newEnquiry);
    }

    /**
     * Gets a list of enquiries associated with a specific camp.
     *
     * @param campID The ID of the specified camp.
     * @return An ArrayList of enquiries associated with the specified camp.
     */
    public ArrayList<Enquiry> getEnquiryByCamp(String campID) {
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        for (Enquiry enquiry: this.centralManager.getMasterEnquiries()) {
            if (enquiry.getCampID().equals(campID)) {
                enquiries.add(enquiry);
            }
        }
        return enquiries;
    }

    /**
     * Finds enquiries associated with camps managed by a staff member.
     *
     * @param staffID        The ID of the staff member.
     * @param onlyNonReplied True if only unreplied enquiries should be returned.
     * @return An ArrayList of enquiries associated with the specified staff member and conditionally based on reply status.
     */
    public ArrayList<Enquiry> findEnquiriesByStaff(String staffID, boolean onlyNonReplied) {
        ArrayList<Camp> myCamps = this.getCampController().getCampsByStaffID(staffID);
        ArrayList<Enquiry> interestedEnquiries = new ArrayList<>();
        for (Camp camp: myCamps) {
            for (Enquiry enquiry: this.getEnquiryController().getEnquiryByCamp(camp.getCampID())) {
                if (onlyNonReplied) {
                    if (!enquiry.getStatus()) {
                        interestedEnquiries.add(enquiry);
                    }
                } else {
                    interestedEnquiries.add(enquiry);
                }
            }
        }
        return interestedEnquiries;
    }

}
