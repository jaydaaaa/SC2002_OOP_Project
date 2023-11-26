package controller;
import entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class StudentController extends UserController{
	public StudentController(CentralManager centralManager){
        super(centralManager);
    }

	public Student getCurrentStudent() {
        return (Student) this.currentUser;
    }

	public void setPassword(String newPassword){
        this.getCurrentStudent().setPassword(newPassword);
    }

	public String getFaculty() {
		return this.getCurrentStudent().getFaculty();
	}

	public Student getStudentByID(String studentID) {
		for (User user: this.getCentralManager().getMasterUsers()) {
			if (user.getUserID().equals(studentID)) {
				return (Student) user;
			}
		}
		return null;
	}

	public boolean doesClash(Camp camp1, Camp camp2) {
		Integer start1, start2, end1, end2;
		ArrayList<Integer> dates1 = camp1.getDates();
		ArrayList<Integer> dates2 = camp2.getDates();
		start1 = dates1.get(0);
		end1 = dates1.get(1);
		start2 = dates2.get(0);
		end2 = dates2.get(1);
		if (start1 < start2) {
			return end1 > start2;
		} else {  // start 1 > start 2
			return end2 > start1;
		}
	}

	public int registerForCamp(String campID, boolean choiceCampComm) {
		// Find the camp
		Camp newCamp = this.getCampController().getCampByID(campID);
		// Check if deadlines overlap
		for (Camp camp: this.getAttendedCamps()) {
			if (this.doesClash(camp, newCamp)) {
				return 0; // clash
			}
		}
		if (newCamp.getNumberAttendees() >= newCamp.getTotalSlots()) {
			return -1; // full
		}
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		// Format the current date using the formatter
		String formattedDate = currentDate.format(formatter);

		// Cast date to integer
		int date = Integer.parseInt(formattedDate);
		System.out.println("currentDate: " + date);

		if (newCamp.getRegistrationDeadline() < date) {
			return -2; // deadline over
		}
		String userID;
		Student currentStudent = this.getCurrentStudent();
		if (choiceCampComm) {
			if (newCamp.getCampCommSlots() <= newCamp.getNumCurrentCampComm()) {
				return -3; // no empty campcomm slots
			} else {
				CampCM campCM = new CampCM(currentStudent.getName(), currentStudent.getUserID(), currentStudent.getEmail(),
						currentStudent.getPassword(), currentStudent.getFaculty(), "CampCM", 0, campID);
				userID = campCM.getUserID();

				// Remove old Student object, add in new CampCM object
				this.getCentralManager().getMasterUsers().remove(currentStudent);
				this.getCentralManager().getMasterUsers().add(campCM);

				// Add CampComm to attendee list and CampCM list of camp
				newCamp.addAttendee(userID);
				newCamp.addCommitteeMember(userID);
			}
		} else {
			userID = currentStudent.getUserID();
			newCamp.addAttendee(userID);
		}
		
		return 1;
	}

	public int withdrawFromCamp(String campID) {
		// Find the camp
		// Remove the student from the camp's attendees
		return this.getCampController().removeAttendee(this.getCurrentStudent(), campID);
	}

	public ArrayList<Camp> getAttendedCamps() {
		return this.getCampController().getAttendedCamps(this.getCurrentStudent().getUserID());
	}

	public ArrayList<Enquiry> getMyEnquiries() {
		return this.getEnquiryController().findEnquiriesBySender(this.getCurrentStudent().getUserID());
	}

	public ArrayList<Student> getAttendeesFromCamps(ArrayList<Camp> camps) {
		ArrayList<Student> attendees = new ArrayList<>();
		for (Camp camp: camps) {
			for (String userID: camp.getAttendees()) {
				Student attendee = this.getStudentByID(userID);
				if (!attendees.contains(attendee)) {
					attendees.add(attendee);
				}
			}
		}
		return attendees;
	}
}
