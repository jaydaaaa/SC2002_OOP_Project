public class User {

	private String userID;
	private String faculty;

    public User(String userID, String faculty) {
        this.userID = userID;
        this.faculty = faculty;
    }

	public String getFaculty() {
		return this.faculty;
	}

	public String getUserID() {
		return this.userID;
	}

}
