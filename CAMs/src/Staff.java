import java.util.List;

public class Staff extends User {

    private List<Camp> createdCamps;

    public Staff(String userID, String faculty, List<Camp> createdCamps) {
        super(userID, faculty);
        this.createdCamps = createdCamps;
    }

    public List<Camp> getCreatedCamps() {
        return createdCamps;
    }
	
}
