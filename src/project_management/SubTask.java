package project_management;

import java.awt.*;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class SubTask extends Task {
    private ProjectTask parentTask;

    SubTask(String taskName, ProjectTask projectTask) {
        super(taskName);
        parentTask = projectTask;

        // initialize task's description
        getDescription().setSubTaskLook();
    }

    /**
     * Gets the project task that this subtask is part of.
     * @return the parentTask
     */
    ProjectTask getParentTask() {
        return parentTask;
    }
}
