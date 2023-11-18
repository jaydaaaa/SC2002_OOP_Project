package boundary;
import entity.CentralManager;
import entity.Enquiry;
import entity.Student;

import java.util.ArrayList;

import controller.UserController;

public class StudentBoundary extends UserBoundary{
    public StudentBoundary(CentralManager centralManager){
        super(centralManager);
    }

	public void displayMenuChoices() {

        System.out.println(
                """
                        ========================= Welcome to Student App =========================
                        1.  Change password
                        2.  View available camps
                        3.  View my camps
                        4.  Register for a camp
                        5.  Submit an enquiry for a camp
						6.  Delete an enquiry for a camp
                        7.  View enquiries and replies
                        8.  Deregister for a camp
                        9.  Exit application
                        ========================================================================
                """
        );
    }

	public void changePassword() {
        //Change password
        String newPassword = this.getLine("Please enter a new password:");
        this.getStudentController().setPassword(newPassword);
        System.out.println("Your password has been successfully reset.");
    }

    public void viewCamps() {
        // View available camps
		String faculty = /*Missing controller */.getFaculty();
        this.getCampBoundary().viewCamps("faculty");
    }

    // public void viewRegisteredProject() {
    //     //View own registered project
    //     if (this.getStudentController().getRegistered()) {
    //         int projectID = this.getStudentController().getRegisteredProject();
    //         this.getProjectBoundary().viewProjectLine(projectID);
    //     } else {
    //         System.out.println("You are not registered in any project. Unable to view registered project.");
    //     }
    // }

    // public void viewRequestHistory() {
    //     ArrayList<Request> requests = this.getStudentController().getRequestsHistory();
    //     this.getRequestBoundary().viewRequestsHistory(requests);
    // }

    // public void requestAllocation() {
    //     // Request allocation
    //     this.viewProjects();
    //     String studentID = this.getStudentController().getCurrentStudent().getId();
    //     if (this.getRequestController().checkPendingProjectRequest(studentID)) {
    //         System.out.println("Existing project allocation request is pending processing. Please wait for the current one to be processed before submitting another request. Current request:");
    //     } else {
    //         if (!this.getStudentController().getRegistered()) {
    //             String projectID = this.getLine("Please enter the projectID you would like to be allocated to:");
    //             while (!this.getProjectController().validateAvailProjectID(Integer.parseInt(projectID))) {
    //                 System.out.print("Invalid project id. ");
    //                 projectID = this.getLine("Please enter the projectID you would like to be allocated to:");
    //             }
    //             this.getStudentController().requestAllocation(Integer.valueOf(projectID), this.getStudentController().getCurrentStudent().getId());
    //             System.out.println("Your request has been submitted");
    //         } else {
    //             System.out.println("You are currently allocated to a FYP and do not have access to available project list");
    //         }
    //     }
    // }

    // public void requestChangeTitle() {
    //     //Request to change title
    //     if (this.getStudentController().getRegistered()) {
    //         String newProjectTitle = this.getLine("Enter you new project title: ");
    //         this.getStudentController().requestChangeTitle(newProjectTitle, this.getStudentController().getCurrentStudent().getId(), this.getStudentController().getRegisteredProject().toString());
    //         System.out.println("Your title change request has been submitted");
    //     } else {
    //         System.out.println("You are not registered in any project. Unable to change title.");
    //     }
    // }

    // public void requestDeallocation() {
    //     //Request de-allocation
    //     if (this.getStudentController().getCurrentStudent().getRegistered()){
    //         System.out.println("Your deallocation request has been submitted");
    //         this.getStudentController().requestDeAllocation(this.getStudentController().getRegisteredProject().toString(), this.getStudentController().getCurrentStudent().getId());
    //     } else{
    //         System.out.println("You are not registered in any project. Please try again!");
    //     }
    // }

    // public void studentOperations() {
    //     int choice = 0;
    //     while (choice != 8) {
    //         this.displayMenuChoices();
    //         choice = this.getInt("Enter your choice:");
    //         if (choice < 1 | choice > 8) {
    //             System.out.println("Enter choice between 1-8 values only");
    //             continue;
    //         }
    //         switch (choice) {
    //             case 1 -> this.changePassword();
    //             case 2 -> this.viewProjects();
    //             case 3 -> this.requestAllocation();
    //             case 4 -> this.viewRegisteredProject();
    //             case 5 -> this.viewRequestHistory();
    //             case 6 -> this.requestChangeTitle();
    //             case 7 -> this.requestDeallocation();
    //             case 8 -> System.out.println("Program exited.");
    //         }
    //     }
    // }
}