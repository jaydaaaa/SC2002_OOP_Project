package entity;
import java.util.ArrayList;
import java.util.List;

public class CampCM extends Student {

	private int myPoints;
	private ArrayList<Suggestion> mySuggestions;

	public CampCM(String name, String userID, String email, String password, String faculty, String userType, int points) {
		super(name,userID,email, password, faculty, userType);
		mySuggestions = new ArrayList<>();
		this.myPoints = points;
	}
	public int getPoints() {
		return this.myPoints;
	}
	public ArrayList<Suggestion> getMySuggestions(){
		return this.mySuggestions;
	}
	public void setPoints(int myPoints) {
		this.myPoints = myPoints;
	}
	public void setMySuggestions(ArrayList<Suggestion> mySuggestions) {
		this.mySuggestions = mySuggestions;
	}
}
