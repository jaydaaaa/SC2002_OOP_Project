package boundary;
import entity.Camp;
import entity.CentralManager;
import entity.Enquiry;
import entity.Student;

import java.util.ArrayList;

import controller.CampController;
import controller.UserController;

public class StudentBoundary extends UserBoundary{
    public StudentBoundary(CentralManager centralManager){
        super(centralManager);
    }
    boolean needToRelogin = false;

    public void displayMenuChoices() {

        System.out.println(
                """
						1.  Change password
						2.  View available camps
						3.  View registered camps
						4.  Register for a camp
						5.  Submit an enquiry for a camp
						6.  Edit an enquiry for a camp
						7.  Delete an enquiry for a camp
						8.  View enquiries and replies
						9.  Withdraw from a camp"""
        );
    }

    public void studentOperations() {
        int choice = 0;
        while (choice != 10) {
            if (this.needToRelogin) {
                System.out.println("As your status has been changed from Student to Camp Committee member, " +
                        "please relogin to access Camp Committee functions.");
                this.needToRelogin = false;
                break;
            }
            System.out.print(
                """
                ========================= Welcome to Student App =========================
                """
            );
            this.displayMenuChoices();
            System.out.print("""
            10. Exit application
            ========================================================================
            """);
            choice = this.getInt("Enter your choice:");
            if (choice < 1 | choice > 10) {
                System.out.println("Enter choice between 1-10 values only");
                continue;
            }
            switch (choice) {
                case 1 -> this.changePassword();
                case 2 -> this.viewCamps();
                case 3 -> this.viewMyCamps();
                case 4 -> this.registerCamp(false);
                case 5 -> this.submitEnquiry();
                case 6 -> this.editEnquiry();
                case 7 -> this.deleteEnquiry();
                case 8 -> this.viewMyEnquiries();
                case 9 -> this.withdrawCamp();
                case 10 -> System.out.println("Program exited.");
            }
        }
    }

    public void changePassword() {
        //Change password
        String newPassword = this.getLine("Please enter a new password:");
        this.getStudentController().setPassword(newPassword);
        System.out.println("Your password has been successfully reset.");
    }

    public void viewCamps() {
        // View available camps
        String studentID = this.getStudentController().getCurrentStudent().getUserID();
        this.getCampBoundary().viewCamps(studentID);
    }

    public void viewMyCamps(){
        //View own camps
        ArrayList<Camp> myCamps = this.getStudentController().getAttendedCamps();
        System.out.println("..........................My registered camps..........................");
        if (myCamps.size() == 0) {
            System.out.println("------------------------- No camp registered -------------------------");
        } else {
            this.getCampBoundary().printCampList(myCamps);
        }
    }

    public void registerCamp(boolean isCampComm) {
        // print all available camps
        while (true) {
            this.viewCamps();
            Student currentStudent = this.getStudentController().getCurrentStudent();
            ArrayList<Camp> camps = this.getCampController().getAvailCamps(currentStudent.getUserID());
            // register for a camp
            int index = this.getInt("Enter the index of the camp you want to register for, or -1 to exit:");

            if (index == -1) {
                return;
            }

            boolean choiceCampComm;
            if (!isCampComm) {
                choiceCampComm = this.input.getBoolean("Register as a CampComm?");
            } else {
                choiceCampComm = false;
            }

            Camp chosenCamp = camps.get(index - 1);
            int success = this.getStudentController().registerForCamp(chosenCamp.getCampID(), choiceCampComm);
            if (success == 1) {
                System.out.println("Camp successfully registered!");
		if (choiceCampComm) {
                    this.needToRelogin = true;
		    break;
                }
            } else if (success == -1) {
                System.out.println("Camp is full, unable to register.");
            } else if (success == -2) {
                System.out.println("Deadline is over, unable to register.");
            } else if (success == -3) {
                System.out.println("No empty camp committee slots, unable to register.");
            }
            else { // success == 0
                System.out.println("Camp period clashes with one of your registered camps, unable to register.");
            }
        }
    }

