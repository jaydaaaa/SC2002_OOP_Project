import java.util.ArrayList;
import java.util.List;

public class CampCMController {
	private CampCM campCM;
	private Camp camp;
	
	public CampCMController(CampCM campCM, Camp camp) {
		this.campCM = campCM;
		this.camp = camp;
	}
	
	public String editSuggestion(int index, String newSuggestionText) {
		//gets list of suggestions from campCM object
		List<Suggestion> suggestions = campCM.getMySuggestions();
		
		//edits the suggestion based on the index give in input
		//not sure how else to select which suggestion to edit if there are multiple
		if (index >= 0 && index < suggestions.size()) {
            // Check if the index is within bounds of the list
            Suggestion suggestion = suggestions.get(index);
            suggestion.setSuggestionText(newSuggestionText);
            return "Suggestion at index " + index + " edited successfully.";
        } else {
            return "Invalid index. Please provide a valid index.";
        }
	}

	public String deleteSuggestion(int index) {
		List<Suggestion> suggestions = campCM.getMySuggestions();
		if (index >= 0 && index < suggestions.size()) {
            // Check if the index is valid
            suggestions.remove(index);
            campCM.setMySuggestions(suggestions);
            return "Suggestion at index " + index + " removed successfully.";
        } else {
            return "Invalid index. Please provide a valid index.";
        }
	}

	public void addSuggestion(String suggestionText, String suggestedBy, boolean status) {
        // Add a new suggestion
        Suggestion newSuggestion = new Suggestion(suggestionText, suggestedBy, status);
        List<Suggestion> suggestions = campCM.getMySuggestions();
        suggestions.add(newSuggestion);
        campCM.setMySuggestions(suggestions);
        int points = getPoints();
        System.out.println("Points added, current points: " + points);
    }

	public void replyEnquiry(int index, String reply) {
		List<Enquiry> enquiries = camp.getEnquiries();
		Enquiry enquiry = enquiries.get(index);
		enquiry.setReplyText(reply);
		enquiry.setReplyBy(campCM.getUserID());
	}

	public int getPoints() {
		int currentPoints = campCM.getMyPoints();
		currentPoints++;
		campCM.setPoints(currentPoints);
		return currentPoints;
	}

	public String generateList(int choice) {
		// TODO - implement CampCommMemController.generateList
				throw new UnsupportedOperationException();	
	}

}
