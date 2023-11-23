package utils.IO;

import entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import org.omg.CORBA.Request;

import controller.BaseController;

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
                String[] lst = line.split("_");
                String name = lst[0];
                String email = lst[1];
                String userID = email.split("@")[1];
                String faculty = lst[2];
                String password = lst[3];
                String userType = lst[4];
                if (Objects.equals(userType, "Student")) {
                    Student student = new Student(name, userID, email, faculty ,password, "Student");
                    users.add(student);
                }
                else if (Objects.equals(userType, "Staff")){
                    Staff staff = new Staff(name, userID, email, faculty ,password, "Staff");
                    users.add(staff);
                }
                else if (Objects.equals(userType, "CampCM")){
                    CampCM campCM = new CampCM(name, userID, email, faculty ,password, "campCM");
                    users.add(campCM);
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
                String enquiryText = lst[0];
                String enquiryBy = lst[1];
                String replyText = lst[2];
                String replyBy = lst[3];
                Boolean status = Boolean.parseBoolean(lst[4]);
                Enquiry enquiry = new Enquiry(enquiryText, enquiryBy, replyText, replyBy, status);
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
        System.out.println("Ingesting projects...");
        ArrayList<Camp> camps = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {
                String[] lst = line.split("_");
                String campName = lst [0];
                String dates = lst[1];
                String registrationDeadline = lst[2];
                String userGroup = lst[3];
                String location = lst[4];
                String description = lst[5];
                Integer totalSlots = Integer.valueOf(lst[6]);
                String staffIC = lst[7];
                Integer campCommSlots = Integer.valueOf(lst[8]);
                Boolean visibility = Boolean.parseBoolean(lst[9]);
                Camp camp = new Camp(campName, dates, registrationDeadline, userGroup, location, description, totalSlots, staffIC, campCommSlots, visibility);
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
    System.out.println("Ingesting suggestions...");
    ArrayList<Suggestion> suggestions = new ArrayList<>();
    try {
        BufferedReader reader = new BufferedReader(new FileReader(fpath));
        String line = reader.readLine();

        while (line != null) {
            String[] lst = line.split("_");
            String suggestionText = lst [0];
            String suggestedBy = lst[1];
            Boolean status = Boolean.parseBoolean(lst[2]);
            Suggestion suggestion = new Suggestion(suggestionText, suggestedBy, status);
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
