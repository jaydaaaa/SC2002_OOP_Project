package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Camp class represents the camp info in the system.
 * Students can register for camps.
 * Staffs can create,edit and delete camps.
 * @author Group 2
 * @since 2023-11-26
 */
public class Camp {

    /**
     * The camp name.
     */
    private String campName;
    /**
     * The start date and end date of the camp.
     */
    private ArrayList<Integer> dates;
    /**
     * The registration deadline.
     */
    private Integer registrationDeadline;
    /**
     * The faculty hosting the camp.
     */
    private String faculty;
    /**
     * The location of the camp.
     */
    private String location;
    /**
     * The description of the camp.
     */
    private String description;
    /**
     * The total attendee slots of the camp.
     */
    private int totalSlots;
    /**
     * The staff in charge of the camp.
     */
    private String staffIC;
    /**
     * The Camp committee slots for the camp.
     */
    private int campCommSlots;
    /**
     * Whether camp is hidden from students or not.
     */
    private boolean visibility;
    /**
     * The list of attendees.
     */
    private ArrayList<String> attendees;
    /**
     * The list of camp committee members.
     */
    private ArrayList<String> committeeMembers;
    /**
     * The list of enquiries for the camp.
     */
    private ArrayList<String> enquiries;
    /**
     * The list of suggestions for the camp.
     */
    private ArrayList<String> suggestions;
    /**
     * The list of students who have withdrawn from the camp before.
    */
    private ArrayList<String> blackList; // for students that have withdrawn from this camp instance
    /**
     * The campID.
     */
    private String campID;

    /**
     * Constructs a new Camp object with the specified details.
     *
     * @param campName            The name of the camp.
     * @param dates               The date of the camp.
     * @param registrationDeadline The registration deadline for the camp.
     * @param faculty             The faculty to which the camp belongs.
     * @param location            The location of the camp.
     * @param description         The description of the camp.
     * @param totalSlots          The total number of slots for attendee and camp committee member available in the camp.
     * @param staffIC             The staff in charge of the camp.
     * @param campCommSlots       The number of camp committee slots in the camp.
     * @param visibility          The visibility status of the camp.
     * @param campID              The campID
     */
    public Camp(String campName, ArrayList<Integer> dates, Integer registrationDeadline, String faculty, String location, String description, int totalSlots, String staffIC, int campCommSlots, boolean visibility, String campID) {
        if (campID.equals("")) {
            this.campID = UUID.randomUUID().toString();
        } else {
            this.campID = campID;
        }
        this.campName = campName;
        this.dates = dates;
        this.registrationDeadline = registrationDeadline;
        this.faculty = faculty;
        this.location = location;
        this.description = description;
        this.totalSlots = totalSlots;
        this.staffIC = staffIC;
        this.campCommSlots = campCommSlots;
        this.visibility = visibility;
        this.attendees = new ArrayList<>();
        this.committeeMembers = new ArrayList<>();
        this.enquiries = new ArrayList<>();
        this.suggestions = new ArrayList<>();
        this.blackList = new ArrayList<>();
    }

    // Getter methods

    /**
     * Gets campID
     * @return The campID.
     */
    public String getCampID() {
        return this.campID;
    }

    /**
     * Gets the name of the camp.
     * @return The camp name.
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Gets the dates of the camp.
     * @return The dates of the camp.
     */
    public ArrayList<Integer> getDates() {
        return dates;
    }

    /**
     * Gets the registration deadline for the camp.
     * @return The registration deadline.
     */
    public Integer getRegistrationDeadline() {
        return registrationDeadline;
    }

    /**
     * Gets the faculty to which the camp belongs.
     * @return The faculty of the camp.
     */
    public String getUserGroup() {
        return faculty;
    }

    /**
     * Gets the location of the camp.
     * @return The location of the camp.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the description of the camp.
     * @return The description of the camp.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the total number of slots available in the camp.
     * @return The total number of slots.
     */
    public int getTotalSlots() {
        return this.totalSlots;
    }

    /**
     * Gets the staff who created the camp
     * @return The staff in charge.
     */
    public String getStaffIC() {
        return this.staffIC;
    }

    /**
     * Gets the number of camp committee slots in the camp.
     * @return The number of camp committee slots.
     */
    public int getCampCommSlots() {
        return this.campCommSlots;
    }

    /**
     * Gets the visibility status of the camp.
     * @return The visibility status.
     */
    public boolean getVisibility() {
        return this.visibility;
    }

    /**
     * Gets the faculty to which the camp belongs.
     * @return The faculty of the camp.
     */
    public String getFaculty() {
        return this.faculty;
    }

    /**
     * Gets the list of attendees in the camp.
     * @return The list of attendees.
     */
    public ArrayList<String> getAttendees() {
        return this.attendees;
    }

