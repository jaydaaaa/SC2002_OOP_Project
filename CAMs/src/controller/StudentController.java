package controller;
import entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The StudentController class is a controller for managing Student entities.
 * It extends the UserController class and provides additional functionality specific to student members.
 * @author Group 2
 * @since 2023-11-26
 */
public class StudentController extends UserController{
	
    /**
     * Constructs a StudentController with the specified CentralManager.
     *
     * @param centralManager The CentralManager instance to associate with the StudentController.
     */
	public StudentController(CentralManager centralManager){
        super(centralManager);
    }

	/**
     * Gets the current student associated with this controller.
     *
     * @return The current Student member.
     */
	public Student getCurrentStudent() {
        return (Student) this.currentUser;
    }

	 /**
     * Sets a new password for the current student member.
     *
     * @param newPassword The new password to set.
     */
	public void setPassword(String newPassword){
        this.getCurrentStudent().setPassword(newPassword);
    }

	 /**
     * Retrieves the faculty of the current student.
     *
     * @return The faculty of the current student.
     */
	public String getFaculty() {
		return this.getCurrentStudent().getFaculty();
	}

	/**
     * Retrieves a student member by their student ID.
     *
     * @param studentID The student ID of the desired student member.
     * @return The Student member with the specified student ID, or null if not found.
     */
	public Student getStudentByID(String studentID) {
		for (User user: this.getCentralManager().getMasterUsers()) {
			if (user.getUserID().equals(studentID)) {
				return (Student) user;
			}
		}
		return null;
	}

	  /**
     * Checks if the dates of two camps overlap.
     *
     * @param camp1 The first camp.
     * @param camp2 The second camp.
     * @return True if the dates overlap, false otherwise.
     */
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

	/**
     * Registers the current student for a camp.
     *
     * @param campID       The ID of the camp to register for.
     * @param choiceCampComm True if the student wants to join as CampComm, false otherwise.
     * @return 1 if registration is successful, 0 if there is a clash, -1 if the camp is full, -2 if the registration deadline has passed, -3 if no empty CampComm slots.
     */
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

	/**
     * Withdraws the current student from a camp.
     *
     * @param campID The ID of the camp to withdraw from.
     * @return The result of the withdrawal operation: 1 if successful, 0 if the student is a committee member, -1 if the student is not an attendee.
     */
	public int withdrawFromCamp(String campID) {
		// Find the camp
		// Remove the student from the camp's attendees
		return this.getCampController().removeAttendee(this.getCurrentStudent(), campID);
	}

	/**
     * Retrieves a list of camps the current student is participating in.
     *
     * @return An ArrayList of camps the current student is participating in.
     */
	public ArrayList<Camp> getAttendedCamps() {
		return this.getCampController().getAttendedCamps(this.getCurrentStudent().getUserID());
	}

	/**
     * Retrieves a list of enquiries sent by the current student.
     *
     * @return An ArrayList of enquiries sent by the current student.
     */
	public ArrayList<Enquiry> getMyEnquiries() {
		return this.getEnquiryController().findEnquiriesBySender(this.getCurrentStudent().getUserID());
	}

	 /**
     * Retrieves a list of students participating in the specified camps.
     *
     * @param camps The list of camps for which attendees are to be retrieved.
     * @return An ArrayList of students participating in the specified camps.
     */
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
