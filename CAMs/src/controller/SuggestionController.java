package controller;

import entity.*;

import java.util.ArrayList;

public class SuggestionController extends BaseController{
    ArrayList<Suggestion> masterSuggestions;
     public SuggestionController(CentralManager centralManager) {
        super(centralManager);
        this.masterSuggestions = this.centralManager.getMasterSuggestions();
    }

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

    public int editSuggestion(Suggestion suggestion, String newSuggestionText){
        if (suggestion.getStatus() == 0) {
            suggestion.setSuggestionText(newSuggestionText);
            return 1;
        } else {
            return 0; // cannot edit as suggestion has been processed
        }
    }

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

    public ArrayList<Suggestion> getAllSuggestions() {
        return this.masterSuggestions;
    }

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
