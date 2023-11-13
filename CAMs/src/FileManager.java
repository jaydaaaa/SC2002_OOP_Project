import java.io.*;
import java.util.*;

public class FileManager {
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public List<User> readFiles() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // First line is header, skip it.
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                users.add(new User(values[0], values[1].split("@")[0].trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void writeFiles(List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<User> readStudents() {
        FileManager studentFileManager = new FileManager(FilePaths.STUDENT_LIST);
        return studentFileManager.readFiles();
    }

    public static List<User> readStaff() {
        FileManager staffFileManager = new FileManager(FilePaths.STAFF_LIST);
        return staffFileManager.readFiles();
    }
}
