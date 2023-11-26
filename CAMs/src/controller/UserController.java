package controller;

import boundary.BaseBoundary;
import entity.CentralManager;
import entity.User;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The UserController class is a controller for managing User entities.
 * It extends the BaseController class and provides common functionality for user-related operations.
 * @author Group 2
 * @since 2023-11-26
 */
public class UserController extends BaseController {
    
	/**
     * The currentUser field represents the currently logged-in user.
     */
	User currentUser;
	
	/**
     * Constructs a UserController with the specified CentralManager.
     *
     * @param centralManager The CentralManager instance to associate with the UserController.
     */
    public UserController(CentralManager centralManager) {
        super(centralManager);
    }

    /**
     * Sets the current user based on the provided user ID.
     *
     * @param userID The user ID of the user to set as the current user.
     */
    public void setCurrentUser(String userID) {
        this.currentUser = this.getUserByID(userID);
    }

    /**
     * Retrieves a user based on the provided user ID.
     *
     * @param userID The user ID of the specified user.
     * @return The User instance with the specified user ID, or null if not found.
     */
    public User getUserByID(String userID) {
        for (User _user: this.centralManager.getMasterUsers()) {
            if (Objects.equals(_user.getUserID(), userID)){
                return _user;
            }
        }
        return null;
    }

    /**
     * Attempts to log in a user with the provided user ID and password.
     *
     * @param userID The user ID for login.
     * @param pwd    The password for login.
     * @return The type of the logged-in user (e.g., "Student," "Staff," "CampCM"), or "InvalidUser" if the login is unsuccessful.
     */
    public String login(String userID, String pwd){
        for(User user: this.centralManager.getMasterUsers()){
            if (Objects.equals(userID, user.getUserID()) && Objects.equals(pwd, user.getPassword())){
                return user.getType();
            }
        }
        return "InvalidUser";
    }
}
