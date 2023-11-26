import entity.CentralManager;

/**
 * The CAMSystem class represents the main system for the Camp Application and Management (CAM) system.
 * It contains the central manager and provides a run method to manage the system's execution flow.
 * @author Group 2
 * @since 2023-11-26
 */
public class CAMSystem {
    
	/**
     * The centralManager field holds an instance of CentralManager for managing system data and components.
     */
	CentralManager centralManager;

	/**
     * Constructs the CAMSystem and initializes the central manager.
     */
    public CAMSystem() {
        this.centralManager = new CentralManager();
    }

    /**
     * Runs the CAMSystem, providing a continuous loop for user interactions.
     * Users are prompted to log in, and the system runs indefinitely until the user chooses to exit.
     */
    public void run() {
        while (true) {
            boolean login = this.centralManager.getInput().getBoolean("Do you want to login?");
            if (login) {
                this.centralManager.getUserBoundary().login();
            } else {
                break;
            }
        }
    }
}

