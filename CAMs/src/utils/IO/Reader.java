package utils.IO;

import entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Reader {
    BufferedReader reader;
    public Reader() {

    }

    public static ArrayList<User> readUsers(String fpath) {
        System.out.println("Ingesting users...");
        ArrayList<User> users = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {
                String[] lst = line.split("_");
                String email = lst[1];
                String userID = email.split("@")[0];
                String password = lst[2];
                String userType = lst[3];
                if (Objects.equals(userType, "Student")) {
                    Student student = new Student(name, userID, email, password, "Student");
                    users.add(student);
                }
                else if (Objects.equals(userType, "Supervisor")){
                    Staff staff = new Staff(name, userID, email, password, "Staff");
                    users.add(staff);
                }

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<Request> readRequests(String fpath) {
        System.out.println("Ingesting requests...");
        ArrayList<Request> requests = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {
                String[] lst = line.split("_");
                String type = lst[0];
                String status = lst[1];
                String date = lst[2];
                Integer projectID = Integer.valueOf(lst[3]);
                String requesteeID = lst[4];
                String value = "";
                if (lst.length == 6) {
                    value = lst[5];
                }
                Request request = new Request(projectID, type, requesteeID, status, LocalDate.parse(date), value);
                requests.add(request);
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public static ArrayList<Project> readProjects(String fpath) {
        System.out.println("Ingesting projects...");
        ArrayList<Project> projects = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {
                String[] lst = line.split("_");
                int projectID = Integer.parseInt(lst[0]);
                String supervisorID = lst[1];
                String studentID = lst[2];
                String projectTitle = lst[3];
                int projectStatus = Integer.parseInt(lst[4]);
                String createdBy = lst[5];
                Project project = new Project(projectID, supervisorID, studentID, projectTitle, projectStatus, createdBy);
                projects.add(project);

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
