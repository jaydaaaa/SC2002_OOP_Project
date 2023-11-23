package entity;
import java.util.ArrayList;
import java.util.List;


public class Camp {
    private String campName;
    private String dates;
    private String registrationDeadline;
    private String userGroup;
    private String location;s
    private String description;
    private int totalSlots;
    private String staffIC;
    private int campCommSlots;
    private boolean visibility;
    private List<Student> attendees;
    private List<CampCM> committeeMembers;
    private List<Enquiry> enquiries;
    private List<Suggestion> suggestions;
    private List<Student> blackList; // for students that have withdrawn from this camp instance

    public Camp(String campName, String dates, String registrationDeadline, String userGroup, String location, String description, int totalSlots, String staffIC, int campCommSlots, boolean visibility) {
        this.campName = campName;
        this.dates = dates;
        this.registrationDeadline = registrationDeadline;
        this.userGroup = userGroup;
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
    public String getCampName() {
        return campName;
    }
    public String getDates() {
        return dates;
    }

    public String getRegistrationDeadline() {
        return registrationDeadline;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription(){
        return description;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public String getStaffIC() {
        return staffIC;
    }

    public int getCampCommSlots() {
        return campCommSlots;
    }

    public boolean getVisibility() {
        return visibility;
    }

     public String getFaculty() {
    return this.faculty;
    }

    public List<Student> getAttendees() {
        return attendees;
    }

    public List<CampCM> getCommitteeMembers() {
        return committeeMembers;
    }

    public ArrayList<Enquiry> getEnquiries() {
        return this.enquiries;
    }
    
    public void setEnquiries(ArrayList<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public List<Student> getBlacklist() {
        return blackList;
    }

    // Setter functions
    public void setCampName(String campName) {
        this.campName = campName;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public void setRegistrationDeadline(String registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalSots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public void setStaffIC(String staffIC) {
        this.staffIC = staffIC;
    }

    public void setCampCommSlots(int campCommSlots) {
        this.campCommSlots = campCommSlots;
    }

    public void setvisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setAttendees(List<Student> attendees) {
        this.attendees = new ArrayList<>(attendees);
    }

    public void setCommitteeMembers(List<CampCM> committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    public void setEnquiries(List<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }
    
    // Changed to add suggestions
    public void addSuggestions(Suggestion suggestion) {
        this.suggestions.add(suggestion);
    }

     public void setFaculty(String faculty) {
         this.faculty = faculty;
     }
     public void setBlacklist(List<Student> blackList) {
        this.blackList = blackList;
    }
}
