package utils.IO;

import entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * The Reader class is responsible for reading data from files.
 * It provides static methods to read different types of data like Users, Enquiries, Camps, and Suggestions.
 * 
 * @author Group 2
 * @since 2023-11-20
 */
public class Reader {
    BufferedReader reader;
    /**
     * Default constructor for the Reader class.
     */
    public Reader() {

    }

    /**
     * Reads user data from a file.
     * @param fpath The path to the file.
     * @return A list of users.
     */
    public static ArrayList<User> readUsers(String fpath) {
        System.out.println("Ingesting Users...");
        ArrayList<User> users = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {
                //System.out.println(line);
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
                        String campID = lst[6];
                        CampCM campCM = new CampCM(name, userID, email, password, faculty, userType, points, campID);
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

    /**
     * Reads enquiry data from a file.
     * @param fpath The path to the file.
     * @return A list of enquiries.
     */
    public static ArrayList<Enquiry> readEnquiries(String fpath) {
        System.out.println("Ingesting Enquiries...");
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {

                if (line.trim().isEmpty()) {
                    line = reader.readLine();
                    continue;
                }

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

    /**
     * Reads camp data from a file.
     * @param fpath The path to the file.
     * @return A list of camps.
     */
    public static ArrayList<Camp> readCamps(String fpath) {
        System.out.println("Ingesting Camps...");
        ArrayList<Camp> camps = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {

                if (line.trim().isEmpty()) {
                    line = reader.readLine();
                    continue;
                }

                String[] lst = line.split("_");

                // Name
                String campName = lst[0];

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
                ArrayList<String> attendees = new ArrayList<>();
                String[] _attendees = lst[10].split("\\|");
                for (String a: _attendees) {
                    if (!a.equals("")) {
                        attendees.add(a);
                    }
                }

                // CampComms
                ArrayList<String> campCMs = new ArrayList<>();
                String[] _campCMs = lst[11].split("\\|");
                for (String c: _campCMs) {
                    if (!c.equals("")) {
                        campCMs.add(c);
                    }
                }

                // Enquiries
                ArrayList<String> enquiries = new ArrayList<>();
                String[] _enquiries = lst[12].split("\\|");
                for (String e: _enquiries) {
                    if (!e.equals("")) {
                        enquiries.add(e);
                    }
                }

                // Suggestions
                ArrayList<String> suggestions = new ArrayList<>();
                String[] _suggestions = lst[13].split("\\|");
                for (String s: _suggestions) {
                    if (!s.equals("")) {
                        suggestions.add(s);
                    }
                }

                // Blacklist
                ArrayList<String> blacklist = new ArrayList<>();
                String[] _blacklist = lst[14].split("\\|");
                for (String b: _blacklist) {
                    if (!b.equals("")) {
                        blacklist.add(b);
                    }
                }

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

    /**
     * Reads suggestion data from a file.
     * @param fpath The path to the file.
     * @return A list of suggestions.
     */
    public static ArrayList<Suggestion> readSuggestions(String fpath) {
    System.out.println("Ingesting Suggestions...");
    ArrayList<Suggestion> suggestions = new ArrayList<>();
    try {
        BufferedReader reader = new BufferedReader(new FileReader(fpath));
        String line = reader.readLine();

        while (line != null) {

            if (line.trim().isEmpty()) {
                line = reader.readLine();
                continue;
            }
            
            String[] lst = line.split("_");
            String campID = lst[0];
            String variableToChange = lst[1];
            String suggestionText = lst[2];
            String suggestedBy = lst[3];
            int status = Integer.parseInt(lst[4]);
            String suggestionID = lst[5];
            Suggestion suggestion = new Suggestion(campID, variableToChange, suggestionText,
                    suggestedBy, status, suggestionID);
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
