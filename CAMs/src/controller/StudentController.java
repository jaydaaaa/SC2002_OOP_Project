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