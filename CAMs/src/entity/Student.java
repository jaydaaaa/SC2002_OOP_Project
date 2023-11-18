package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Student extends User {

    protected ArrayList<Enquiry> myEnquiries;
    protected ArrayList<Camp> myCamps;

    public Student(String name, String userID, String email, String password, String type) {
        super(userID, faculty);
        this.myEnquiries = myEnquiries;
        this.myCamps = myCamps;
    }
	
    public ArrayList<Enquiry> getMyEnquiries() {
        return myEnquiries;
    }

    public ArrayList<Camp> getMyCamps() {
        return myCamps;
    }
}
