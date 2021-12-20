package project_management;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class ProjectTask extends Task implements ActionListener {
    private List<SubTask> subTasks = new ArrayList<>();
    private JButton newSubTaskButton = new JButton("Add New Sub-Task",
                                      ResourceLoader.createImageIcon(getClass(),
                                     "/resources/plus-icon-small-16.png"));

    public ProjectTask(String taskName) {
        super(taskName);

        // initialize taskPanel
        JPanel taskPanel = super.getTaskPanel();
        taskPanel.add(newSubTaskButton, BorderLayout.SOUTH);

        // color "Add new Sub-Task"-button
        newSubTaskButton.setBackground(new Color(0, 0, 153));
        newSubTaskButton.setForeground(new Color(0, 0, 153));
    }

    /**
     * Adds a new sub-task to this project task.
     * @param subTaskName the name of the new sub-task
     */
    public void addNewSubTask(String subTaskName) {
        SubTask newSubTask = new SubTask(subTaskName, this);
        subTasks.add(newSubTask);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newSubTaskButton) {
            // user adds a new sub-task

        }
    }
}
