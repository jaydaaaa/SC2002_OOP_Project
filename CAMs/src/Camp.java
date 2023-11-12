import java.util.ArrayList;
import java.util.List;
public class Camp {

    private String campName;
    private String dates;
    private String registrationDeadline;
    private String userGroup;
    private String location;
    private String description;
    private int totalSlots;
    private String staffIC;
    private int campCommSlots;
    private boolean visibility;
    private List<Student> attendees;
    private List<CampCM> committeeMembers;
    private List<Enquiry> enquiries;
    private List<Suggestion> suggestions;

    public Camp(String campName, String dates, String registrationDeadline, String userGroup,
                String location, String description, int totalSlots, String staffIC,
                int campCommSlots, boolean visibility, List<Student> attendees,
                List<CampCM> committeeMembers, List<Enquiry> enquiries,
                List<Suggestion> suggestions) {
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
        this.attendees = new ArrayList<>(attendees);
        this.committeeMembers = new ArrayList<>(committeeMembers);
        this.enquiries = new ArrayList<>(enquiries);
        this.suggestions = new ArrayList<>(suggestions);
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

    public boolean isVisibility() {
        return visibility;
    }

    public List<Student> getAttendees() {
        return attendees;
    }

    public List<CampCM> getCommitteeMembers() {
        return committeeMembers;
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
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

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
}
