package entity;

import java.util.UUID;

/**
 * The Suggestion class represents a suggestion by a class committee member.
 * This suggestion is specific to each camp.
 * @author Group 2
 * @since 2023-11-26
 */
public class Suggestion {

    /**
     * Suggestion of that specific campID.
     */
    private String campID;
    /**
     * Whether suggestion is based on location or description.
     */
    private String variableToChange;
    /**
     * The suggestion text.
     */
    private String suggestionText;
    /**
     * The name of camp committee member.
     */
    private String suggestedBy;
    /**
     * Whether a suggestion has been replied of pending.
     */
    private int status;
    /**
     * The suggestion ID.
     */
    private String suggestionID;

    /**
     * Constructs a new Suggestion object with the specified details.
     *
     * @param campID            The campaign ID associated with the suggestion.
     * @param variableToChange The variable to be changed(location or description) based on the suggestion.
     * @param suggestionText    The text of the suggestion.
     * @param suggestedBy       The user who suggested the change.
     * @param status            The status of the suggestion.
     * @param suggestionID      The unique identifier for the suggestion.
     */
    public Suggestion(String campID, String variableToChange, String suggestionText, String suggestedBy, int status, String suggestionID) {
        this.campID = campID;
        this.suggestionText = suggestionText;
        this.suggestedBy = suggestedBy;
        this.status = status;
        if (suggestionID.equals("")) {
            this.suggestionID = UUID.randomUUID().toString();
        } else {
            this.suggestionID = suggestionID;
        }
        this.variableToChange = variableToChange;
    }

    /**
     * Gets the suggestion ID.
     * @return The suggestion ID.
     */
    public String getSuggestionID() {
        return this.suggestionID;
    }

    /**
     * Gets the camp ID associated with the suggestion.
     * @return The camp ID.
     */
    public String getCampID() {
        return this.campID;
    }

    /**
     * Gets the text of the suggestion.
     * @return The text of the suggestion.
     */
    public String getSuggestionText() {
        return suggestionText;
    }

    /**
     * Gets the camp committee member who suggested the change.
     * @return The camp committee member who suggested the change.
     */
    public String getSuggestedBy() {
        return suggestedBy;
    }

    /**
     * Gets the status of the suggestion.
     * @return The status of the suggestion.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets the variable to be changed(location or description) based on the suggestion.
     * @return The variable to be changed.
     */
    public String getVariableToChange() {
        return this.variableToChange;
    }

    /**
     * Sets the status of the suggestion.
     * @param status The new status to set.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Sets the text of the suggestion.
     * @param newSuggestionText The new suggestion text to set.
     */
    public void setSuggestionText(String newSuggestionText) {
        this.suggestionText = newSuggestionText;
    }

    /**
     * Sets the variable to be changed(location or description) based on the suggestion.
     * @param newVariable The new variable to set.
     */
    public void setVariableToChange(String newVariable) {
        this.variableToChange = newVariable;
    }
}
