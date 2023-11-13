// Your existing classes and methods...

public class Main{
    public static void main(String[] args) {
        // Create some sample camps with attendees and committee members
        List<Camp> createdCamps = new ArrayList<>();

        // Create Camp 1
        List<Student> attendees1 = new ArrayList<>();
        attendees1.add(new Student("Wei Ting", "Alice"));
        attendees1.add(new Student("Yi Xuan", "Bob"));
        List<CampCommMem> committeeMembers1 = new ArrayList<>();
        committeeMembers1.add(new CampCommMem("moon", "Charlie"));
        committeeMembers1.add(new CampCommMem("sun", "Diana"));
        List<Suggestion> suggestion1 = new ArrayList<>();
        List<Enquiry> enquiry1 = new ArrayList<>();

        Camp camp1 = new Camp("Summer Camp", "2023-07-01", "2023-07-15", "Group A", "Location A", "Description A", 100, "Staff1", 10, true, attendees1, committeeMembers1, enquiry1, suggestion1);
        createdCamps.add(camp1);

        // Create Camp 2
        List<Student> attendees2 = new ArrayList<>();
        attendees2.add(new Student("Poop", "Eve"));
        attendees2.add(new Student("Pee", "Frank"));
        List<CampCommMem> committeeMembers2 = new ArrayList<>();
        committeeMembers2.add(new CampCommMem("Bean", "Grace"));
        committeeMembers2.add(new CampCommMem("Boon", "Harry"));
        List<Suggestion> suggestion2 = new ArrayList<>();
        List<Enquiry> enquiry2 = new ArrayList<>();

        Camp camp2 = new Camp("Winter Camp", "2023-12-01", "2023-12-1", "Group B", "Location B", "Description B", 150, "Staff2", 15, true, attendees2, committeeMembers2,enquiry2,suggestion2);
        createdCamps.add(camp2);

        // Call the method from the StaffController class to generate the report
        StaffController staffController = new StaffController();
        staffController.generateReport(createdCamps);
    }
}
