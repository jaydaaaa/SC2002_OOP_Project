package entity;

import java.util.ArrayList;

public class Student extends User {
    public Student(String name, String userID, String email, String password, String faculty, String userType) {
        super(name, userID,email, password, faculty, userType);
    }

    public String getUserID() {
        return super.getUserID();
    }
}
