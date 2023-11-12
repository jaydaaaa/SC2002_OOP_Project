import java.util.HashMap;
import java.util.Map;

public class LoginAuthentication {

    private Map<String, String> passwordMap;

    public LoginAuthentication() {
        this.passwordMap = new HashMap<>();
    }

    public void register(String userID, String password) {
        if (!passwordMap.containsKey(userID)) {
            passwordMap.put(userID, password);
            System.out.println("Registration successful for user: " + userID);
        } else {
            System.out.println("User ID already exists. Please choose another one.");
        }
    }

    public void changePassword(String userID, String newPassword) {
        if (passwordMap.containsKey(userID)) {
            passwordMap.put(userID, newPassword);
            System.out.println("Password changed successfully for user: " + userID);
        } else {
            System.out.println("User not found. Password change failed.");
        }
    }

    public boolean login(String userID, String password) {
        return passwordMap.containsKey(userID) && passwordMap.get(userID).equals(password);
    }
}
