import java.util.ArrayList;
import java.util.List;

public class CampCMController {
	private CampCM campCM;
	private Camp camp;
	
	public CampCMController(CampCM campCM, Camp camp) {
		this.campCM = campCM;
		this.camp = camp;
	}
	
	public String editSuggestion(int index, String newSuggestionText) {
		//gets list of suggestions from campCM object
		List<Suggestion> suggestions = campCM.getMySuggestions();
		
		//edits the suggestion based on the index give in input
		//not sure how else to select which suggestion to edit if there are multiple
		if (index >= 0 && index < suggestions.size()) {
	           	// Check if the index is within bounds of the list
	           	Suggestion suggestion = suggestions.get(index);
	            	suggestion.setSuggestionText(newSuggestionText);
	            	return "Suggestion at index " + index + " edited successfully.";
        	} 
		else {
            		return "Invalid index. Please provide a valid index.";
        	}
	}

	public String deleteSuggestion(int index) {
		List<Suggestion> suggestions = campCM.getMySuggestions();
		if (index >= 0 && index < suggestions.size()) {
            // Check if the index is valid
            suggestions.remove(index);
            campCM.setMySuggestions(suggestions);
            return "Suggestion at index " + index + " removed successfully.";
        } else {
            return "Invalid index. Please provide a valid index.";
        	}
	}

	public void addSuggestion(String suggestionText, String suggestedBy, boolean status) {
	        // Add a new suggestion
	        Suggestion newSuggestion = new Suggestion(suggestionText, suggestedBy, status);
	        List<Suggestion> suggestions = campCM.getMySuggestions();
	        suggestions.add(newSuggestion);
	        campCM.setMySuggestions(suggestions);
	        int points = getPoints();
	        System.out.println("Points added, current points: " + points);
   	 }

	public void replyEnquiry(int index, String reply) {
		List<Enquiry> enquiries = camp.getEnquiries();
		Enquiry enquiry = enquiries.get(index);
		enquiry.setReplyText(reply);
		enquiry.setReplyBy(campCM.getUserID());
	}

	public int getPoints() {
		int currentPoints = campCM.getMyPoints();
		currentPoints++;
		campCM.setPoints(currentPoints);
		return currentPoints;
	}

	public String generateList(int choice) {
		String csvFile = "report.csv";
		switch(choice) {
		case 1: //print only students
			try (FileWriter writer = new FileWriter(csvFile)) {
	            writer.append("Camp: ").append(camp.getCampName()).append('\n');
	            writer.append("Date: ").append(camp.getDates()).append('\n');
	            writer.append("Registration Deadline: ").append(camp.getRegistrationDeadline()).append('\n');
	            writer.append("User Group: ").append(camp.getUserGroup()).append('\n');
	            writer.append("Location: ").append(camp.getLocation()).append('\n');
	            writer.append("Description: ").append(camp.getDescription()).append('\n');
	            writer.append("Total Slots: ").append("" + camp.getTotalSlots()).append('\n');
	            writer.append("StaffIC: ").append(camp.getStaffIC()).append('\n');
	            writer.append("CampCommSlots: ").append("" + camp.getCampCommSlots()).append('\n');
	            writer.append("Visibility: ").append(String.valueOf(camp.getVisibility())).append('\n');

	            // Printing the list of students
	            writer.append("List of students: ").append('\n');
	            for (Student student : camp.getAttendees()) {
	                writer.append(student.getUserID()).append('\n');
	            }
	            writer.append("\n");
	            System.out.println("The csv has been created");
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
		case 2: //print only camp committee members
			try (FileWriter writer = new FileWriter(csvFile)) {
	            writer.append("Camp: ").append(camp.getCampName()).append('\n');
	            writer.append("Date: ").append(camp.getDates()).append('\n');
	            writer.append("Registration Deadline: ").append(camp.getRegistrationDeadline()).append('\n');
	            writer.append("User Group: ").append(camp.getUserGroup()).append('\n');
	            writer.append("Location: ").append(camp.getLocation()).append('\n');
	            writer.append("Description: ").append(camp.getDescription()).append('\n');
	            writer.append("Total Slots: ").append("" + camp.getTotalSlots()).append('\n');
	            writer.append("StaffIC: ").append(camp.getStaffIC()).append('\n');
	            writer.append("CampCommSlots: ").append("" + camp.getCampCommSlots()).append('\n');
	            writer.append("Visibility: ").append(String.valueOf(camp.getVisibility())).append('\n');
	            writer.append("List of committee Members: ").append('\n');
	            for (CampCM committeeMembers : camp.getCommitteeMembers()) {
	                writer.append(committeeMembers.getUserID()).append("\n");
	            }
	            writer.append("\n");
	            System.out.println("The csv has been created");
	        } catch(IOException e) {
	            e.printStackTrace();
		}
		case 3: //print all
	        try (FileWriter writer = new FileWriter(csvFile)) {
	            writer.append("Camp: ").append(camp.getCampName()).append('\n');
	            writer.append("Date: ").append(camp.getDates()).append('\n');
	            writer.append("Registration Deadline: ").append(camp.getRegistrationDeadline()).append('\n');
	            writer.append("User Group: ").append(camp.getUserGroup()).append('\n');
	            writer.append("Location: ").append(camp.getLocation()).append('\n');
	            writer.append("Description: ").append(camp.getDescription()).append('\n');
	            writer.append("Total Slots: ").append("" + camp.getTotalSlots()).append('\n');
	            writer.append("StaffIC: ").append(camp.getStaffIC()).append('\n');
	            writer.append("CampCommSlots: ").append("" + camp.getCampCommSlots()).append('\n');
	            writer.append("Visibility: ").append(String.valueOf(camp.getVisibility())).append('\n');
	
	            // Printing the list of students
	            writer.append("List of students: ").append('\n');
	            for (Student student : camp.getAttendees()) {
	                writer.append(student.getUserID()).append('\n');
	            }
	            writer.append("\n");
	            writer.append("List of committee Members: ").append('\n');
	            for (CampCM committeeMembers : camp.getCommitteeMembers()) {
	                writer.append(committeeMembers.getUserID()).append("\n");
	            }
	            writer.append("\n");
	            System.out.println("The csv has been created");
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
		}
	}

}
