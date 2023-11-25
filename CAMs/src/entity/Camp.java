package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Camp {
    private String campName;
    private ArrayList<Integer> dates;
    private Integer registrationDeadline;
    private String faculty;
    private String location;
    private String description;
    private int totalSlots;
    private String staffIC;
    private int campCommSlots;
    private boolean visibility;
    private ArrayList<String> attendees;
    private ArrayList<String> committeeMembers;
    private ArrayList<String> enquiries;
    private ArrayList<String> suggestions;
    private ArrayList<String> blackList; // for students that have withdrawn from this camp instance
    private String campID;

    public Camp(String campName, ArrayList<Integer> dates, Integer registrationDeadline, String faculty, String location, String description, int totalSlots, String staffIC, int campCommSlots, boolean visibility, String campID) {
        if (campID.equals("")) {
            this.campID =  UUID.randomUUID().toString();
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

    public String getCampID() {
        return this.campID;
    }

    public String getCampName() {
        return campName;
    }
    public ArrayList<Integer> getDates() {
        return dates;
    }

    public Integer getRegistrationDeadline() {
        return registrationDeadline;
    }

    public String getUserGroup() {
        return faculty;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription(){
        return description;
    }

    public int getTotalSlots() {
        return this.totalSlots;
    }

    public String getStaffIC() {
        return this.staffIC;
    }

    public int getCampCommSlots() {
        return this.campCommSlots;
    }

    public boolean getVisibility() {
        return this.visibility;
    }

     public String getFaculty() {
    return this.faculty;
    }

    public ArrayList<String> getAttendees() {
        return this.attendees;
    }

    public ArrayList<String> getCommitteeMembers() {
        return this.committeeMembers;
    }

    public ArrayList<String> getEnquiries() {
        return this.enquiries;
    }

    public List<String> getSuggestions() {
        return this.suggestions;
    }

    public List<String> getBlacklist() {
        return this.blackList;
    }
    public int getNumberAttendees() {
        return this.attendees.size();
    }

    // Setter functions
    public void setCampName(String campName) {
        this.campName = campName;
    }

    public void setDates(ArrayList<Integer> dates) {
        this.dates = dates;
    }

    public void setRegistrationDeadline(Integer registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public void setUserGroup(String userGroup) {
        this.faculty = userGroup;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public void setStaffIC(String staffIC) {
        this.staffIC = staffIC;
    }

    public void setCampCommSlots(int campCommSlots) {
        this.campCommSlots = campCommSlots;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setAttendees(ArrayList<String> attendees) {
        this.attendees = attendees;
    }

    public void setBlackList(ArrayList<String> blackList) {
        this.blackList = blackList;
    }

    public void setCommitteeMembers(ArrayList<String> committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    public void setEnquiries(ArrayList<String> enquiries) {
        this.enquiries = enquiries;
    }

    public void setSuggestions(ArrayList<String> suggestions) {
        this.suggestions = suggestions;
    }

    // Adder methods
    public void addAttendee(String studentID) {
        this.attendees.add(studentID);
    }

    public void addCommitteeMember(String studentID) {
        this.committeeMembers.add(studentID);
    }

    public void addEnquiry(String enquiryID) {
        this.enquiries.add(enquiryID);
    }

    public void addSuggestion(String suggestionID) {
        this.suggestions.add(suggestionID);
    }

    public void setFaculty(String facultyID) {
         this.faculty = facultyID;
    }

    public void addToBlacklist(String studentID) {
        this.blackList.add(studentID);
    }
}
