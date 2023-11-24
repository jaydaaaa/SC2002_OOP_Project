package utils.IO;

import entity.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Writer {
    public static void writeUsers(String fpath, ArrayList<User> masterUsers) {
        System.out.println("Saving changes to user file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            ArrayList<String> coordinators = new ArrayList<>();
            for (User user: masterUsers) {
                if (!coordinators.contains(user.getUserID())) {
                    String toWrite = user.getName() + '_' + user.getEmail() +'_' + user.getFaculty() + '_' + user.getPassword() + '_' + user.getType() + '\n';
                    writer.write(toWrite);
                    if (Objects.equals(user.getType(), "Coordinator")) {
                        coordinators.add(user.getUserID());
                    }
                }

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeEnquiry(String fpath, ArrayList<Enquiry> masterRequests) {
        System.out.println("Saving changes to requests file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Enquiry enquiry: masterRequests) {
                String toWrite = enquiry.getCampName() + '_' + enquiry.getEnquiryText() + '_' + enquiry.getEnquiryBy() + '_' + enquiry.getReplyText() + '_' + enquiry.getReplyBy() + '_' +  enquiry.getStatus() + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeSuggestions(String fpath, ArrayList<Suggestion> masterSuggestions) {
        System.out.println("Saving changes to suggestions file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Suggestion suggestion: masterSuggestions) {
                String toWrite = suggestion.getCampName() + '_' + suggestion.getSuggestionText() + '_' + suggestion.getSuggestedBy() + '_' + suggestion.getStatus() + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeCamps(String fpath, ArrayList<Camp> masterCamps) {
        System.out.println("Saving changes to camps file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Camp camp: masterCamps) {
                String toWrite = camp.getCampName() + "_" + camp.getDates() + "_" + camp.getRegistrationDeadline() + "_" + camp.getUserGroup() + "_" +  camp.getLocation() + "_" + camp.getDescription() + "_" + camp.getTotalSlots() + "_" +  camp.getStaffIC() + "_" + camp.getCampCommSlots() + "_" + camp.getVisibility() + "_" + camp.getAttendees() + "_" +  camp.getCommitteeMembers() + "_" + camp.getEnquiries() + "_" +  camp.getSuggestions() + "_" + camp.getBlacklist() + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
