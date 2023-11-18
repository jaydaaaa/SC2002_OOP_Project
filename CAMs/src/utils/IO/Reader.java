package utils.IO;

import entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import org.omg.CORBA.Request;

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
                String name = lst[0];
                String email = lst[1];
                String userID = email.split("@")[1];
                String faculty = lst[2];
                String password = lst[3];
                String userType = lst[4];
                if (Objects.equals(userType, "Student")) {
                    Student student = new Student(name, userID, email, faculty ,password, "Student");
                    users.add(student);
                }
                else if (Objects.equals(userType, "Staff")){
                    Staff staff = new Staff(name, userID, email, faculty ,password, "Staff");
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

    public static ArrayList<Enquiry> readRequests(String fpath) {
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

    public static ArrayList<Camp> readProjects(String fpath) {
        System.out.println("Ingesting projects...");
        ArrayList<Camp> Camp = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fpath));
            String line = reader.readLine();

            while (line != null) {
                String[] lst = line.split("_");
                String campName = lst [0];
                String dates = lst[5];
                String registrationDeadline = lst[]
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
