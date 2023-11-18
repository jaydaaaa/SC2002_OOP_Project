package utils.IO;

import entity.Project;
import entity.Request;
import entity.User;

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
                if (!coordinators.contains(user.getUserId())) {
                    String toWrite = user.getName() + '_' + user.getEmail() +'_' + user.getFaculty() + '_' + user.getPassword() + '_' + user.getType() + '\n';
                    writer.write(toWrite);
                    if (Objects.equals(user.getType(), "Coordinator")) {
                        coordinators.add(user.getUserId());
                    }
                }

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeEnquiry(String fpath, ArrayList<Request> masterRequests) {
        System.out.println("Saving changes to requests file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Request request: masterRequests) {
                String toWrite = request.getType() + '_' + request.getStatus() + '_' + request.getDate() + '_' + request.getProjectID() + '_' +  request.getRequesteeID() + '_' + request.getUpdatedValue() + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeCamps(String fpath, ArrayList<Camp> masterProjects) {
        System.out.println("Saving changes to projects file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Camp camp: masterProjects) {
                String toWrite = Camp.getCampName() + "_" + Camp.getDates() + "_" + Camp.getRegistrationDeadline() + "_" + Camp.getUserGroup() + "_" +  Camp.getLocation() + "_" + Camp.getDescription() + "_" + Camp.getTotalSlots() + "_" +  Camp.getStaffIC() + "_" + Camp.getCampCommSlots() + "_" + Camp.getVisibility() + "_" + Camp.getAttendees() + "_" +  Camp.getCommitteeMembers() + "_" + Camp.getEnquiries() + "_" +  Camp.getSuggestions() + "_" + Camp.getBlacklist()'\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
