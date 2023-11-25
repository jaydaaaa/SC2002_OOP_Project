package controller;

import entity.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class CampCMController extends StudentController{


	public CampCMController(CentralManager centralManager){
		super(centralManager);
	}

	public CampCM getCurrentCampCM() {
		return (CampCM) this.currentUser;
	}

	public CampCM getStudentByID(String studentID) {
		for (User user: this.getCentralManager().getMasterUsers()) {
			if (user.getUserID().equals(studentID)) {
				return (CampCM) user;
			}
		}
		return null;
	}

	public void replyEnquiry(Enquiry enquiry, String replyText) {
		String studentID = this.getCampCMController().getCurrentCampCM().getUserID();
		this.getEnquiryController().replyEnquiry(enquiry, replyText, studentID);
		CampCM currentCM = this.getCampCMController().getCurrentCampCM();

		// increment points
		currentCM.setPoints(currentCM.getPoints() + 1);
	}

	public int registerForCamp(String campID) {
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
		CampCM currentStudent = this.getCurrentCampCM();
		userID = currentStudent.getUserID();
		newCamp.addAttendee(userID);
		return 1;
	}
}
