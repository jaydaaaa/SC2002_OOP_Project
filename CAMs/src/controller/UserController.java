package controller;

import boundary.BaseBoundary;
import entity.CentralManager;
import entity.User;

import java.util.ArrayList;
import java.util.Objects;

// get data from arraylist, manipulate the data anyth that has to do with logic
// student and staff controller and boundary can extend from my user boundary

public class UserController extends BaseController {
    ArrayList<User> users;
    User currentUser;
    public UserController(CentralManager centralManager) {
        super(centralManager);
        this.users = this.centralManager.getMasterUsers();
    }

    public void setCurrentUser(String userID) {
        this.currentUser = this.getUserByID(userID);
    }

    public User getUserByID(String userID) {
        for (User _user: this.users) {
            if (Objects.equals(_user.getUserId(), userID)){
                return _user;
            }
        }
        return null;
    }

    public String login(String userID, String pwd){
        for(User user: this.users){
            if (Objects.equals(userID, user.getUserId()) && Objects.equals(pwd, user.getPassword())){
                return user.getType();
            }

        }
        return "InvalidUser";
    }
}
