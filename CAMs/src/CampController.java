import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampController {

	private List<Camp> camps; //might have to be in Camp.java or FilePaths.java

	
	public void removeAttendee(Student student, Camp camp) {
		if (camp.attendees.contains(student)){
			camp.attendees.remove(student);
			System.out.println(student.getUserID()+" was successfully removed from "+camp.getCampName());
			camp.setTotalSlots(camp.getTotalSlots()-1);
		}
		else{
			System.out.println(student.getUserID()+" is not registered in "+camp.getCampName());
		}
	}

	public void addCampCM(Student student, Camp camp) {
		//need to check if student is already part of CAMP_COMM_MEM_LIST
	    CampCM campCM = new CampCM(student.getUserID(),student.getFaculty(),student.myEnquiries,student.myCamps,0,new ArrayList<>());
		camp.committeeMembers.add(campCM);
		//update CAMP_COMM_MEM_LIST
	}

	public void toggleVisibility(Camp camp) {

		if(camp.getVisibility()){
			camp.setVisibility(false);
			System.out.println(camp.getCampName()+" visibility set to off");
		}
		else{
			camp.setVisibility(true);
			System.out.println(camp.getCampName()+" visibility set to on");
		}
	}

	public void deleteCamp(Camp camp) {
		camps.remove(camp);
		//might need to remove camp from other instances
	}

	public void editCamp(Camp camp) {
		System.out.println("Enter choice of the following information would you like to edit (1-10)");
		System.out.println("1. Camp name: "+camp.getCampName());
		System.out.println("2. Dates: "+camp.getDates());
		System.out.println("3. Registration Deadline: "+camp.getRegistrationDeadline());
		System.out.println("4. User Group: "+camp.getUserGroup());
		System.out.println("5. Location: "+camp.getLocation());
		System.out.println("6. Description: "+camp.getDescription());
		System.out.println("7. Total Slots: "+camp.getTotalSlots());
		System.out.println("8. Staff IC: "+camp.getStaffIC());
		System.out.println("9. Camp Committee Slots: "+camp.getCampCommSlots());
		System.out.println("10. Cancel");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				System.out.println("Enter new camp name:");
				String campName = sc.nextLine();
				camp.setCampName(campName);
				break;
			case 2:
				System.out.println("Enter new dates:");
				String dates = sc.nextLine();
				camp.setDates(dates);
				break;
			case 3:
				System.out.println("Enter new registration deadline:");
				String registrationDeadline = sc.nextLine();
				camp.setRegistrationDeadline(registrationDeadline);
				break;
			case 4:
				System.out.println("Enter new user group:");
				String userGroup = sc.nextLine();
				camp.setUserGroup(userGroup);
				break;
			case 5:
				System.out.println("Enter new location:");
				String location = sc.nextLine();
				camp.setLocation(location);
				break;
			case 6:
				System.out.println("Enter new description:");
				String description = sc.nextLine();
				camp.setDescription(description);
				break;
			case 7:
				System.out.println("Enter new total slots:");
				int totalSlots = sc.nextInt();
				camp.setTotalSlots(totalSlots);
				break;
			case 8:
				System.out.println("Enter new staff IC:");
				String staffIC = sc.nextLine();
				camp.setStaffIC(staffIC);
				break;
			case 9:
				System.out.println("Enter new camp committee slots:");
				int campCommSlots = sc.nextInt();
				camp.setCampCommSlots(campCommSlots);
				break;
			case 10:
				System.out.println("Cancel. No changes were made.");
				break;
			default:
				System.out.println("Invalid choice. No changes were made.");
				break;
		}
	}
	

	// public String getCampName() {
	// 	return camp.getCampName();
	// }

	// public List<Student> getAttendees() {
	// 	// TODO - implement CampController.getAttendees
	// 	throw new UnsupportedOperationException();
	// }

	// public List<CampCM> getCampCommMem() {
	// 	// TODO - implement CampController.getCampCommMem
	// 	throw new UnsupportedOperationException();
	// }

	public void addCamp() {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter name of camp:");
	    String campName = sc.nextLine();
	    System.out.println("Enter dates of camp:");
	    String dates = sc.nextLine();
	    System.out.println("Enter Registration Deadline of camp:");
	    String registrationDeadline = sc.nextLine();
	    System.out.println("Enter user group of camp:");
	    String userGroup = sc.nextLine();
	    System.out.println("Enter location of camp:");
	    String location = sc.nextLine();
	    System.out.println("Enter description of camp:");
	    String description = sc.nextLine();
	    System.out.println("Enter total slots of camp:");
	    int totalSlots = sc.nextInt();
	    System.out.println("Enter staff IC of camp:");
	    String staffIC = sc.nextLine();
	    System.out.println("Enter camp committee slots:");
	    int campCommSlots = sc.nextInt();
	    System.out.println("Enter visibility of camp (true/false):");
	    boolean visibility = sc.nextBoolean();
	    Camp newCamp = new Camp(campName, dates, registrationDeadline, userGroup, location, description, totalSlots, staffIC, campCommSlots, visibility);
	    camps.add(newCamp);
	}

}