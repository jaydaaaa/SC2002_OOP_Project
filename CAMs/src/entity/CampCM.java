package entity;
import java.util.ArrayList;
import java.util.List;

public class CampCM extends Student {

	private int myPoints;
	private List<Suggestion> mySuggestions;
	
	public CampCM(String name, String userID, String email, String password, String faculty, String type) {
		super(name,userID,email, password, faculty, type);
		mySuggestions = new ArrayList<>();
		this.myPoints = 0;
	}
	public int getMyPoints() {
		return this.myPoints;
	}
	public List<Suggestion> getMySuggestions(){
		return this.mySuggestions;
	}
	public void setPoints(int myPoints) {
		this.myPoints = myPoints;
	}
	public void setMySuggestions(List<Suggestion> mySuggestions) {
		this.mySuggestions = mySuggestions;
	}
}
