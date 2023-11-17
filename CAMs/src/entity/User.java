package entity;

public class User {
    //private String Name;
    //private String email;
    private String userID;
    private String password;
    private String faculty;
    private String type;

    public User(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
    }

    public User() {};

    public String getUserId() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public String getType() {
        return this.type;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
