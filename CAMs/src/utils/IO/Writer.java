package utils.IO;

import entity.Project;
import entity.Request;
import entity.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Writer {
    public static void writeUsers(String fpath, ArrayList<User> masterUsers) {
        System.out.println("Saving changes to user file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            ArrayList<String> coordinators = new ArrayList<>();
            for (User user: masterUsers) {
                if (!coordinators.contains(user.getUserId())) {
                    String toWrite = user.getName() + '_' + user.getEmail() + '_' + user.getPassword() + '_' + user.getType() + '\n';
                    writer.write(toWrite);
                    if (Objects.equals(user.getType(), "Coordinator")) {
                        coordinators.add(user.getId());
                    }
                }

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeRequests(String fpath, ArrayList<Request> masterRequests) {
        System.out.println("Saving changes to requests file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Request request: masterRequests) {
                String toWrite = request.getType() + '_' + request.getStatus() + '_' + request.getDate() + '_' + request.getProjectID() + '_' +  request.getRequesteeID() + '_' + request.getUpdatedValue() + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeProjects(String fpath, ArrayList<Project> masterProjects) {
        System.out.println("Saving changes to projects file...");
        try {
            FileWriter writer = new FileWriter(fpath);
            for (Project project: masterProjects) {
                String toWrite = project.getProjectID() + "_" + project.getSupervisorID() + "_" + project.getStudentID() + "_" + project.getProjectTitle() + "_" +  project.getProjectStatus() + "_" + project.getCreatedBy() + '\n';
                writer.write(toWrite);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
