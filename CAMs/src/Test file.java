import java.util.ArrayList;
import java.util.List;

// Your existing classes and methods...

public class Main{
    public static void main(String[] args) {
        // Create some sample camps with attendees and committee members
        List<Camp> createdCamps = new ArrayList<>();

        // Create Camp 1
        List<Suggestion> suggestion1 = new ArrayList<>();
        List<Enquiry> enquiry1 = new ArrayList<>();
        List<Enquiry> enquiry2  = new ArrayList<>();
        List<Student> attendees1 = new ArrayList<>();
        List<Camp> myCamp1 = new ArrayList<>();
        List<Camp> myCamp2 = new ArrayList<>();
        attendees1.add(new Student("Wei Ting", "Alice", enquiry1, myCamp1 ));
        attendees1.add(new Student("Yi Xuan", "Bob", enquiry2, myCamp2 ));
        List<CampCommMem> committeeMembers1 = new ArrayList<>();
        committeeMembers1.add(new CampCommMem("moon", "Charlie", enquiry1, myCamp1));
        committeeMembers1.add(new CampCommMem("sun", "Diana", enquiry2, myCamp2));
        

        Camp camp1 = new Camp("Summer Camp", "2023-07-01", "2023-07-15", "Group A", "Location A", "Description A", 100, "Staff1", 10, true, attendees1, committeeMembers1, enquiry1, suggestion1);
        createdCamps.add(camp1);

        // Create Camp 2
        List<Student> attendees2 = new ArrayList<>();
        attendees2.add(new Student("Poop", "SCSE", enquiry1, myCamp1));
        attendees2.add(new Student("Pee", "NBS", enquiry2, myCamp1));
        List<CampCommMem> committeeMembers2 = new ArrayList<>();
        committeeMembers2.add(new CampCommMem("Bean", "Grace", enquiry1, myCamp1));
        committeeMembers2.add(new CampCommMem("Boon", "Harry", enquiry2, myCamp2));
        List<Suggestion> suggestion2 = new ArrayList<>();
        List<Enquiry> enquiry3 = new ArrayList<>();

        Camp camp2 = new Camp("Winter Camp", "2023-12-01", "2023-12-1", "Group B", "Location B", "Description B", 150, "Staff2", 15, true, attendees2, committeeMembers2,enquiry3,suggestion2);
        createdCamps.add(camp2);

        // Call the method from the StaffController class to generate the report
        StaffController staffController = new StaffController();
        staffController.generateReport(createdCamps);
    }
}
