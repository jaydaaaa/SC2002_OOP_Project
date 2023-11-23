package controller;
import entity.*;
import java.util.ArrayList;

public class StudentController extends UserController{
	public StudentController(CentralManager centralManager){
        super(centralManager);
    }

	public Student getCurrentStudent() {
        return (Student) this.currentUser;
    }

	public Student getStudentByID(String studentID) {
        return (Student) this.getUserByID(studentID);
    }

	public void setPassword(String newPassword){
        this.getCurrentStudent().setPassword(newPassword);
    }

	public String getFaculty() {
		return this.getCurrentStudent().getFaculty();
	}

	public ArrayList<Camp> getMyCamps() {
		return this.getCurrentStudent().getMyCamps();
	}

	public void registerForCamp(String campName) {
		// Find the camp
		for (Camp camp : this.centralManager.getMasterCamps()) {
			if (camp.getCampName().equals(campName)) {
				// Add the student to the camp's attendees
				camp.getAttendees().add(this.getCurrentStudent());
				// Add the camp to the student's camps
				this.getCurrentStudent().getMyCamps().add(camp);
				break;
			}
		}
	}

	public void withdrawFromCamp(String campName) {
		// Find the camp
		for (Camp camp : this.centralManager.getMasterCamps()) {
			if (camp.getCampName().equals(campName)) {
				// Remove the student from the camp's attendees
				camp.getAttendees().remove(this.getCurrentStudent());
				// Remove the camp from the student's camps
				this.getCurrentStudent().getMyCamps().remove(camp);
				break;
			}
		}
	}

	public ArrayList<Enquiry> getMyEnquiries() {
		return this.getCurrentStudent().getMyEnquiries();
	}

	

	// private EnquiryController enquiryController = new EnquiryController();

	// public void addEnquiry(Student student, String enquiryText) {
	// 	enquiryController.addEnquiry(student, enquiryText);
	// }
	
	// public void deleteEnquiry(Student student, Enquiry enquiry) {
	//         enquiryController.deleteEnquiry(student, enquiry);
	// }

	
	// public void enrollForCamp(Student student, Camp camp) {
	//     if (!student.getMyCamps().contains(camp)) {
	//         student.getMyCamps().add(camp);
	//         System.out.println(student.getUserID() + " has successfully enrolled for " + camp.getCampName());
	//     } else {
	//         System.out.println(student.getUserID() + " is already enrolled in " + camp.getCampName());
	//     }
	// }

	// public void withdrawFromCamp(Student student, Camp camp) {
	//     if (student.getMyCamps().contains(camp)) {
	//         student.getMyCamps().remove(camp);
	//         System.out.println(student.getUserID() + " has successfully withdrawn from " + camp.getCampName());
	//     } else {
	//         System.out.println(student.getUserID() + " is not enrolled in " + camp.getCampName());
	//     }
	// }
}