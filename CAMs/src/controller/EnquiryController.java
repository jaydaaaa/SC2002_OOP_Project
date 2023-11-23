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
        if(student.getMyEnquiries().contains(enquiry)) {
            enquiry.setEnquiryText(newEnquiryText);
        } else {
            System.out.println("Enquiry not found for the given student");
        }
    }

    public void deleteEnquiry(Student student, Enquiry enquiry) {
        if(student.getMyEnquiries().contains(enquiry)) {
            student.getMyEnquiries().remove(enquiry);
        } else {
            System.out.println("Enquiry not found for the given student");
        }
    }

    public void addEnquiry(Student student, String enquiryText) {
        Enquiry newEnquiry = new Enquiry(enquiryText, student.getUserID(), "", "", false);
        student.getMyEnquiries().add(newEnquiry);
    }

    public void replyEnquiry(Student student, Enquiry enquiry, String replyText) {
        if(student.getMyEnquiries().contains(enquiry)) {
            enquiry.setReplyText(replyText);
            enquiry.setStatus(true);
        } else {
            System.out.println("Enquiry not found for the given student");
        }
    }

    public void submitEnquiry(Student student, String enquiryText, String campName) {
        // Create a new enquiry
        Enquiry newEnquiry = new Enquiry(enquiryText, student.getUserId(), "", "", false);
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
    
}
