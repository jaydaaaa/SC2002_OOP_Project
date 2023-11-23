package controller;

import entity.CentralManager;
import entity.Camp;
import entity.Enquiry;
import entity.Student;

import java.util.ArrayList;
import java.util.Objects;

public class EnquiryController extends BaseController{

     public EnquiryController(CentralManager centralManager) {
        super(centralManager);
    }

    public void editEnquiry(Student student, Enquiry enquiry, String newEnquiryText) {
        // Check if the student's enquiries contain the given enquiry
        if(student.getMyEnquiries().contains(enquiry)) {
            // If it does, set the enquiry text of the enquiry to the provided new enquiry text
            enquiry.setEnquiryText(newEnquiryText);
        } else { // If the student's enquiries do not contain the given enquiry
            // Print a message indicating that the enquiry was not found for the given student
            System.out.println("Enquiry not found for the given student");
        }
    }

    public void deleteEnquiry(Student student, Enquiry enquiry) {
        if(student.getMyEnquiries().contains(enquiry)) {
            // If it does, remove the enquiry from the student's list of enquiries 
            student.getMyEnquiries().remove(enquiry);
        } else { 
            System.out.println("Enquiry not found for the given student"); 
        }
    }

    public void addEnquiry(Student student, String enquiryText) {
        Enquiry newEnquiry = new Enquiry(enquiryText, student.getUserID(), "", "", false);
        // Add the new enquiry to the student's list of enquiries
        student.getMyEnquiries().add(newEnquiry);
    }

    public void replyEnquiry(Student student, Enquiry enquiry, String replyText) {
        // Check if the student's enquiries contain the given enquiry
        if(student.getMyEnquiries().contains(enquiry)) { 
            // If it does, set the reply text of the enquiry to the provided reply text
            enquiry.setReplyText(replyText); 
            // Set the status of the enquiry to true, indicating it has been replied
            enquiry.setStatus(true);
        } else { 
            System.out.println("Enquiry not found for the given student"); 
        }
    }

    public void submitEnquiry(Student student, String enquiryText, String campName) {
        // Create a new enquiry
        Enquiry newEnquiry = new Enquiry(enquiryText, student.getUserID(), "", "", false);
        // Add the enquiry to the student's list of enquiries
        student.getMyEnquiries().add(newEnquiry);
        // Find the camp and add the enquiry to the camp's list of enquiries
        for (Camp camp : this.centralManager.getMasterCamps()) {
            if (camp.getCampName().equals(campName)) {
                camp.getEnquiries().add(newEnquiry);
                break;
            }
        }
    }
     // Get list of enquiry based on camp
    public ArrayList<Enquiry> getEnquiryByCamp(Camp camp) {
        ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
        for (Enquiry enquiry: this.masterEnquiries) {
            if (Objects.equals(enquiry.getCampName(),camp.getCampName())) {
                enquiries.add(enquiry);
            }
        }
        return enquiries;
    }
    
}
