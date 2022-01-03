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

    /**
     * Loads all saved projects which were saved previously.
     * @return the list of loaded SavedProject instances.
     * If no saved projects are loaded, the list is empty.
     */
    static List<SavedProject> loadProjects() {
        List<SavedProject> loadedSavedProjects = new ArrayList<>();

        // get names of files of saved projects
        File savedProjectsDirectory = new File("saved_projects");
        String[] savedProjectsPathnames = savedProjectsDirectory.list();

        // case: getting filenames of saved projects failed
        if (savedProjectsPathnames == null) {
            System.err.println("Error: savedProjectsDirectory.list() returned null.");
            return loadedSavedProjects;
        }

        // deserialize files of saved projects
        for (String filename : savedProjectsPathnames) {
            SavedProject loadedSavedProject = loadProject(filename);
            if (loadedSavedProject == null) {
                // loading of project saved in filename failed
                System.err.println("Error: Loading project " + filename + " failed.");
                return loadedSavedProjects;
            }
            else {
                loadedSavedProjects.add(loadedSavedProject);
            }
        }

        return loadedSavedProjects;
    }

    /**
     * Saves the given project by creating a SavedProject object and serializing this.
     * @param project the project to be saved
     */
    private static void saveProject(Project project) {
        // create file for project
        File file = new File("saved_projects" + File.separator
                + "project__" + project.getName() + ".save");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("An error occurred.");
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
            System.err.println("An error occurred while serializing a savedProject.");
            e.printStackTrace();
        }
    }

    /**
     * Loads a project by deserializing the content of the given file.
     * @param filename the file to be deserialized
     * @return the previously saved project (instance of SavedProject) created out of
     * the deserialization, null if deserialization failed
     */
    private static SavedProject loadProject(String filename) {
        SavedProject loadedSavedProject = null;

        // deserialize content of filename
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            loadedSavedProject = (SavedProject) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e) {
            System.err.println("An error occurred while deserializing file content.");
            e.printStackTrace();
        }

        return loadedSavedProject;
    }

}
