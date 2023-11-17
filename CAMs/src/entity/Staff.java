package entity;
import java.util.ArrayList;
import java.util.List;

public class Staff extends User{
    private List<Camp> createdCamps;

    public Staff(String userID, String faculty) {
        super(userID, faculty);
        createdCamps = new ArrayList<>();
    }

    public List<Camp> getCamps() {
        return createdCamps;
    }
}
