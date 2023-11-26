
/**
 * The Main class serves as the entry point for the Camp Attendance Management (CAM) system.
 * It contains the main method responsible for initializing and running the CAMSystem.
 * @author Group 2
 * @since 2023-11-26
 */
public class Main {
	
	/**
     * The main method, serving as the entry point for the CAM system.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
	public static void main(String[] args) {
        CAMSystem camSystem = new CAMSystem();
        camSystem.run();
    }
}
