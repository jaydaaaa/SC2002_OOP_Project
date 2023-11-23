package controller;

import entity.*;

import java.util.ArrayList;
import java.util.Objects;

public class SuggestionController extends BaseController{
    ArrayList<Suggestion> masterSuggestions;
     public SuggestionController(CentralManager centralManager) {
        super(centralManager);
        this.masterSuggestions = this.centralManager.getMasterSuggestions();
    }

    public void addSuggestion(){

    }

    public void editSuggestion(){

    }

    public void deleteSuggestion(){

    }

    public void approveSuggestion(){

    }

    public ArrayList<Suggestion> getAllSuggestions() {
        return this.masterSuggestions;
    }

    public ArrayList<Suggestion> getSuggestionsbyCamp(Camp camp){
        ArrayList<Suggestion> suggestions = new ArrayList<>();
         //TODO - get all suggestions for a particular camp 
        return suggestions;
    }

     public ArrayList<Suggestion> getSuggestionsbyCampCM(CampCM campCM){
        ArrayList<Suggestion> suggestions = new ArrayList<>();
         //TODO - get all suggestions by a particular CampCm 
        return suggestions;
    }
}
