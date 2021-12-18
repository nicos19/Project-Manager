package project_management;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class SubTask extends Task {
    private ProjectTask parentTask;

    public SubTask(String taskName, ProjectTask projectTask) {
        super(taskName);
        parentTask = projectTask;
    }
}
