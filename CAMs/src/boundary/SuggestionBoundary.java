package boundary;
import entity.Camp;
import entity.CampCM;
import entity.CentralManager;
import entity.Suggestion;

import java.util.ArrayList;
import java.util.Objects;

public class SuggestionBoundary extends BaseBoundary {
    public SuggestionBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    public void printSuggestionFormat() {
        System.out.println("[Index]. [Camp] | [Suggestor] | [Attribute to Edit] | [Suggested Change] | [Status]");
    }
    public void printSuggestionLine(Suggestion suggestion, int index) {
        String campName = this.getCampController().getCampByID(suggestion.getCampID()).getCampName();
        String userName = this.getUserController().getUserByID(suggestion.getSuggestedBy()).getName();
        String toPrint = index + ". " + campName + " | " + userName + " | " + suggestion.getVariableToChange() + " | "
                + suggestion.getSuggestionText() + " | " + suggestion.getStatus();
        System.out.println(toPrint);
    }

    public void printSuggestionList(ArrayList<Suggestion> suggestions) {
        this.printSuggestionFormat();
        int counter = 0;
        if (suggestions.size() == 0) {
            System.out.println("------------------ No Suggestions ------------------");
        } else {
            counter += 1;
            for (Suggestion suggestion: suggestions) {
                this.printSuggestionLine(suggestion, counter);
            }
        }
    }

    public void addSuggestion(Camp camp, CampCM campCM) {
        String variable_to_change;
        while (true) {
            int choice  = this.getInt("""
                    Please enter either 1 or 2, corresponding the following options:
                    1. Description of Camp
                    2. Location of Camp
                    """);
            if (!(choice == 1 || choice == 2)) {
                System.out.print("Invalid option.");
            } else {
                if (choice == 1) {
                    variable_to_change = "description";
                } else { // choice == 2
                    variable_to_change = "location";
                }
                break;
            }

        }
        String suggestionText = this.getLine("Please enter proposed changes to camp" + variable_to_change);
        this.getSuggestionController().addSuggestion(campCM, variable_to_change, suggestionText, camp);
    }

    public void editSuggestion(Suggestion suggestion) {
        String suggestionText = this.getLine("Please enter your new proposed changes.");
        int success = this.getSuggestionController().editSuggestion(suggestion, suggestionText);
        if (success == 0) {
            System.out.println("Unable to edit suggestion as it was already processed.");
        } else {
            System.out.println("Suggestion edited succesfully.");
        }
    }
}
