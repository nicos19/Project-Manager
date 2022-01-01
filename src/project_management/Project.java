package project_management;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 09.12.2021.
 *
 * A Project Instance represents one project the user wants to manage with the project
 * manager.
 */
public class Project {
    private String name;
    private TaskManager taskManager = new TaskManager();
    private ClassManager classManager = new ClassManager();


    Project(String projectName) {
        name = projectName;
    }

    /**
     * Returns the name of the project.
     * @return the project name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the TaskManager associated with this project.
     * @return the taskManager
     */
    TaskManager getTaskManager() {
        return taskManager;
    }

    /**
     * Returns the ClassManager associated with this project.
     * @return the classManager
     */
    ClassManager getClassManager() {
        return classManager;
    }

}
