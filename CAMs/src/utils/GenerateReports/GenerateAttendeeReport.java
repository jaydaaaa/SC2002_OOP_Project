package utils.GenerateReports;

import entity.Camp;
import entity.CampCM;
import entity.Student;
import entity.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GenerateAttendeeReport {
    ArrayList<String> finalAttendees;
    ArrayList<Student> attendees;
    ArrayList<Camp> camps;
    boolean isPerfReport;
    public GenerateAttendeeReport(ArrayList<Student> attendees, ArrayList<Camp> camps, String nameFilter,
                                  String roleFilter, String locationFilter, String campNameFilter, boolean isPerfReport) {
        this.attendees = attendees;
        // Filtering by attendee name
        ArrayList<String> nameFilteredAttendees = new ArrayList<>();
        for (User attendee: attendees) {
            if (!nameFilter.equals("")) {
                if (attendee.getName().equals(nameFilter)) {
                    nameFilteredAttendees.add(attendee.getUserID());
                }
            } else {
                nameFilteredAttendees.add(attendee.getUserID());
            }
        }
        // Filtering by role
        ArrayList<String> roleFilteredAttendees = new ArrayList<>();
        for (User attendee: attendees) {
            if (!roleFilter.equals("")) {
                if (attendee.getType().equals(roleFilter)) {
                    roleFilteredAttendees.add(attendee.getUserID());
                }
            } else {
                roleFilteredAttendees.add(attendee.getUserID());
            }
        }
        // Filtering by camp location
        ArrayList<String> locationFilteredAttendees = new ArrayList<>();
        if (!locationFilter.equals("")) {
            ArrayList<Camp> locFilteredCamps = new ArrayList<>();
            for (Camp camp: camps) {
                if (camp.getLocation().equals(locationFilter)) {
                    locFilteredCamps.add(camp);
                }
            }
            for (User attendee: attendees) {
                for (Camp camp: locFilteredCamps) {
                    if (camp.getAttendees().contains(attendee.getUserID())) {
                        locationFilteredAttendees.add(attendee.getUserID());
                    }
                }
            }
        } else {
            for (User attendee: attendees) {
                locationFilteredAttendees.add(attendee.getUserID());
            }
        }

        // Filtering by camp Name
        ArrayList<String> campNameFilteredAttendees = new ArrayList<>();
        if (!nameFilter.equals("")) {
            ArrayList<Camp> nameFilteredCamps = new ArrayList<>();
            for (Camp camp: camps) {
                if (camp.getCampName().equals(campNameFilter)) {
                    nameFilteredCamps.add(camp);
                }
            }
            for (User attendee: attendees) {
                for (Camp camp: nameFilteredCamps) {
                    if (camp.getAttendees().contains(attendee.getUserID())) {
                        campNameFilteredAttendees.add(attendee.getUserID());
                    }
                }
            }
        } else {
            for (User attendee: attendees) {
                campNameFilteredAttendees.add(attendee.getUserID());
            }
        }

        ArrayList<ArrayList<String>> combinedList = new ArrayList<>();
        combinedList.add(nameFilteredAttendees);
        combinedList.add(roleFilteredAttendees);
        combinedList.add(locationFilteredAttendees);
        combinedList.add(campNameFilteredAttendees);
        this.finalAttendees = this.findCommonElements(combinedList);
        this.camps = camps;
        this.isPerfReport = isPerfReport;
    }

    private ArrayList<String> findCommonElements(ArrayList<ArrayList<String>> lists) {
        if (lists == null || lists.isEmpty()) {
            return new ArrayList<>();
        }

        // Create a copy of the first list to avoid modifying the original lists
        ArrayList<String> commonElements = new ArrayList<>(lists.get(0));

        // Iterate through the remaining lists and retain only the common elements
        for (int i = 1; i < lists.size(); i++) {
            commonElements.retainAll(lists.get(i));
        }

        return commonElements;
    }

    private User getUserByID(String userID) {
        for (User attendee: this.attendees) {
            if (attendee.getUserID().equals(userID)) {
                return attendee;
            }
        }
        return null;
    }

    public void generateReport(String fpath) {
        try {
            FileWriter writer = new FileWriter(fpath);
            String header = "";
            if (isPerfReport) {
                header = "CampName,CampLocation,CampStartDate,CampEndDate,RegistrationDeadline,UserName,UserRole,UserPoints\n";
            } else {
                header = "CampName,CampLocation,CampStartDate,CampEndDate,RegistrationDeadline,UserName,UserRole\n";
            }
            writer.write(header);
            for (Camp camp: this.camps) {
                String campName = camp.getCampName();
                String campLocation = camp.getLocation();
                for (String userID: this.finalAttendees) {
                    if (camp.getAttendees().contains(userID)) {
                        User user = this.getUserByID(userID);
                        assert user != null;
                        String userName = user.getName();
                        String userRole;

                        if (camp.getCommitteeMembers().contains(user.getUserID())) {
                            userRole = "CampCommittee";
                        } else {
                            userRole = "Student";
                        }

                        String toWrite = "";
                        ArrayList<Integer> dates = camp.getDates();
                        if (isPerfReport) {
                            if (userRole.equals("CampCommittee")) {
                                CampCM campCM = (CampCM) user;
                                int points = campCM.getPoints();
                                toWrite = campName + "," + campLocation + "," + dates.get(0) + "," + dates.get(1) + "," + camp.getRegistrationDeadline() + "," + userName + "," + userRole + ","
                                        + points + "\n";
                            }
                        } else {
                            toWrite = campName + "," + campLocation + "," + dates.get(0) + "," + dates.get(1) + "," + camp.getRegistrationDeadline() + "," + userName + "," + userRole + "\n";
                        }
                        if (!toWrite.equals("")) {
                            writer.write(toWrite);
                        }
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