    public void withdrawCamp(){
       while (true) {
           System.out.println("..........................My registered camps..........................");
           // get my camps
           ArrayList<Camp> myCamps = this.getStudentController().getAttendedCamps();
           if (myCamps.size() == 0) {
               System.out.println("------------------------- No camp registered -------------------------");
               return;
           }
           this.getCampBoundary().printCampList(myCamps);

           // get choice
           int index = this.getInt("Enter the index of the camp you want to withdraw from, enter -1 to exit:");
           if (index == -1) {
               return;
           }
           Camp chosenCamp = myCamps.get(index - 1);

           //withdraw from camp
           int success = this.getStudentController().withdrawFromCamp(chosenCamp.getCampID());
           if (success == 0) {
               System.out.println("Unable to withdraw from a camp you are MainComm for");
           } else if (success == 1) {
               System.out.println("Successfully withdrawed from camp.");
           } else {  // success == -1
               System.out.println("Unable to withdraw from a camp you are not an participant of");
           }
       }
    }

    public void editEnquiry() {
        //Delete an enquiry for a camp
        while (true) {
            System.out.println("..............................My Enquiries..............................");
            Student currentStudent = this.getStudentController().getCurrentStudent();
            ArrayList<Enquiry> enquiries = this.getEnquiryController().findEnquiriesBySender(currentStudent.getUserID());
            if (enquiries.size() > 0) {
                this.getEnquiryBoundary().printEnquiries(enquiries);
                int index = this.getInt("Enter the index of the enquiry you want to edit, -1 to exit:");
                if (index == -1) {
                    return;
                }
                // Edit enquiry
                String newText = this.getLine("Enter new enquiry text");
                Enquiry chosenEnquiry = enquiries.get(index - 1);
                int success = this.getEnquiryController().editEnquiry(currentStudent.getUserID(),
                        chosenEnquiry.getEnquiryID(), newText);
                if (success == 0) {
                    System.out.println("Unable to edit enquiry as you were not the sender.");
                } else if (success == 1) {
                    System.out.println("Enquiry successfully edited");
                } else {
                    System.out.println("Unable to edit enquiry as it was already answered.");
                }
            } else {
                System.out.println("-------------------------No enquiries submitted-------------------------");
                return;
            }
        }
    }

    public void submitEnquiry(){
        // Submit an enquiry for a camp
        while (true) {
            ArrayList<Camp> myCamps = this.getStudentController().getAttendedCamps();
            this.getCampBoundary().printCampList(myCamps);
            int index = this.getInt("Enter the index of the camp you are enquiring for, enter -1 to exit:");
            if (index == -1) {
                return;
            }
            Camp chosenCamp = myCamps.get(index - 1);
            String enquiryText = this.getLine("Enter your enquiry:");
            this.getEnquiryController().submitEnquiry(this.getStudentController().getCurrentStudent(), enquiryText,
                    chosenCamp.getCampID());
            System.out.println("Enquiry for camp successfully submitted.");
        }
    }

    public void deleteEnquiry(){
        //Delete an enquiry for a camp
        while (true) {
            System.out.println("..............................My Enquiries..............................");
            Student currentStudent = this.getStudentController().getCurrentStudent();
            ArrayList<Enquiry> enquiries = this.getEnquiryController().findEnquiriesBySender(currentStudent.getUserID());
            if (enquiries.size() > 0) {
                this.getEnquiryBoundary().printEnquiries(enquiries);
                int index = this.getInt("Enter the index of the enquiry you want to delete, -1 to exit:");
                if (index == -1) {
                    return;
                }
                // Remove enquiry
                Enquiry chosenEnquiry = enquiries.get(index - 1);
                int success = this.getEnquiryController().deleteEnquiry(currentStudent.getUserID(),
                        chosenEnquiry.getEnquiryID());
                if (success == 0) {
                    System.out.println("Unable to delete enquiry as you were not the sender.");
                } else if (success == 1){
                    System.out.println("Enquiry successfully removed");
                } else {
                    System.out.println("Unable to delete enquiry as it was already answered.");
                }
            } else {
                System.out.println("-------------------------No enquiries submitted-------------------------");
                return;
            }
        }
    }

    public void viewMyEnquiries(){
        System.out.println("..............................My Enquiries..............................");
        ArrayList<Enquiry> myEnquiries = this.getStudentController().getMyEnquiries();
        if (myEnquiries.size() == 0) {
            System.out.println("-------------------------No enquiries submitted-------------------------");
        } else {
            // TODO: View replies
            this.getEnquiryBoundary().printEnquiries(myEnquiries);
        }
    }

}
