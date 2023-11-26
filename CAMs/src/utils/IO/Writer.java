package utils.IO;

import entity.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Writer class is responsible for writing data to files.
 * It provides static methods to write different types of data like Users, Enquiries, Camps, and Suggestions.
 * 
 * @author Group 2
 * @since 2023-11-26
 */
public class Writer {
    /**
     * Writes user data to a file.
     * @param fpath The path to the file.
     * @param masterUsers The list of users to be written to the file.
     */
    public static void writeUsers(String fpath, ArrayList<User> masterUsers) {
        System.out.println("Saving changes to user file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            int points;
            String campID;
            for (User user: masterUsers) {
                if (user.getType().equals("CampCM")) {
                    CampCM campCM = (CampCM) user;
                    points = campCM.getPoints();
                    campID = campCM.getCampID();
                } else {
                    points = 0;
                    campID = "";
                }
                String toWrite = user.getName() + '_' + user.getEmail() +'_' + user.getFaculty() + '_' +
                        user.getPassword() + '_' + user.getType() + '_' + points + '_' + campID + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes enquiry data to a file.
     * @param fpath The path to the file.
     * @param masterRequests The list of enquiries to be written to the file.
     */
    public static void writeEnquiry(String fpath, ArrayList<Enquiry> masterRequests) {
        System.out.println("Saving changes to requests file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Enquiry enquiry: masterRequests) {
                String toWrite = enquiry.getCampID() + '_' + enquiry.getEnquiryText() + '_' + enquiry.getEnquiryBy() + '_' + enquiry.getReplyText() + '_' + enquiry.getReplyBy() + '_' + enquiry.getEnquiryID() + '_' + enquiry.getStatus() + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    /**
     * Writes suggestion data to a file.
     * @param fpath The path to the file.
     * @param masterSuggestions The list of suggestions to be written to the file.
     */
    public static void writeSuggestions(String fpath, ArrayList<Suggestion> masterSuggestions) {
        System.out.println("Saving changes to suggestions file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Suggestion suggestion: masterSuggestions) {
                String toWrite = suggestion.getCampID() + '_' + suggestion.getVariableToChange() + '_' + suggestion.getSuggestionText() + '_' + suggestion.getSuggestedBy() + '_' + suggestion.getStatus() + '_' + suggestion.getSuggestionID() + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes camp data to a file.
     * @param fpath The path to the file.
     * @param masterCamps The list of camps to be written to the file.
     */
    public static void writeCamps(String fpath, ArrayList<Camp> masterCamps) {
        System.out.println("Saving changes to camps file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Camp camp: masterCamps) {
                int start_date = camp.getDates().get(0);
                int end_date = camp.getDates().get(1);
                String dates = start_date + "|" + end_date;
                StringBuilder blacklist = new StringBuilder();
                for (String studentID: camp.getBlacklist()) {
                    if (!studentID.equals("")) {
                        blacklist.append(studentID).append("|");
                    }
                }

                StringBuilder attendees = new StringBuilder();
                for (String studentID: camp.getAttendees()) {
                    if (!studentID.equals("")) {
                        attendees.append(studentID).append("|");
                    }
                }

                StringBuilder campCM = new StringBuilder();
                for (String studentID: camp.getCommitteeMembers()) {
                    if (!studentID.equals("")) {
                        campCM.append(studentID).append("|");
                    }
                }

                StringBuilder enquiries = new StringBuilder();
                for (String enquiryID: camp.getEnquiries()) {
                    if (!enquiryID.equals("")) {
                        enquiries.append(enquiryID).append("|");
                    }
                }

                StringBuilder suggestions = new StringBuilder();
                for (String suggestionID: camp.getSuggestions()) {
                    if (!suggestionID.equals("")) {
                        suggestions.append(suggestionID).append("|");
                    }
                }

                String toWrite = camp.getCampName() + "_" + dates + "_" + camp.getRegistrationDeadline() + "_"
                        + camp.getUserGroup() + "_" +  camp.getLocation() + "_" + camp.getDescription() + "_"
                        + camp.getTotalSlots() + "_" +  camp.getStaffIC() + "_" + camp.getCampCommSlots() + "_"
                        + camp.getVisibility() + "_" + attendees + "_" +  campCM
                        + "_" + enquiries + "_" +  suggestions + "_" + blacklist + "_" + camp.getCampID() + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
