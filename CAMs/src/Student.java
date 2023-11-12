import java.util.List;

public class Student extends User {

    protected List<Enquiry> myEnquiries;
    protected List<Camp> myCamps;

    public Student(String userID, String faculty, List<Enquiry> myEnquiries, List<Camp> myCamps) {
        super(userID, faculty);
        this.myEnquiries = myEnquiries;
        this.myCamps = myCamps;
    }
	
    public List<Enquiry> getMyEnquiries() {
        return myEnquiries;
    }

    public List<Camp> getMyCamps() {
        return myCamps;
    }
}
