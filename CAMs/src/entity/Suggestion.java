package entity;

import java.util.UUID;

public class Suggestion {

    private String campName;
    private String suggestionText;
    private String suggestedBy;
    private boolean status;
    private String suggestionID;

    public Suggestion(String campName, String suggestionText, String suggestedBy, boolean status, String suggestionID) {
        this.campName = campName;
        this.suggestionText = suggestionText;
        this.suggestedBy = suggestedBy;
        this.status = false;
        if (suggestionID.equals("")) {
            this.suggestionID =  UUID.randomUUID().toString();
        } else {
            this.suggestionID = suggestionID;
        }
    }

    public String getSuggestionID() {
        return this.suggestionID;
    }

    public String getCampName() {
        return campName;
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

    // Setter methods
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setSuggestionText(String newSuggestionText){
        this.suggestionText=newSuggestionText;
    }
}
