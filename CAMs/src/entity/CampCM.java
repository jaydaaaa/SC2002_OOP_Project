package entity;

import java.util.ArrayList;

/**
 * The CampCM class represents a student who is a committee member for a camp in the system.
 * It extends the Student class.
 * @author Group 2
 * @since 2023-11-20
 */
public class CampCM extends Student {

	/**
	 * The points associated with camp committee member.
	 */
	private int myPoints;
	/**
	 * The camp ID.
	 */
	private String campID;
	/**
	 * The list of suggestions created by the camp committee member.
	 */
	private ArrayList<Suggestion> mySuggestions;

	/**
	 * Constructs a new CampCM object with the specified details.
	 *
	 * @param name      The name of the committee member.
	 * @param userID    The user ID of the committee member.
	 * @param email     The email address of the committee member.
	 * @param password  The password of the committee member.
	 * @param faculty   The faculty of the committee member.
	 * @param userType  The user type of the committee member.
	 * @param points    The points of the committee member.
	 * @param campID    The camp ID for which the committee member is associated.
	 */
	public CampCM(String name, String userID, String email, String password, String faculty, String userType, int points, String campID) {
		super(name, userID, email, password, faculty, userType);
		mySuggestions = new ArrayList<>();
		this.myPoints = points;
		this.campID = campID;
	}

	/**
	 * Gets the camp ID associated with the committee member.
	 * @return The camp ID.
	 */
	public String getCampID() {
		return this.campID;
	}

	/**
	 * Gets the points associated with the committee member.
	 * @return The points of a committee member.
	 */
	public int getPoints() {
		return this.myPoints;
	}

	/**
	 * Gets the list of suggestions made by the committee member.
	 * @return The list of suggestions.
	 */
	public ArrayList<Suggestion> getMySuggestions() {
		return this.mySuggestions;
	}

	/**
	 * Sets the points associated with the committee member.
	 * @param myPoints The new points value.
	 */
	public void setPoints(int myPoints) {
		this.myPoints = myPoints;
	}

	/**
	 * Sets the list of suggestions made by the committee member.
	 * @param mySuggestions The new list of suggestions.
	 */
	public void setMySuggestions(ArrayList<Suggestion> mySuggestions) {
		this.mySuggestions = mySuggestions;
	}
}
