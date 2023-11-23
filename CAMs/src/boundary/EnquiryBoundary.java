package boundary;
import entity.CentralManager;
import entity.Enquiry;

import java.util.ArrayList;
import java.util.Objects;

public class EnquiryBoundary extends BaseBoundary {
    public EnquiryBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    // View enquiries by Camp
    public void viewEnquiriesByCamp() {
        System.out.println("Displaying all requests: ");
        String StaffId = this.getStaffController().getCurrentStaff().getUserId();
        ArrayList<Camp> camps = this.getCampController().getCampsByStaffId(StaffId);
        for (Camp camp: camps) {
            this.printEnquiryFormat(camp);
            ArrayList<Enquiry> enquiries = this.getEnquiryController().getEnquiryByCamp(camp);
            this.printEnquiries(enquiries);
        }

    }

	
}
