package project_management;

import javax.swing.*;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class Task {
    private String name;
    private boolean isOpen = false;
    private String description = "";
    private JPanel taskPanel = new JPanel();

    public Task(String taskName) {
        name = taskName;
        taskPanel.add(new JLabel("Description: " + taskName));
    }

    /**
     * Returns the name of this task.
     * @return the task name
     */
    public String getName() {
        return name;
    }

    public JPanel getTaskPanel() {
        return taskPanel;
    }

}
