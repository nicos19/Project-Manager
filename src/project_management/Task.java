package project_management;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class Task {
    private String name;
    private boolean isOpen = false;
    private String description = "";

    // base panel -> contains contentPanel and the "Add new Project Task"-button
    private JPanel taskPanel = new JPanel(new BorderLayout());

    // contains descriptionPanel and
    // for project tasks only: contains also sub-tasks panels
    private JPanel contentPanel = new JPanel();

    // contains task description
    private JPanel descriptionPanel = new JPanel();

    public Task(String taskName) {
        name = taskName;

        // initialize taskPanel
        taskPanel.add(contentPanel, BorderLayout.CENTER);

        // initialize contentPanel
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(descriptionPanel);
        contentPanel.setBackground(new Color(204, 229, 255));

        // initialize descriptionPanel
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("TASK: " + taskName);
        label.setOpaque(true);
        label.setBackground(Color.DARK_GRAY);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        descriptionPanel.add(label);
        descriptionPanel.add(new JTextArea());
        descriptionPanel.setBackground(Color.RED);
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
