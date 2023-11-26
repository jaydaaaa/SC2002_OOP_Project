package boundary;

/**
 * The StudentBoundary class represents a student in the system.
 * A student can be enrolled in many courses.
 * This class provides methods for handling student operations such as viewing camps, registering for a camp, 
 * submitting an enquiry, editing an enquiry, deleting an enquiry, and changing password.
 * @author Group 2
 * @since 2023-11-26
 */
import entity.Camp;
import entity.CentralManager;
import entity.Enquiry;
import entity.Student;

import java.util.ArrayList;

import controller.CampController;
import controller.UserController;

/**
 * This class represents the boundary for a Student in the system.
 * It extends the UserBoundary class and provides methods for student operations.
 */
public class StudentBoundary extends UserBoundary{

    /**
    * Constructor for StudentBoundary.
    * @param centralManager The central manager controlling the system.
    */
    public StudentBoundary(CentralManager centralManager){
        super(centralManager);
    }
    boolean needToRelogin = false;

   /**
     * Displays the menu choices for the student.
     */
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
						8.  View my enquiries and replies
						9.  Withdraw from a camp"""
        );
    }

    /**
     * This method handles the operations for the student.
     * It displays a menu of choices and prompts the student to enter their choice.
     * Depending on the student's choice, it calls the appropriate method to perform the chosen operation.
     * If the student's status has been changed from Student to Camp Committee member, it prompts the student to relogin.
     * If the student enters a choice that is not within the valid range (1-10), it prints an error message and prompts the student to enter their choice again.
     * If the student chooses to exit the application, it prints a message indicating that the program has exited.
     */
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

    /**
     * This method allows the student to change their password.
     * It first prompts the student to enter a new password.
     * Then, it calls the `setPassword()` method of `StudentController` to update the student's password.
     * Finally, it prints a confirmation message to inform the student that their password has been successfully reset.
     */
    public void changePassword() {
        //Change password
        String newPassword = this.getLine("Please enter a new password:");
        this.getStudentController().setPassword(newPassword);
        System.out.println("Your password has been successfully reset.");
    }

   /**
     * This method displays the available camps for the student.
     * It first retrieves the student's ID.
     * Then, it calls the `viewCamps()` method of `CampBoundary` to display the camps available to the student.
     */
    public void viewCamps() {
        // View available camps
        String studentID = this.getStudentController().getCurrentStudent().getUserID();
        this.getCampBoundary().viewCamps(studentID);
    }

   /**
     * This method displays the camps that the student is registered for.
     * It first retrieves a list of the student's registered camps.
     * Then, it prints a header.
     * If the student is not registered for any camps, it prints a message indicating this.
     * Otherwise, it prints each camp in the list.
     */
    public void viewMyCamps(){
        //View own camps
        ArrayList<Camp> myCamps = this.getStudentController().getAttendedCamps();
        System.out.println("..........................My registered camps..........................");
        if (myCamps.size() == 0) {
            System.out.println("------------------------- No camp registered -------------------------");
        } else {
            this.getCampBoundary().printCampList(myCamps, this.getStudentController().getCurrentStudent().getUserID());
        }
    }

   /**
     * This method allows the student to register for a camp.
     * It first prints a list of the available camps.
     * Then, it prompts the student to enter the index of the camp they want to register for.
     * If the student enters -1, the method returns and no registration is made.
     * If the student enters a valid index, the method prompts the student to choose whether they want to register as a CampComm.
     * The student is then registered for the chosen camp, and a confirmation message is printed.
     * If the camp is full, the deadline has passed, there are no empty CampComm slots, or the camp period clashes with one of the student's registered camps, an error message is printed and the registration is not made.
     * If the student chooses to register as a CampComm, they are logged out and must log back in to access CampComm functions.
     * @param isCampComm A boolean indicating whether the student is registering as a CampComm.
     */
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

   /**
     * This method allows the student to withdraw from a camp.
     * It first prints a list of the camps that the student is registered for.
     * Then, it prompts the student to enter the index of the camp they want to withdraw from.
     * If the student enters -1, the method returns and no withdrawal is made.
     * If the student enters a valid index, the method attempts to withdraw the student from the chosen camp.
     * If the student is the MainComm for the camp, an error message is printed and the withdrawal is not made.
     * If the withdrawal is successful, a confirmation message is printed.
     * If the student is not a participant of the camp, an error message is printed and the withdrawal is not made.
     * If the student is not registered for any camps, a message is printed and the method returns.
     */
    public void withdrawCamp(){
       while (true) {
           System.out.println("..........................My registered camps..........................");
           // get my camps
           ArrayList<Camp> myCamps = this.getStudentController().getAttendedCamps();
           if (myCamps.size() == 0) {
               System.out.println("------------------------- No camp registered -------------------------");
               return;
           }
           this.getCampBoundary().printCampList(myCamps, this.getStudentController().getCurrentStudent().getUserID());

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

   /**
     * This method allows the student to edit an enquiry.
     * It first prints a list of the student's enquiries.
     * Then, it prompts the student to enter the index of the enquiry they want to edit.
     * If the student enters -1, the method returns and no enquiry is edited.
     * If the student enters a valid index, the method prompts the student to enter their new enquiry text.
     * The enquiry is then updated, and a confirmation message is printed.
     * If the student was not the sender of the enquiry, or if the enquiry has already been answered,
     * an error message is printed and the enquiry is not edited.
     * If the student has not submitted any enquiries, a message is printed and the method returns.
     */
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

   /**
     * This method allows the student to submit an enquiry for a camp.
     * It first prints a list of the camps that the student is attending.
     * Then, it prompts the student to enter the index of the camp they want to enquire about.
     * If the student enters -1, the method returns and no enquiry is submitted.
     * If the student enters a valid index, the method prompts the student to enter their enquiry.
     * The enquiry is then submitted, and a confirmation message is printed.
     */
    public void submitEnquiry(){
        // Submit an enquiry for a camp
        while (true) {
            ArrayList<Camp> myCamps = this.getStudentController().getAttendedCamps();
            this.getCampBoundary().printCampList(myCamps, this.getStudentController().getCurrentStudent().getUserID());
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

    /**
     * This method allows the student to delete an enquiry for a camp.
     * It first prints a list of the student's enquiries.
     * Then, it prompts the student to enter the index of the enquiry they want to delete.
     * If the student enters -1, the method returns and no enquiry is deleted.
     * If the student enters a valid index, the method attempts to delete the enquiry.
     * If the student was not the sender of the enquiry, or if the enquiry has already been answered,
     * an error message is printed and the enquiry is not deleted.
     * If the deletion is successful, a confirmation message is printed.
     * If the student has not submitted any enquiries, a message is printed and the method returns.
     */
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

   /**
     * This method allows the student to view their enquiries.
     * It first prints a header, then retrieves the student's enquiries.
     * If there are no enquiries, it prints a message indicating this.
     * Otherwise, it prints each enquiry.
     * Note: Currently, this method does not support viewing replies to enquiries.
     */
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
