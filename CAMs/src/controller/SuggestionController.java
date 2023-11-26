package controller;

import entity.*;

import java.util.ArrayList;

/**
 * The SuggestionController class is a controller for managing Suggestion entities.
 * It extends the BaseController class and provides functionality for handling camp-related suggestions.
 * @author Group 2
 * @since 2023-11-26
 */
public class SuggestionController extends BaseController{
	/**
     * The masterSuggestions field holds a reference to the master list of suggestions managed by the system.
     */
	ArrayList<Suggestion> masterSuggestions;
    
	/**
     * Constructs a SuggestionController with the specified CentralManager.
     *
     * @param centralManager The CentralManager instance to associate with the SuggestionController.
     */
	public SuggestionController(CentralManager centralManager) {
        super(centralManager);
        this.masterSuggestions = this.centralManager.getMasterSuggestions();
    }

	/**
     * Adds a new suggestion provided by a camp committee member for a specific camp.
     *
     * @param campCM            The camp committee member providing the suggestion.
     * @param variableToChange  The variable in the camp to be changed (e.g., "location" or "description").
     * @param suggestionText    The text of the suggestion.
     * @param camp              The camp associated with the suggestion.
     */
    public void addSuggestion(CampCM campCM, String variableToChange, String suggestionText, Camp camp){
        String campID = camp.getCampID();
        String campCMID = campCM.getUserID();
        // Create a new Suggestion
        Suggestion newSuggestion = new Suggestion(campID, variableToChange, suggestionText, campCMID, 0, "");

        // Add the new Suggestion to the masterSuggestions list
        this.masterSuggestions.add(newSuggestion);

        // Add the new Suggestion to the camp's list of suggestions
        camp.addSuggestion(newSuggestion.getSuggestionID());

        // Increment point
        campCM.setPoints(campCM.getPoints() + 1);
    }

    /**
     * Edits an existing suggestion's text if it has not been processed.
     *
     * @param suggestion         The suggestion to be edited.
     * @param newSuggestionText  The new text for the suggestion.
     * @return 1 if edit is successful, 0 if the suggestion has been processed.
     */
    public int editSuggestion(Suggestion suggestion, String newSuggestionText){
        if (suggestion.getStatus() == 0) {
            suggestion.setSuggestionText(newSuggestionText);
            return 1;
        } else {
            return 0; // cannot edit as suggestion has been processed
        }
    }

    /**
     * Deletes an existing suggestion if it has not been processed.
     *
     * @param suggestion The suggestion to be deleted.
     * @return 1 if deletion is successful, 0 if the suggestion has been processed.
     */
    public int deleteSuggestion(Suggestion suggestion){
         if (suggestion.getStatus() != 0) {
             return 0; // suggestion was processed already
         } else {
             // Remove the Suggestion from the masterSuggestions list
             this.masterSuggestions.remove(suggestion);

             // Remove the Suggestion from the camp's list of suggestions
             for (Camp camp : this.centralManager.getMasterCamps()) {
                 camp.getSuggestions().remove(suggestion);
             }
             return 1;
         }
    }

    /**
     * Processes a suggestion by updating the associated camp based on the staff member's approval or rejection.
     *
     * @param chosenSuggestion The suggestion to be processed.
     * @param approval          True if the suggestion is approved, false if rejected.
     */
    public void processSuggestion(Suggestion chosenSuggestion, boolean approval) {
        if (approval) {
            String variableToChange = chosenSuggestion.getVariableToChange();
            String campID = chosenSuggestion.getCampID();
            Camp camp = this.getCampController().getCampByID(campID);
            if (variableToChange.equals("location")) {
                camp.setLocation(chosenSuggestion.getSuggestionText());
            } else { // variableToChange.equals("description")
                camp.setDescription(chosenSuggestion.getSuggestionText());
            }
            chosenSuggestion.setStatus(1); // approved

            // increment campCM points on approval of suggestion
            CampCM campCM = this.getCampCMController().getStudentByID(chosenSuggestion.getSuggestedBy());
            campCM.setPoints(campCM.getPoints() + 1);
        } else {
            chosenSuggestion.setStatus(-1); // rejected
        }
    }

    /**
     * Retrieves the master list of all suggestions.
     *
     * @return An ArrayList of all suggestions.
     */
    public ArrayList<Suggestion> getAllSuggestions() {
        return this.masterSuggestions;
    }

    /**
     * Retrieves a list of suggestions associated with a specific camp.
     *
     * @param campID The ID of the camp for which suggestions are to be retrieved.
     * @return An ArrayList of suggestions associated with the specified camp.
     */
    public ArrayList<Suggestion> getSuggestionsbyCamp(String campID){
        ArrayList<Suggestion> suggestionsList = new ArrayList<>();
        // Iterate over all suggestions
        for (Suggestion suggestion : this.masterSuggestions) {
            // If the suggestion's camp is the same as the provided camp
            if (suggestion.getCampID().equals(campID)) {
                // Add the suggestion to the list
                suggestionsList.add(suggestion);
            }
        }
        return suggestionsList;
    }

    /**
     * Retrieves a list of suggestions sent to a staff member, optionally filtering only unprocessed suggestions.
     *
     * @param staffID           The ID of the staff member.
     * @param isNotProcessedOnly True if only unprocessed suggestions should be returned.
     * @return An ArrayList of suggestions sent to the specified staff member.
     */
    public ArrayList<Suggestion> findSugggestionsByStaff(String staffID, boolean isNotProcessedOnly) {
         ArrayList<Camp> myCamps = this.getCampController().getCampsByStaffID(staffID);
         ArrayList<Suggestion> interestedSuggestions = new ArrayList<>();
         for (Camp camp: myCamps) {
             for (Suggestion suggestion: this.getSuggestionsbyCamp(camp.getCampID()))
                 if (isNotProcessedOnly) {
                     if (suggestion.getStatus() == 0) {
                         interestedSuggestions.add(suggestion);
                     }
                 } else {
                     interestedSuggestions.add(suggestion);
                 }
         }
         return interestedSuggestions;
    }

    /**
     * Retrieves a list of suggestions sent by a specific camp committee member.
     *
     * @param userID The ID of the camp committee member.
     * @return An ArrayList of suggestions sent by the specified camp committee member.
     */
    public ArrayList<Suggestion> getSuggestionsbyCampCM(String userID){
        ArrayList<Suggestion> suggestionsList = new ArrayList<>();
        // Iterate over all suggestions
        for (Suggestion suggestion : this.masterSuggestions) {
            // If the suggestion's camp committee member is the same as the provided camp committee member
            if (suggestion.getSuggestedBy().equals(userID)) {
                // Add the suggestion to the list
                suggestionsList.add(suggestion);
            }
        }

        return suggestionsList;
    }
}
