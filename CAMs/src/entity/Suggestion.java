package entity;

import java.util.UUID;

public class Suggestion {

    private String campID;
    private String variableToChange;
    private String suggestionText;
    private String suggestedBy;
    private int status;
    private String suggestionID;

    public Suggestion(String campID, String variableToChange, String suggestionText, String suggestedBy, int status, String suggestionID) {
        this.campID = campID;
        this.suggestionText = suggestionText;
        this.suggestedBy = suggestedBy;
        this.status = status;
        if (suggestionID.equals("")) {
            this.suggestionID =  UUID.randomUUID().toString();
        } else {
            this.suggestionID = suggestionID;
        }
        this.variableToChange = variableToChange;
    }

    public String getSuggestionID() {
        return this.suggestionID;
    }

    public String getCampID() {
        return this.campID;
    }

    public String getSuggestionText() {
        return suggestionText;
    }

    public String getSuggestedBy() {
        return suggestedBy;
    }

    public int getStatus() {
        return status;
    }

    public String getVariableToChange() {
        return this.variableToChange;
    }

    // Setter methods
    public void setStatus(int status) {
        this.status = status;
    }

    public void setSuggestionText(String newSuggestionText){
        this.suggestionText=newSuggestionText;
    }
    public void setVariableToChange(String newVariable) {
        this.variableToChange = newVariable;
    }
}
