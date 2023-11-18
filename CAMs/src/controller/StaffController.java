package controller;

import entity.*;

import java.util.ArrayList;
import java.util.Objects;

public class StaffController extends UserController{
    ArrayList<Camp> masterCamps = this.centralManager.getMasterCamps();

    public StaffController(CentralManager centralManager) {
        super(centralManager);
    }

    public void setPassword(String newPassword) {
        this.getCurrentStaff().setPassword(newPassword);
    }

    public Staff getCurrentStaff() {
        return (Staff) this.currentUser;
    }

    
    // protected List<Camp> createdCamps; //(can check using staffIC)

    // /**
    //  *
    //  * @param
    //  */
    // public void approveSuggestions(List<Suggestion> suggestions) {
    //     // TODO - implement StaffController.approveSuggestions
    //     Scanner sc = new Scanner(System.in);
    //     // Print out the suggestions 1 by 1
    //     for (Suggestion suggestion : suggestions) {
    //         System.out.println("Suggestion : " + suggestion.getSuggestionText());
    //         System.out.println("Approve? (Y/N) : ");
    //         String choice = sc.next();
    //         // If approved, set status to true
    //         if (choice.equals("Y")) {
    //             suggestion.setStatus(true);
    //         }
    //     }
    // }

    // public void replyEnquiry(List<Enquiry> enquiries) {
    //     // TODO - implement StaffController.replyEnquiry
    //     Scanner sc = new Scanner(System.in);
    //     // Iterate every enquiries in the enquiry list
    //     for (Enquiry enquiry : enquiries) {
    //         System.out.println("Enquiries: " + enquiry.getEnquiryText());
    //         System.out.println("Reply: ");
    //         String reply = sc.nextLine();
    //         enquiry.setReplyText(reply);
    //     }
    // }

    // /**
    //  *
    //  * @param createdCamps
    //  */
    // public void generateList(List<Camp> createdCamps) {
    //     // TODO - implement StaffController.generateList
    //     // Generate list of camps that the staff created
    //     int i = 1;
    //     for (Camp camp : createdCamps) {
    //         System.out.println("Camp " + i + ": " + camp.getCampName());
    //         i++;
    //     }
    // }

    // /**
    //  *
    //  * @param
    //  */
    // // Report for list of students and committee members, including camp details
    // public void generateReport(List<Camp> createdCamps) {
    //     // TODO - implement StaffController.generateReport
    //     String csvFile = "report.csv";
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("(1) Print only List of students");
    //     System.out.println("(2) Print only List of committee members");
    //     System.out.println("(3) Print both students and committee members");
    //     int choice = sc.nextInt();

    //     try (FileWriter writer = new FileWriter(csvFile)) {
    //         for (Camp camp: createdCamps) {
    //             writer.append("Camp: ").append(camp.getCampName()).append('\n');
    //             writer.append("Date: ").append(camp.getDates()).append('\n');
    //             writer.append("Registration Deadline: ").append(camp.getRegistrationDeadline()).append('\n');
    //             writer.append("User Group: ").append(camp.getUserGroup()).append('\n');
    //             writer.append("Location: ").append(camp.getLocation()).append('\n');
    //             writer.append("Description: ").append(camp.getDescription()).append('\n');
    //             writer.append("Total Slots: ").append("" + camp.getTotalSlots()).append('\n');
    //             writer.append("StaffIC: ").append(camp.getStaffIC()).append('\n');
    //             writer.append("CampCommSlots: ").append("" + camp.getCampCommSlots()).append('\n');
    //             writer.append("Visibility: ").append(String.valueOf(camp.getVisibility())).append('\n');

    //             // Printing the list of students
    //             if (choice == 1 || choice == 3) {
    //                 writer.append("List of students: ").append('\n');
    //                 for (Student student : camp.getAttendees()) {
    //                     writer.append(student.getUserID()).append('\n');
    //                 }
    //                 writer.append("\n");
    //             }
    //             if (choice == 2 || choice == 3) {
    //                 writer.append("List of committee Members: ").append('\n');
    //                 for (CampCommMem committeeMembers : camp.getCommitteeMembers()) {
    //                     writer.append(committeeMembers.getUserID()).append("\n");
    //                 }
    //                 writer.append("\n");
    //             }
    //         }
    //         System.out.println("The csv has been created");
    //     } catch(IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void createCamp() {
    //     // TODO - implement StaffController.createCamp
    //     Scanner sc = new Scanner(System.in);
    //     System.out.println("Enter name of camp:");
    //     String campName = sc.nextLine();
    //     System.out.println("Enter dates of camp:");
    //     String dates = sc.nextLine();
    //     System.out.println("Enter Registration Deadline of camp:");
    //     String registrationDeadline = sc.nextLine();
    //     System.out.println("Enter user group of camp:");
    //     String userGroup = sc.nextLine();
    //     System.out.println("Enter location of camp:");
    //     String location = sc.nextLine();
    //     System.out.println("Enter description of camp:");
    //     String description = sc.nextLine();
    //     System.out.println("Enter total slots of camp:");
    //     int totalSlots = sc.nextInt();
    //     System.out.println("Enter staff IC of camp:");
    //     String staffIC = sc.nextLine();
    //     System.out.println("Enter camp committee slots:");
    //     int campCommSlots = sc.nextInt();
    //     System.out.println("Enter visibility of camp (true/false):");
    //     boolean visibility = sc.nextBoolean();
    //     List<Student> attendees = new ArrayList<>();
    //     List<CampCommMem> committeeMembers = new ArrayList<>();
    //     List<Enquiry> enquiries = new ArrayList<>();
    //     List<Suggestion> suggestions = new ArrayList<>();

    //     Camp newCamp = new Camp(campName, dates, registrationDeadline, userGroup, location, description, totalSlots,
    //             staffIC, campCommSlots, visibility, attendees, committeeMembers, enquiries, suggestions);
    //     createdCamps.add(newCamp);
    // }
    // // Not included in UML Class diagram*************************************************
    // // Generate report for a particular Camp
    // public void generatePerformanceReport(Camp camp) {
    //     String csvFile = "PerformanceReport.csv";
    //     try (FileWriter writer = new FileWriter(csvFile)) {
    //         writer.append("Camp: ").append(camp.getCampName()).append('\n');
    //         writer.append("Name, Points\n");
    //         for (CampCommMem committeeMember : camp.getCommitteeMembers()) {
    //             writer.append(committeeMember.getUserID())
    //                     .append(',')
    //                     .append(String.valueOf(committeeMember.getMyPoints()))
    //                     .append('\n');

    //         }
    //     } catch(IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    // public void deleteCamp(Camp camp) {
    //     // TODO - implement StaffController.deleteCamp
    //     createdCamps.remove(camp);
    // }

}
