package entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import boundary.*;
import controller.*;

import utils.IO.Reader;
import utils.IO.Writer;
import utils.Input;

/**
 * The CentralManager class serves as the central hub for managing data and controlling
 * various aspects of the system. It initializes master arrays for users, camps, suggestions,
 * and enquiries. It also provides methods for ingesting and writing data files, as well as
 * access to different controllers and boundaries.
 * @author Group 2
 * @since 2023-11-20
 */
public class CentralManager {
    // Master Arrays
    private ArrayList<User> MasterUsers;
    private ArrayList<Camp> MasterCamps;
    private ArrayList<Suggestion> MasterSuggestions;
    private ArrayList<Enquiry> MasterEnquiries;

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
    /**
     * Constructs a new CentralManager object, initializing master arrays, controllers,
     * boundaries, and reader and writer files
     */
    public CentralManager () {
        // Current Working Directory
        this.currentWorkingDirectory = System.getProperty("user.dir") + '/';

        // Master Arrays
        this.MasterUsers = new ArrayList<User>();
        this.MasterCamps = new ArrayList<Camp>();
        this.MasterSuggestions = new ArrayList<Suggestion>();
        this.MasterEnquiries = new ArrayList<Enquiry>();

        // Ingest Files
        this.ingestCamps();
        this.ingestUsers();
        // this.ingestCampCMs();
        this.ingestSuggestions();
        this.ingestEnquiries();

        // Scanner
        this.sc = new Scanner(System.in);
        this.input = new Input(this.sc);

        // Initialize Controllers
        this.campController = new CampController(this);
        this.enquiryController = new EnquiryController(this);
        this.suggestionController = new SuggestionController(this);
        this.userController = new UserController(this);
        this.studentController = new StudentController(this);
        this.staffController = new StaffController(this);
        this.campCMController = new CampCMController(this);


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
            // this.writeCampCMs();
            this.writeEnquiries();
            this.writeSuggestions();
        }));
    }

    // Ingest Files
    /**
     * Reads user data from a file and updates the MasterUsers array.
     */
    public void ingestUsers() {
        String fpath = this.currentWorkingDirectory + "data/Users.txt";
        this.MasterUsers = Reader.readUsers(fpath);
        //System.out.print("users:");
        //System.out.print(this.MasterUsers);
    }
    /**
     * Reads camp data from a file and updates the MasterCamps array.
     */
    public void ingestCamps() {
        String fpath = this.currentWorkingDirectory + "data/Camps.txt";
        this.MasterCamps = Reader.readCamps(fpath);
    }

    // public void ingestCampCMs() {
    //     String fpath = this.currentWorkingDirectory + "data/CampCMs.txt";
    //     this.MasterCampCMs = Reader.readCampCMs(fpath);
    // }

    /**
     * Reads enquiry data from a file and updates the MasterEnquiries array.
     */
    public void ingestEnquiries() {
        String fpath = this.currentWorkingDirectory + "data/Enquiries.txt";
        this.MasterEnquiries = Reader.readEnquiries(fpath);
    }

    /**
     * Reads suggestion data from a file and updates the MasterSuggestions array.
     */
    public void ingestSuggestions() {
        String fpath = this.currentWorkingDirectory + "data/Suggestions.txt";
        this.MasterSuggestions = Reader.readSuggestions(fpath);
    }

    // Write files
    /**
     * Writes user data to a file.
     */
    public void writeUsers() {
        String fpath = this.currentWorkingDirectory + "data/Users.txt";
        Writer.writeUsers(fpath, this.MasterUsers);
    }
    /**
     * Writes camp data to a file.
     */
    public void writeCamps() {
        String fpath = this.currentWorkingDirectory + "data/Camps.txt";
        Writer.writeCamps(fpath, this.MasterCamps);
    }

    // public void writeCampCMs() {
    //     String fpath = this.currentWorkingDirectory + "data/CampCMs.txt";
    //     Writer.writeCampCMs(fpath, this.MasterCampCMs);
    // }

    /**
     * Writes enquiry data to a file.
     */
    public void writeEnquiries() {
        String fpath = this.currentWorkingDirectory + "data/Enquiries.txt";
        Writer.writeEnquiry(fpath, this.MasterEnquiries);
    }

    /**
     * Writes suggestion data to a file.
     */
    public void writeSuggestions() {
        String fpath = this.currentWorkingDirectory + "data/Suggestions.txt";
        Writer.writeSuggestions(fpath, this.MasterSuggestions);
    }
    // Boundaries
    /**
     * Gets the UserBoundary associated with this CentralManager.
     * @return The UserBoundary object.
     */
    public UserBoundary getUserBoundary(){return this.userBoundary;}
    /**
     * Gets the StudentBoundary associated with this CentralManager.
     * @return The StudentBoundary object.
     */
    public StudentBoundary getStudentBoundary(){return this.studentBoundary;}
    /**
     * Gets the CampBoundary associated with this CentralManager.
     * @return The CampBoundary object.
     */
    public CampBoundary getCampBoundary(){return this.campBoundary;}
    /**
     * Gets the EnquiryBoundary associated with this CentralManager.
     * @return The EnquiryBoundary object.
     */
    public EnquiryBoundary getEnquiryBoundary() { return this.enquiryBoundary;}
    /**
     * Gets the SuggestionBoundary associated with this CentralManager.
     * @return The SuggestionBoundary object.
     */
    public SuggestionBoundary getSuggestionBoundary() { return this.suggestionBoundary;}
    /**
     * Gets the CampCMBoundary associated with this CentralManager.
     * @return The CampCMBoundary object.
     */
    public CampCMBoundary getCampCMBoundary() {return this.campCMBoundary;}
    /**
     * Gets the StaffBoundary associated with this CentralManager.
     * @return The StaffBoundary object.
     */
    public StaffBoundary getStaffBoundary() {return this.staffBoundary;}

    // Get Master Arrays
    /**
     * Gets the Master camp list
     * @return The ArrayList of camps.
     */
    public ArrayList<Camp> getMasterCamps() {
        return this.MasterCamps;
    }

    /**
     * Gets the Master users list
     * @return The ArrayList of users.
     */
    public ArrayList<User> getMasterUsers() {
        return this.MasterUsers;
    }

    /**
     * Gets the Master enquiries list
     * @return The ArrayList of enquiries.
     */

    public ArrayList<Enquiry> getMasterEnquiries() {
        return this.MasterEnquiries;
    }

    /**
     * Gets the Master suggestions list
     * @return The ArrayList of suggestions.
     */
    public ArrayList<Suggestion> getMasterSuggestions() {
        return this.MasterSuggestions;
    }

    // Get input
    /**
     * Gets the Input object associated with this CentralManager.
     * @return The Input.
     */
    public Input getInput() {
        return this.input;
    }


    // Get Controllers
    /**
     * Gets the UserController associated with this CentralManager.
     * @return The UserController.
     */
    public UserController getUserController() {
        return this.userController;
    }
    /**
     * Gets the StudentController associated with this CentralManager.
     * @return The StudentController.
     */
    public StudentController getStudentController(){
        return this.studentController;
    }
    /**
     * Gets the CampCMController associated with this CentralManager.
     * @return The CampCMController.
     */
    public CampCMController getCampCMController() {
        return this.campCMController;
    }
    /**
     * Gets the CampController associated with this CentralManager.
     * @return The CampController.
     */
    public CampController getCampController() {
        return this.campController;
    }
    /**
     * Gets the StaffController associated with this CentralManager.
     * @return The StaffController.
     */
    public StaffController getStaffController() {
        return this.staffController;
    }
    /**
     * Gets the EnquiryController associated with this CentralManager.
     * @return The EnquiryController.
     */
    public EnquiryController getEnquiryController() {
        return this.enquiryController;
    }
    /**
     * Gets the SuggestionController associated with this CentralManager.
     * @return The SuggestionController.
     */
    public SuggestionController getSuggestionController() {
        return this.suggestionController;
    }
}
