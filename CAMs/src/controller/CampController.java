package controller;
import java.util.ArrayList;

import entity.CampCM;
import entity.Student;
import entity.Camp;

import entity.*;

import java.util.Objects;

public class CampController extends BaseController{

    public CampController(CentralManager centralManager) {
        super(centralManager);
    }
    // Added method to get Camps based on faculty for student
    public ArrayList<Camp> getAvailCamps(String studentID) {
        System.out.println("debug studentID:" + studentID);
        Student student = this.getStudentController().getStudentByID(studentID);
        ArrayList<Camp> facultyCamps = new ArrayList<>();
        for (Camp camp : this.getAllCamps()) {
            if ((camp.getFaculty().equals(student.getFaculty()) || camp.getFaculty().equals("ALL")) && camp.getVisibility() && !camp.getBlacklist().contains(studentID) && !camp.getAttendees().contains(student)) {
                facultyCamps.add(camp);
            }
        }
        return facultyCamps;
    }

    // Added method to get all camps for staff
    public ArrayList<Camp> getAllCamps() {
        return this.getCentralManager().getMasterCamps();
    }

    // Added method to get camps according to staffId
    public ArrayList<Camp> getCampsByStaffID(String staffID) {
        ArrayList<Camp> camps = new ArrayList<>();
        for (Camp camp : this.getAllCamps()) {
            if (Objects.equals(camp.getStaffIC(), staffID)) {
                camps.add(camp);
            }
        }
        return camps;
    }

    public Camp getCampByID(String campID) {
        for (Camp camp: this.getAllCamps()) {
            if (camp.getCampID().equals(campID)) {
                return camp;
            }
        }
        return null;
    }

    public ArrayList<Camp> getAttendedCamps(String studentID) {
        ArrayList<Camp> attendedCamps = new ArrayList<>();
        for (Camp camp: this.getAllCamps()) {
            ArrayList<String> attendees = camp.getAttendees();
            if (attendees.contains(studentID)) {
                attendedCamps.add(camp);
            }
        }
        return attendedCamps;
    }

    // Find object Camp from the masterCamp list taking in string as parameter
    public Camp findCamp(String campName) {
        for (Camp camp : this.getAllCamps()) {
            if(Objects.equals(camp.getCampName(),campName)) {
                return camp;
            }
        }
        return null;
    }

    public int removeAttendee(Student student, String campID) {
        Camp camp = this.getCampByID(campID);
        if (camp.getAttendees().contains(student.getUserID())){
            if (!camp.getCommitteeMembers().contains(student.getUserID())) { // allows for withdrawal if not CampCom
                camp.getAttendees().remove(student.getUserID());
                // Add student to camp's blacklist
                camp.addToBlacklist(student.getUserID());
                return 1;
            } else {
                return 0;
            }
        } else{
            return -1;
        }
    }

    public int addCampCM(Student student, Camp camp) {
        // TODO: check if student is already part of CAMP_COMM_MEM_LIST
        // TODO: check if CC member limit reached
        // TODO: Set campComm flag in Student to campID

        CampCM campCM = new CampCM(student.getName(), student.getUserID(), student.getEmail(), student.getPassword(),
                student.getFaculty(), student.getType(), 0);

        //update CAMP_COMM_MEM_LIST
        camp.getCommitteeMembers().add(campCM.getUserID());
        return 1;
    }

    public void toggleVisibility(Camp camp) {
        if(camp.getVisibility()){
            camp.setVisibility(false);
            System.out.println(camp.getCampName()+" visibility set to off");
        } else{
            camp.setVisibility(true);
            System.out.println(camp.getCampName()+" visibility set to on");
        }
    }

    public void deleteCamp(String campID) {
        Camp camp = this.getCampController().getCampByID(campID);
        this.getCentralManager().getMasterCamps().remove(camp);
    }

    public void editCamp() {
        // TODO
        // params of this method will correspond to all editable attribs of this camp (meaning all attribs except for the campID bcos that is system generated and blackList)
    }

    public void createCamp(String campName, ArrayList<Integer> dates, int registrationDeadline, String userGroup,
                           String location, String description, Integer totalSlots, String staffIC, Integer campCommSlots,
                           boolean visibility) {
        Camp newCamp = new Camp(campName, dates, registrationDeadline, userGroup, location, description, totalSlots,
                staffIC, campCommSlots, visibility, "");
        this.getCentralManager().getMasterCamps().add(newCamp);

    }

}