    /**
     * Gets the list of camp committee members in the camp.
     * @return The list of camp committee members.
     */
    public ArrayList<String> getCommitteeMembers() {
        return this.committeeMembers;
    }

    /**
     * Gets the number of current committee members in the camp.
     * @return The number of current committee members.
     */
    public Integer getNumCurrentCampComm() {
        return this.committeeMembers.size();
    }

    /**
     * Gets the list of enquiries related to the camp.
     * @return The list of enquiries.
     */
    public ArrayList<String> getEnquiries() {
        return this.enquiries;
    }

    /**
     * Gets the list of suggestions related to the camp.
     * @return The list of suggestions.
     */
    public List<String> getSuggestions() {
        return this.suggestions;
    }

    /**
     * Gets the list of students in the camp's blacklist.
     * @return The list of students in the blacklist.
     */
    public List<String> getBlacklist() {
        return this.blackList;
    }

    /**
     * Gets the number of attendees in the camp.
     * @return The number of attendees.
     */
    public int getNumberAttendees() {
        return this.attendees.size();
    }

    // Setter functions

    /**
     * Sets the name of the camp.
     * @param campName The new name of the camp.
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Sets the date of the camp.
     * @param dates The new date of the camp.
     */
    public void setDates(ArrayList<Integer> dates) {
        this.dates = dates;
    }

    /**
     * Sets the registration deadline for the camp.
     * @param registrationDeadline The new registration deadline for the camp.
     */
    public void setRegistrationDeadline(Integer registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    /**
     * Sets the faculty to which the camp belongs.
     * @param userGroup The new faculty of the camp.
     */
    public void setUserGroup(String userGroup) {
        this.faculty = userGroup;
    }

    /**
     * Sets the location of the camp.
     * @param location The new location of the camp.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the description of the camp.
     * @param description The new description of the camp.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the total number of slots available in the camp.
     * @param totalSlots The new total number of slots.
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    /**
     * Sets the staff in charge of the camp.
     * @param staffIC The new staff in charge.
     */
    public void setStaffIC(String staffIC) {
        this.staffIC = staffIC;
    }

    /**
     * Sets the number of camp committee slots in the camp.
     * @param campCommSlots The new number of camp committee slots.
     */
    public void setCampCommSlots(int campCommSlots) {
        this.campCommSlots = campCommSlots;
    }

    /**
     * Sets the visibility status of the camp.
     * @param visibility The new visibility status.
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * Sets the list of attendees in the camp.
     * @param attendees The new list of attendees.
     */
    public void setAttendees(ArrayList<String> attendees) {
        this.attendees = attendees;
    }

    /**
     * Sets the list of students in the camp's blacklist.
     * @param blackList The new list of students in the blacklist.
     */
    public void setBlackList(ArrayList<String> blackList) {
        this.blackList = blackList;
    }

    /**
     * Sets the list of committee members in the camp.
     * @param committeeMembers The new list of committee members.
     */
    public void setCommitteeMembers(ArrayList<String> committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    /**
     * Sets the list of enquiries related to the camp.
     * @param enquiries The new list of enquiries.
     */
    public void setEnquiries(ArrayList<String> enquiries) {
        this.enquiries = enquiries;
    }

    /**
     * Sets the list of suggestions related to the camp.
     * @param suggestions The new list of suggestions.
     */
    public void setSuggestions(ArrayList<String> suggestions) {
        this.suggestions = suggestions;
    }

    // Adder methods

    /**
     * Adds a student to the list of attendees in the camp.
     * @param studentID The ID of the student to be added.
     */
    public void addAttendee(String studentID) {
        this.attendees.add(studentID);
    }

    /**
     * Adds a student to the list of committee members in the camp.
     * @param studentID The ID of the student to be added.
     */
    public void addCommitteeMember(String studentID) {
        this.committeeMembers.add(studentID);
    }

    /**
     * Adds an enquiry to the list of enquiries related to the camp.
     * @param enquiryID The ID of the enquiry to be added.
     */
    public void addEnquiry(String enquiryID) {
        this.enquiries.add(enquiryID);
    }

    /**
     * Adds a suggestion to the list of suggestions related to the camp.
     * @param suggestionID The ID of the suggestion to be added.
     */
    public void addSuggestion(String suggestionID) {
        this.suggestions.add(suggestionID);
    }

    /**
     * Sets the faculty of the camp.
     * @param facultyID The new faculty ID.
     */
    public void setFaculty(String facultyID) {
        this.faculty = facultyID;
    }

    /**
     * Adds a student to the blacklist of students that have withdrawn from this camp instance.
     * @param studentID The ID of the student to be added to the blacklist.
     */
    public void addToBlacklist(String studentID) {
        this.blackList.add(studentID);
    }
}
