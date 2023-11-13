package boundary;
import java.util.List;

import entity.Enquiry;
public class StudentBoundary implements EnquiryBoundary, CampBoundary {

	protected List<Enquiry> myEnquiries;
	protected List<Camp> registeredCamp;

	public void displayMyEnquiries() {
		// TODO - implement StudentDisplay.displayMyEnquiries
		throw new UnsupportedOperationException();
	}

	public void displayMyCamps() {
		// TODO - implement StudentDisplay.displayMyCamps
		throw new UnsupportedOperationException();
	}

	public void displayAllCamps() {
		// TODO - implement StudentDisplay.displayAllCamps
		throw new UnsupportedOperationException();
	}
}