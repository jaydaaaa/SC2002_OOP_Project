package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Student extends User {

    private ArrayList<Enquiry> myEnquiries; //since this array is destroyed when student logs out, we need to write to masterEnquiry list instead
    private ArrayList<Camp> myCamps;        //if that is the case, should we just write to masterEnquiry with userID and campName instead for searching?

    public Student(String name, String userID, String email, String password, String faculty, String type) {
        super(name,userID,email, password, faculty, type);
        myEnquiries = new ArrayList<>();
        myCamps = new ArrayList<>();
    }
	
    public ArrayList<Enquiry> getMyEnquiries() {
        return this.myEnquiries;
    }

    public String getUserID() {
        return super.getUserID();
    }
    public void setMyEnquiries(ArrayList<Enquiry> myEnquiries) {
        this.myEnquiries = myEnquiries;
    }

    public ArrayList<Camp> getMyCamps() {
        return this.myCamps;
    }
}
