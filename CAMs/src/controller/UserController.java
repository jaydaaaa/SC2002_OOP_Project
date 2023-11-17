package controller;
import boundary.BaseBoundary;
import entity.CentralManager;
import entity.User;
import java.util.ArrayList;
import java.util.Objects;
public class UserController extends BaseController{
    ArrayList<User> users;
    User currentUser;

    public UserController(CentralManager centralManager) {
        super(centralManager);
        this.users = this.centralManager.getMasterUsers();
    }

    public void setcurrentUser(String userID) {
        this.currentUser = this.getUserID(userID);
    }

    public User getUserID(String userID) {
        for (User user: this.users) {
            if (Objects.equals(user.getUserId(), userID)) {
                return user;
            }
        }
        return null;
    }
}
