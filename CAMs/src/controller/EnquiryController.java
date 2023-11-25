package controller;

import entity.CentralManager;
import entity.Camp;
import entity.Enquiry;
import entity.Student;

import java.util.ArrayList;

public class EnquiryController extends BaseController{

     public EnquiryController(CentralManager centralManager) {
        super(centralManager);
    }

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

    public void replyEnquiry(Student student, Enquiry enquiry, String replyText) {
        // Check if the student's enquiries contain the given enquiry
        // TODO
    }

    public Enquiry findEnquiryByID(String enquiryID) {
         for (Enquiry enquiry: this.getCentralManager().getMasterEnquiries()) {
             if (enquiry.getEnquiryID().equals(enquiryID)) {
                 return enquiry;
             }
         }
        return null;
    }

    public ArrayList<Enquiry> findEnquiriesBySender(String userID) {
         ArrayList<Enquiry> enquiries = new ArrayList<>();
         for (Enquiry enquiry: this.getCentralManager().getMasterEnquiries()) {
             if (enquiry.getEnquiryBy().equals(userID)) {
                 enquiries.add(enquiry);
             }
         }
         return enquiries;
     }

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
     // Get list of enquiry based on camp
    public ArrayList<Enquiry> getEnquiryByCamp(String campID) {
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        for (Enquiry enquiry: this.centralManager.getMasterEnquiries()) {
            if (enquiry.getCampID().equals(campID)) {
                enquiries.add(enquiry);
            }
        }
        return enquiries;
    }

}
