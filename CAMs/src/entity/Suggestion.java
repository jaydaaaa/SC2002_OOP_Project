package entity;
public class Suggestion {

    private String campName;
    private String suggestionText;
    private String suggestedBy;
    private boolean status;

    public Suggestion(String campName, String suggestionText, String suggestedBy, boolean status) {
        this.campName = campName;
        this.suggestionText = suggestionText;
        this.suggestedBy = suggestedBy;
        this.status = false;
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
