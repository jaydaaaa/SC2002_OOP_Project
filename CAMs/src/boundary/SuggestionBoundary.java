package boundary;
import entity.Camp;
import entity.CampCM;
import entity.CentralManager;
import entity.Suggestion;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The SuggestionBoundary class represents a suggestion in the system.
 * A suggestion can be made by a student, staff, or camp committee member.
 * This class provides methods for handling suggestion operations such as adding and editing suggestions.
 * @author Group 2
 * @since 2023-11-26
 */
public class SuggestionBoundary extends BaseBoundary {
    /**
     * Constructor for SuggestionBoundary.
     * @param centralManager The central manager controlling the system.
     */
    public SuggestionBoundary(CentralManager centralManager) {
        super(centralManager);
    }

    /**
     * Prints the format for displaying suggestions.
     */
    public void printSuggestionFormat() {
        System.out.println("[Index]. [Camp] | [Suggestor] | [Attribute to Edit] | [Suggested Change] | [Status]");
    }

    /**
     * This method prints a single suggestion line.
     * It retrieves the camp name, user name, and status from the suggestion.
     * Then, it formats and prints this information.
     * @param suggestion The suggestion to print.
     * @param index The index of the suggestion.
     */
    public void printSuggestionLine(Suggestion suggestion, int index) {
        String campName = this.getCampController().getCampByID(suggestion.getCampID()).getCampName();
        String userName = this.getUserController().getUserByID(suggestion.getSuggestedBy()).getName();
        String status = "test";
        if (suggestion.getStatus()==0){
           status="Pending";
        }
        else if(suggestion.getStatus()==1){
            status="Approved";
        }
        else{
            status="Rejected";
        }

        String toPrint = index + ". " + campName + " | " + userName + " | " + suggestion.getVariableToChange() + " | "
                + suggestion.getSuggestionText() + " | " + status;
        System.out.println(toPrint);
    }

    /**
     * This method prints a list of suggestions.
     * It first prints the format for displaying suggestions.
     * If there are no suggestions, it prints a message indicating this.
     * Otherwise, it prints each suggestion in the list.
     * @param suggestions The list of suggestions to print.
     */
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

    /**
     * This method allows a CampCM to add a suggestion for a camp.
     * It first prompts the user to choose the attribute of the camp they want to suggest changes for (either the description or the location).
     * Then, it prompts the user to enter their proposed changes.
     * The suggestion is then added to the system.
     * @param camp The camp to suggest changes for.
     * @param campCM The CampCM making the suggestion.
    */
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
        String suggestionText = this.getLine("Please enter proposed changes to camp " + variable_to_change);
        this.getSuggestionController().addSuggestion(campCM, variable_to_change, suggestionText, camp);
    }

    /**
     * This method allows a user to edit a suggestion.
     * It first prompts the user to enter their new proposed changes.
     * Then, it calls the `editSuggestion()` method of `SuggestionController` to update the suggestion.
     * If the suggestion has already been processed, an error message is printed and the suggestion is not edited.
     * If the edit is successful, a confirmation message is printed.
     * @param suggestion The suggestion to edit.
     */
    public void editSuggestion(Suggestion suggestion) {
        String suggestionText = this.getLine("Please enter your new proposed changes: ");
        int success = this.getSuggestionController().editSuggestion(suggestion, suggestionText);
        if (success == 0) {
            System.out.println("Unable to edit suggestion as it was already processed.");
        } else {
            System.out.println("Suggestion edited succesfully.");
        }
    }
}
