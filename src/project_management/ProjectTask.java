package project_management;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class ProjectTask extends Task {
    private List<SubTask> subTasks = new ArrayList<>();
    private JButton newSubTaskButton = new JButton("Add New Sub-Task",
                                      ResourceLoader.createImageIcon(getClass(),
                                     "/resources/plus-icon-small-16.png"));

    public ProjectTask(String taskName) {
        super(taskName);

        // initialize taskPanel
        JPanel taskPanel = super.getTaskPanel();
        taskPanel.add(newSubTaskButton, BorderLayout.SOUTH);

        // initialize "Add new Sub-Task"-button
        newSubTaskButton.setBackground(new Color(0, 0, 153));
        newSubTaskButton.setForeground(new Color(0, 0, 153));

        //newSubTaskButton.setContentAreaFilled(false);

        //newSubTaskButton.setOpaque(true);
        //newSubTaskButton.setBorderPainted(true);
        //newSubTaskButton.setBorder(new LineBorder(Color.RED));

        //taskContentPanel.add(new JLabel("DESCRIPTION:"));
        /*taskContentPanel.add(new JLabel("Sub-Task 1"));
        taskContentPanel.add(new JLabel("Sub-Task 2"));
        taskContentPanel.add(new JLabel("Sub-Task 3"));*/
    }

    public void addNewSubTask(String subTaskName) {
        subTasks.add(new SubTask(subTaskName, this));
    }

}
