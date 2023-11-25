package boundary;
import entity.Camp;
import entity.CentralManager;
import entity.Enquiry;

import java.util.ArrayList;

public class EnquiryBoundary extends BaseBoundary {
    public EnquiryBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    public void printEnquiryFormat() {
        String format = "[Index]. [Camp Name] | [Enquiry Text] | [Enquired By] | [Status] | [Replied By]";
        System.out.println(format);
    }

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
            repliedBy = this.getStudentController().getStudentByID(enquiry.getReplyBy()).getName();
        }
        String toPrint = index + " | " + campName + " | " + enquiry.getEnquiryText() + " | " + enquiryUserName +
                " | " + replyStatus + " | " + repliedBy;
        System.out.println(toPrint);
    }

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

    // View enquiries by Camp
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
