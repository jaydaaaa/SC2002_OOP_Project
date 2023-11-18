package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Student extends User {

    private ArrayList<Enquiry> myEnquiries;
    private ArrayList<Camp> myCamps;

    public Student(String name, String userID, String email, String password, String faculty, String type) {
        super(name,userID,email, password, faculty, type);
        myEnquiries = new ArrayList<>();
        myCamps = new ArrayList<>();
    }
	
    public ArrayList<Enquiry> getMyEnquiries() {
        return myEnquiries;
    }

    public ArrayList<Camp> getMyCamps() {
        return myCamps;
    }
}
