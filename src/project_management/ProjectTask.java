package project_management;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class ProjectTask extends Task {
    private List<SubTask> subTasks = new ArrayList<>();

    public ProjectTask(String taskName) {
        super(taskName);
    }

    public void addNewSubTask(String subTaskName) {
        subTasks.add(new SubTask(subTaskName, this));
    }

    public void initializeProjectTaskPanel() {
        JPanel taskPanel = super.getTaskPanel();
        taskPanel.setLayout(new BorderLayout());
    }
}
