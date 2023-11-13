package entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import boundary.*;
import controller.*;

import utils.IO.Reader;
import utils.IO.Writer;
import utils.Input;

public class CentralManager {
    // Master Arrays
    private ArrayList<User> MasterUsers;
    private ArrayList<Camp> MasterCamps;
    private ArrayList<CampCM> MasterCampCMs;

     // Boundary & Controllers
    private UserBoundary userBoundary;
    private UserController userController;

    private StudentBoundary studentBoundary;
    private StudentController studentController;

    private StaffBoundary staffBoundary;
    private StaffController staffController;

    private CampCMBoundary campCMBoundary;
    private CampCMController campCMController;

    private CampBoundary campBoundary;
    private CampController campController;

    private EnquiryBoundary enquiryBoundary;
    private EnquiryController enquiryController;

    private SuggestionBoundary suggestionBoundary;
    private SuggestionController suggestionController;

    private String currentWorkingDirectory;

    private Input input;

    private Scanner sc;

    // stores all the data of Users, Projects, Requests
    public CentralManager () {
        // Current Working Directory
        this.currentWorkingDirectory = System.getProperty("user.dir") + '/';

        // Master Arrays
        this.MasterUsers = new ArrayList<User>();
        this.MasterCamps = new ArrayList<Camp>();
        this.MasterCampCM = new ArrayList<CampCM>();

        // Ingest Files
        this.ingestCamps();
        this.ingestUsers();
        this.ingestCampCMs();

        // Scanner
        this.sc = new Scanner(System.in);
        this.input = new Input(this.sc);

        // Initialize Controllers
        this.campController = new CampController(this);
        this.enquiryController = new RequestController(this);
        this.suggestionController = new SuggestionController(this);
        this.userController = new UserController(this);
        this.studentController = new StudentController(this);
        this.staffController = new SupervisorController(this);
        this.campCMController = new CoordinatorController(this);


        // Initialize Boundaries
        this.enquiryBoundary = new EnquiryBoundary(this);
        this.suggestionBoundary = new SuggestionBoundary(this);
        this.campBoundary = new CampBoundary(this);
        this.studentBoundary = new StudentBoundary(this);
        this.userBoundary = new UserBoundary(this);
        this.staffBoundary = new StaffBoundary(this);
        this.campCMBoundary = new CampCMBoundary(this);

        // Add ShutdownHook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Running shutdown routine...");
            this.writeUsers();
            this.writeCamps();
            this.writeCampCMs();
        }));
    }

    // Ingest Files
    public void ingestUsers() {
        String fpath = this.currentWorkingDirectory + "data/Users.txt";
        this.MasterUsers = Reader.readUsers(fpath);
    }

    public void ingestProjects() {
        String fpath = this.currentWorkingDirectory + "data/Camps.txt";
        this.MasterCamps = Reader.readCamps(fpath);
    }

    public void ingestRequests() {
        String fpath = this.currentWorkingDirectory + "data/CampCMs.txt";
        this.MasterCampCMs = Reader.readCampCMs(fpath);
    }

    // Write files
    public void writeUsers() {
        String fpath = this.currentWorkingDirectory + "data/Users.txt";
        Writer.writeUsers(fpath, this.MasterUsers);
    }

    public void writeProjects() {
        String fpath = this.currentWorkingDirectory + "data/Projects.txt";
        Writer.writeCamps(fpath, this.MasterCamps);
    }

    public void writeRequests() {
        String fpath = this.currentWorkingDirectory + "data/Requests.txt";
        Writer.writeCampCMs(fpath, this.MasterCampCMs);
    }


    // Boundaries
    public UserBoundary getUserBoundary(){return this.userBoundary;}
    public StudentBoundary getStudentBoundary(){return this.studentBoundary;}
    public CampBoundary getCampBoundary(){
        return this.campBoundary;
    }
    public EnquiryBoundary getEnquiryBoundary() { return this.enquiryBoundary;}
    public SuggestionBoundary getSuggestionBoundary() { return this.suggestionBoundary;}
    public CampCMBoundary getCampCMBoundary() {
        return this.campCMBoundary;
    }
    public StaffBoundary getStaffBoundary() {
        return this.staffBoundary;
    }

    // Get Master Arrays
    public ArrayList<Camp> getMasterProjects() {
        return this.MasterCamps;
    }

    public ArrayList<CampCM> getMasterRequests() {
        return this.MasterCampCMs;
    }

    public ArrayList<User> getMasterUsers() {
        return this.MasterUsers;
    }

    // Get input
    public Input getInput() {
        return this.input;
    }


    // Get Controllers
    public UserController getUserController() {
        return this.userController;
    }
    public StudentController getStudentController(){
        return this.studentController;
    }

    public CampCMController getCampCMController() {
        return this.campCMController;
    }

    public CampController getCampController() {
        return this.campController;
    }

    public StaffController getStaffController() {
        return this.staffController;
    }

    public EnquiryController getEnquiryController() {
        return this.enquiryController;
    }

    public SuggestionController getSuggestionController() {
        return this.suggestionController;
    }
}