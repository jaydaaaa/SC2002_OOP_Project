package entity;

public class User {
    private String name;
    private String email;
    private String userID;
    private String password;
    private String faculty;
    private String type;

    // The default password is "password"
    public User(String name,String userID,String email,String password, String faculty, String userType) {
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.faculty = faculty;
        this.type = userType;
    }

    public User() {};

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserID() {
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
