package controller;

import boundary.BaseBoundary;
import entity.CentralManager;
import entity.User;

import java.util.ArrayList;
import java.util.Objects;

// get data from arraylist, manipulate the data anyth that has to do with logic
// student and staff controller and boundary can extend from my user boundary

public class UserController extends BaseController {
    User currentUser;
    public UserController(CentralManager centralManager) {
        super(centralManager);
    }

    public void setCurrentUser(String userID) {
        this.currentUser = this.getUserByID(userID);
    }

    public User getUserByID(String userID) {
        for (User _user: this.centralManager.getMasterUsers()) {
            if (Objects.equals(_user.getUserID(), userID)){
                return _user;
            }
        }
        return null;
    }

    public String login(String userID, String pwd){
        for(User user: this.centralManager.getMasterUsers()){
            if (Objects.equals(userID, user.getUserID()) && Objects.equals(pwd, user.getPassword())){
                return user.getType();
            }
        }
        return "InvalidUser";
    }

    public void setPassword(String userID, String newPassword) {
        this.getUserByID(userID).setPassword(newPassword);
    }
}
