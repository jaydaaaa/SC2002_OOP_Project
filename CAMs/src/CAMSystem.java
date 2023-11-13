import entity.CentralManager;
public class CAMSystem {
    CentralManager centralManager;

    public CAMSystem() {
        this.centralManager = new CentralManager();
    }

    public void run() {
        this.centralManager.getUserBoundary().login();
    }
}
