package controller;

import entity.*;

import java.util.ArrayList;

public class SuggestionController extends BaseController{
    ArrayList<Suggestion> masterSuggestions;
     public SuggestionController(CentralManager centralManager) {
        super(centralManager);
        this.masterSuggestions = this.centralManager.getMasterSuggestions();
    }

    public void addSuggestion(CampCM campCM, String suggestionText, Camp camp){
        String campName = camp.getCampName();
        String CampCMName = campCM.getName();
        // Create a new Suggestion
        Suggestion newSuggestion = new Suggestion(campName, suggestionText, CampCMName, false, "");

        // Add the new Suggestion to the masterSuggestions list
        this.masterSuggestions.add(newSuggestion);

        // Add the new Suggestion to the camp's list of suggestions
        camp.addSuggestion(newSuggestion.getSuggestionID());
    }

    public void editSuggestion(Suggestion suggestion, String newSuggestionText){
        suggestion.setSuggestionText(newSuggestionText);
    }

    public void deleteSuggestion(Suggestion suggestion){
         // Remove the Suggestion from the masterSuggestions list
        this.masterSuggestions.remove(suggestion);

        // Remove the Suggestion from the camp's list of suggestions
        for (Camp camp : this.centralManager.getMasterCamps()) {
        camp.getSuggestions().remove(suggestion);
    }
    }

    public void approveSuggestion(Suggestion suggestion){
        suggestion.setStatus(true);
    }

    public ArrayList<Suggestion> getAllSuggestions() {
        return this.masterSuggestions;
    }

    public ArrayList<Suggestion> getSuggestionsbyCamp(Camp camp){
        ArrayList<Suggestion> suggestionsList = new ArrayList<>();
        // Iterate over all suggestions
        for (Suggestion suggestion : this.masterSuggestions) {
            // If the suggestion's camp is the same as the provided camp
            if (suggestion.getCampName().equals(camp.getCampName())) {
                // Add the suggestion to the list
                suggestionsList.add(suggestion);
            }
        }
        return suggestionsList;
    }

    public ArrayList<Suggestion> getSuggestionsbyCampCM(CampCM campCM){
        ArrayList<Suggestion> suggestionsList = new ArrayList<>();
        // Iterate over all suggestions
        for (Suggestion suggestion : this.masterSuggestions) {
            // If the suggestion's camp committee member is the same as the provided camp committee member
            if (suggestion.getSuggestedBy().equals(campCM.getName())) {
                // Add the suggestion to the list
                suggestionsList.add(suggestion);
            }
        }

        return suggestionsList;
    }
}
