package controller;
import java.util.ArrayList;

import entity.*;

import java.util.Objects;

/**
 * The CampController class is a controller for managing Camp entities.
 * It extends the BaseController class and provides additional functionality specific to camp management.
 * @author Group 2
 * @since 2023-11-20
 */
public class CampController extends BaseController{
	
    /**
     * Constructs a CampController with the specified CentralManager.
     *
     * @param centralManager The CentralManager instance to associate with the CampController.
     */
    public CampController(CentralManager centralManager) {
        super(centralManager);
    }
    
    /**
     * Retrieves available camps based on the faculty of a student.
     *
     * @param studentID The student ID of the student for whom available camps are retrieved.
     * @return An ArrayList of available camps for the specified student.
     */
    public ArrayList<Camp> getAvailCamps(String studentID) {
        Student student = this.getStudentController().getStudentByID(studentID);
        ArrayList<Camp> facultyCamps = new ArrayList<>();
        for (Camp camp : this.getAllCamps()) {
            if ((camp.getFaculty().equals(student.getFaculty()) || camp.getFaculty().equals("NTU")) &&
                    camp.getVisibility() &&  // is visible
                    !camp.getBlacklist().contains(studentID) && // is not in blacklist
                    !camp.getAttendees().contains(studentID)) {  // is not an attendee
                facultyCamps.add(camp);
            }
        }
        return facultyCamps;
    }

    /**
     * Retrieves all camps managed by the system.
     *
     * @return An ArrayList of all camps.
     */
    public ArrayList<Camp> getAllCamps() {
        return this.getCentralManager().getMasterCamps();
    }

    /**
     * Retrieves camps associated with a staff member based on their staff ID.
     *
     * @param staffID The staff ID of the staff member.
     * @return An ArrayList of camps associated with the specified staff member.
     */
    public ArrayList<Camp> getCampsByStaffID(String staffID) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : this.getAllCamps()) {
            if (Objects.equals(camp.getStaffIC(), staffID)) {
                camps.add(camp);
            }
        }
        return camps;
    }

    /**
     * Retrieves a camp by its camp ID.
     *
     * @param campID The ID of the camp to retrieve.
     * @return The Camp object with the specified camp ID, or null if not found.
     */
    public Camp getCampByID(String campID) {
        for (Camp camp: this.getAllCamps()) {
            if (camp.getCampID().equals(campID)) {
                return camp;
            }
        }
        return null;
    }

    /**
     * Retrieves camps that a student is participating in based on their student ID.
     *
     * @param studentID The student ID of the student for whom camps they are participating in is retrieved.
     * @return An ArrayList of camps the specified student is participating in.
     */
    public ArrayList<Camp> getAttendedCamps(String studentID) {
        ArrayList<Camp> attendedCamps = new ArrayList<>();
        for (Camp camp: this.getAllCamps()) {
            ArrayList<String> attendees = camp.getAttendees();
            if (attendees.contains(studentID)) {
                attendedCamps.add(camp);
            }
        }
        return attendedCamps;
    }

    /**
     * Finds a camp by its camp name.
     *
     * @param campName The name of the camp to find.
     * @return The Camp object with the specified camp name, or null if not found.
     */
    public Camp findCamp(String campName) {
        for (Camp camp : this.getAllCamps()) {
            if(Objects.equals(camp.getCampName(),campName)) {
                return camp;
            }
        }
        return null;
    }

    /**
     * Removes an attendee from a camp.
     *
     * @param student  The student to be removed as an attendee.
     * @param campID   The ID of the camp from which the student will be removed.
     * @return 1 if removal is successful, 0 if the student is a committee member, -1 if the student is not an attendee.
     */
    public int removeAttendee(Student student, String campID) {
        Camp camp = this.getCampByID(campID);
        if (camp.getAttendees().contains(student.getUserID())){
            if (!camp.getCommitteeMembers().contains(student.getUserID())) { // allows for withdrawal if not CampCom
                camp.getAttendees().remove(student.getUserID());
                // Add student to camp's blacklist
                camp.addToBlacklist(student.getUserID());
                return 1;
            } else {
                return 0;
            }
        } else{
            return -1;
        }
    }

    /**
     * Toggles the visibility of a camp.
     *
     * @param camp The camp for which visibility will be toggled.
     */
    public void toggleVisibility(Camp camp) {
        if(camp.getVisibility()){
            camp.setVisibility(false);
            System.out.println(camp.getCampName()+" visibility set to off");
        } else{
            camp.setVisibility(true);
            System.out.println(camp.getCampName()+" visibility set to on");
        }
    }

    /**
     * Deletes a camp using the specified camp ID.
     *
     * @param campID The ID of the camp to be deleted.
     * @return 1 if deletion is successful, 0 if camp cannot be deleted as there are attendees in the camp.
     */
    public int deleteCamp(String campID) {
        Camp camp = this.getCampController().getCampByID(campID);
        if (camp.getNumberAttendees() > 0) {
            return 0;
        } else {
            this.getCentralManager().getMasterCamps().remove(camp);
            return 1;
        }
    }

    /**
     * Edits the attributes of a camp.
     *
     * @param camp              The camp to be edited.
     * @param newLocation       The new location of the camp.
     * @param newDescription    The new description of the camp.
     * @param registrationDeadline The new registration deadline of the camp.
     */
    public void editCamp(Camp camp, String newLocation, String newDescription, int registrationDeadline) {
        camp.setDescription(newDescription);
        camp.setLocation(newLocation);
        camp.setRegistrationDeadline(registrationDeadline);
    }

    /**
     * Creates a new camp with the specified attributes.
     *
     * @param campName        The name of the new camp.
     * @param dates           The dates of the new camp.
     * @param registrationDeadline The registration deadline of the new camp.
     * @param userGroup       The user group of the new camp.
     * @param location        The location of the new camp.
     * @param description     The description of the new camp.
     * @param totalSlots      The total slots available in the new camp.
     * @param staffIC         The staff in charge of the new camp.
     * @param campCommSlots   The number of camp committee slots in the new camp.
     * @param visibility      The visibility status of the new camp.
     */
    public void createCamp(String campName, ArrayList<Integer> dates, int registrationDeadline, String userGroup,
                           String location, String description, Integer totalSlots, String staffIC, Integer campCommSlots,
                           boolean visibility) {
        Camp newCamp = new Camp(campName, dates, registrationDeadline, userGroup, location, description, totalSlots,
                staffIC, campCommSlots, visibility, "");
        this.getCentralManager().getMasterCamps().add(newCamp);

    }

}
