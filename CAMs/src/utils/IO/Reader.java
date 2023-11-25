package utils.IO;

import entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


public class Reader {
    BufferedReader reader;
    public Reader() {

    }

    public static ArrayList<User> readUsers(String fpath) {
        System.out.println("Ingesting users...");
        ArrayList<User> users = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                String[] lst = line.split("_");
                String name = lst[0];
                String email = lst[1];
                String userID = email.split("@")[0];
                String faculty = lst[2];
                String password = lst[3];
                String userType = lst[4];
                int points = Integer.parseInt(lst[5]);


                switch (userType) {
                    case "Student" -> {
                        Student student = new Student(name, userID, email, password, faculty, userType);
                        users.add(student);
                    }
                    case "Staff" -> {
                        Staff staff = new Staff(name, userID, email, password, faculty, userType);
                        users.add(staff);
                    }
                    case "CampCM" -> {
                        CampCM campCM = new CampCM(name, userID, email, password, faculty, userType, points);
                        users.add(campCM);
                    }
                }

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<Enquiry> readEnquiries(String fpath) {
        System.out.println("Ingesting requests...");
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {
                String[] lst = line.split("_");
                String campID = lst[0];
                String enquiryText = lst[1];
                String enquiryBy = lst[2];
                String replyText = lst[3];
                String replyBy = lst[4];
                String enquiryID = lst[5];
                boolean status = Boolean.parseBoolean(lst[6]);
                Enquiry enquiry = new Enquiry(enquiryText, campID, enquiryBy, replyText, replyBy, status, enquiryID);
                enquiries.add(enquiry);
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enquiries;
    }

    public static ArrayList<Camp> readCamps(String fpath) {
        System.out.println("Ingesting Camps...");
        ArrayList<Camp> camps = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {
                String[] lst = line.split("_");

                // Name
                String campName = lst [0];

                // Dates
                String[] _dates = lst[1].split("\\|");
                ArrayList<Integer> dates = new ArrayList<>();
                for (String d: _dates) {
                    dates.add(Integer.parseInt(d));
                }

                // Reg Deadline
                Integer registrationDeadline = Integer.parseInt(lst[2]);

                // Faculty
                String userGroup = lst[3];

                // Location
                String location = lst[4];

                // Description
                String description = lst[5];

                // Total slots
                int totalSlots = Integer.parseInt(lst[6]);

                // StaffIC
                String staffIC = lst[7];

                // CampCommSlots
                int campCommSlots = Integer.parseInt(lst[8]);

                // Visibility
                boolean visibility = Boolean.parseBoolean(lst[9]);

                // Attendees
                String[] _attendees = lst[10].split("\\|");
                ArrayList<String> attendees = new ArrayList<>(Arrays.asList(_attendees));

                // CampComms
                String[] _campCMs = lst[10].split("\\|");
                ArrayList<String> campCMs = new ArrayList<>(Arrays.asList(_campCMs));

                // Suggestions
                String[] _suggestions = lst[10].split("\\|");
                ArrayList<String> suggestions = new ArrayList<>(Arrays.asList(_suggestions));

                // Blacklist
                String[] _blacklist = lst[14].split("\\|");
                ArrayList<String> blacklist = new ArrayList<>(Arrays.asList(_blacklist));

                // Camp ID
                String campID = lst[15];

                // Initalize camp object
                Camp camp = new Camp(campName, dates, registrationDeadline, userGroup, location, description, totalSlots, staffIC, campCommSlots, visibility, campID);
                camp.setAttendees(attendees);
                camp.setCommitteeMembers(campCMs);
                camp.setSuggestions(suggestions);
                camp.setBlackList(blacklist);
                camps.add(camp);

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return camps;
    }
public static ArrayList<Suggestion> readSuggestions(String fpath) {
    System.out.println("Ingesting Suggestions...");
    ArrayList<Suggestion> suggestions = new ArrayList<>();
    try {
        BufferedReader reader = new BufferedReader(new FileReader(fpath));
        String line = reader.readLine();

        while (line != null) {
            String[] lst = line.split("_");
            String campName = lst[0];
            String suggestionText = lst [1];
            String suggestedBy = lst[2];
            boolean status = Boolean.parseBoolean(lst[3]);
            String suggestionID = lst[4];
            Suggestion suggestion = new Suggestion(campName, suggestionText, suggestedBy, status, suggestionID);
            suggestions.add(suggestion);
            // read next line
            line = reader.readLine();
        }

        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return suggestions;
}
}
