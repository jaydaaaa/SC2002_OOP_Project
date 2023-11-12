public class Suggestion {

	private String suggestionText;
	private String suggestedBy;
	private boolean status;

	public Suggestion(String suggestionText, String suggestedBy, boolean status) {
        	this.suggestionText = suggestionText;
        	this.suggestedBy = suggestedBy;
        	this.status = status;
	}

	public String getSuggestionText() {
        	return suggestionText;
    	}

	public String getSuggestedBy() {
        	return suggestedBy;
    	}

	public boolean getStatus() {
        	return status;
    	}

}
