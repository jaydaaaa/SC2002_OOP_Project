import java.util.List;

public class CampCM extends Student {

	private int myPoints;
	private List<Suggestion> mySuggestions;
	
	public CampCM(String userID, String faculty, List<Enquiry> myEnquiries, List<Camp> myCamps, int myPoints, List<Suggestion> mySuggestions) {
        super(userID, faculty, myEnquiries, myCamps);
        this.myPoints = myPoints;
        this.mySuggestions = mySuggestions;
    }
	public int getMyPoints() {
		return this.myPoints;
	}
	public List<Suggestion> getMySuggestions(){
		return this.mySuggestions;
	}
}
