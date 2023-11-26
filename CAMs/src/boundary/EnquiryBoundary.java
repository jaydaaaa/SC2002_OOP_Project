package boundary;

/**
 * The EnquiryBoundary class represents an enquiry in the system.
 * An enquiry can be made by a student, staff, or camp committee member.
 * This class provides methods for handling enquiry operations such as viewing and printing enquiries.
 * @author Group 2
 * @since 2023-11-26
 */

import entity.Camp;
import entity.CentralManager;
import entity.Enquiry;

import java.util.ArrayList;

public class EnquiryBoundary extends BaseBoundary {
    /**
     * Constructor for EnquiryBoundary.
     * @param centralManager The central manager controlling the system.
     */
    public EnquiryBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    /**
     * Prints the format for displaying enquiries.
     */
    public void printEnquiryFormat() {
        String format = "[Index]. [Camp Name] | [Enquiry Text] | [Enquired By] | [Status] | [Replied By]";
        System.out.println(format);
    }

    /**
     * This method prints a single enquiry line.
     * It retrieves the camp name, enquiry user name, reply status, and replied by information from the enquiry.
     * Then, it formats and prints this information.
     * @param index The index of the enquiry.
     * @param enquiry The enquiry to print.
     */
    public void printEnquiryLine(int index, Enquiry enquiry) {
        String campName = this.getCampController().getCampByID(enquiry.getCampID()).getCampName();
        String enquiryUserName = this.getUserController().getUserByID(enquiry.getEnquiryBy()).getName();
        String replyStatus;
        if (enquiry.getStatus()) {
            replyStatus = "Replied";
        } else {
            replyStatus = "Pending";
        }
        String repliedBy;
        if (enquiry.getReplyBy().equals("")) {
            repliedBy = "-";
        } else {
            repliedBy = this.getUserController().getUserByID(enquiry.getReplyBy()).getName();
        }
        String toPrint = index + " | " + campName + " | " + enquiry.getEnquiryText() + " | " + enquiryUserName +
                " | " + replyStatus + " | " + repliedBy;
        System.out.println(toPrint);
    }

    /**
     * This method prints a list of enquiries.
     * It first prints the format for displaying enquiries.
     * If there are no enquiries, it prints a message indicating this.
     * Otherwise, it prints each enquiry in the list.
     * @param enquiries The list of enquiries to print.
     */
    public void printEnquiries(ArrayList<Enquiry> enquiries) {
        this.printEnquiryFormat();
        if (enquiries.size() == 0) {
            System.out.println("--------------------- No Enquiries ---------------------");
        } else {
            int counter = 0;
            for (Enquiry enquiry: enquiries) {
                counter += 1;
                this.printEnquiryLine(counter, enquiry);
            }
        }
    }

    /**
     * This method displays enquiries by Camp.
     * It first prints a header, then retrieves the staff's ID and the camps associated with the staff.
     * Then, for each camp, it retrieves the enquiries associated with the camp and prints them.
     */
    public void viewEnquiriesByCamp() {
        System.out.println("Displaying all requests: ");
        String StaffId = this.getStaffController().getCurrentStaff().getUserID();
        ArrayList<Camp> camps = this.getCampController().getCampsByStaffID(StaffId);
        this.printEnquiryFormat();
        for (Camp camp: camps) {
            ArrayList<Enquiry> enquiries = this.getEnquiryController().getEnquiryByCamp(camp.getCampID());
            this.printEnquiries(enquiries);
        }
    }


}
