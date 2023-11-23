package entity;
import java.util.ArrayList;
import java.util.List;

public class Staff extends User{
    private ArrayList<Camp> createdCamps;

    public Staff(String name, String userID, String email, String faculty, String password, String type) {
        super(name, userID, email, faculty, password, type);
        createdCamps = new ArrayList<>();
    }

    public ArrayList<Camp> getCamps() {
        return createdCamps;
    }
    
    // Added addCamps function
    public void addCamps(Camp newCamp) { this.createdCamps.add(newCamp);}
}
