package project_management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 02.01.2022.
 *
 * A class to manage saving and loading of the projects in the project manager.
 */
public class SaveManager {

    /**
     * Saves the projects in the given list.
     * @param projects the projects to be saved
     */
    static void saveProjects(List<Project> projects) {
        for (Project p : projects) {
            saveProject(p);
        }
    }

    static List<Project> loadProjects() {
        List<Project> loadedProjects = new ArrayList<>();


    }

    /**
     * Saves the given project by creating a SavedProject object and serializing this.
     * @param project the project to be saved
     */
    private static void saveProject(Project project) {
        // create file for project
        File file = new File("project_" + project.getName() + ".save");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // create SavedProject instance for project
        SavedProject savedProject = new SavedProject(project);

        // serialize savedProject
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    fileOutputStream);
            objectOutputStream.writeObject(savedProject);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
