import entity.CentralManager;
public class CAMSystem {
    CentralManager centralManager;

    public CAMSystem() {
        this.centralManager = new CentralManager();
    }

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
