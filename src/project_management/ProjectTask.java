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
    private ProjectManager projectManager;
    private List<SubTask> subTasks = new ArrayList<>();
    private JButton newSubTaskButton = new JButton("Add new Subtask",
            IconCreation.createBluePlus());

    // tabView.basePane contains project task's visual representation and the
    // representation of all its subtasks
    private ManagedTabView tabView = new ManagedTabView();

    // contains tabView.basePane and newSubTaskButton
    private JPanel projectTaskPanel = new JPanel(new BorderLayout());


    ProjectTask(String taskName, ProjectManager pm) {
        super(taskName);
        projectManager = pm;

        // initialize taskPanel
        projectTaskPanel.add(tabView.getBasePane(), BorderLayout.CENTER);
        projectTaskPanel.add(newSubTaskButton, BorderLayout.SOUTH);

        // initialize task's description
        getDescription().setProjectTaskLook();

        // initialize tabView (add project task's description to tabView)
        tabView.setTaskManagerView();
        tabView.addDescription(getDescription());

        // initialize newSubTaskButton
        newSubTaskButton.addActionListener(this);
        newSubTaskButton.setBackground(new Color(0, 0, 153));
        newSubTaskButton.setForeground(new Color(0, 0, 153));
    }

    /**
     * Gets the panel that contains the visual representation of the project task
     * (including all subtasks) and with the newSubTaskButton.
     * @return the projectTaskPanel
     */
    JPanel getProjectTaskPanel() {
        return projectTaskPanel;
    }

    /**
     * Adds a new sub-task to this project task.
     * @param subTaskName the name of the new sub-task
     */
    private void addNewSubTask(String subTaskName) {
        if (TaskManager.hasTask(subTaskName, subTasks)) {
            // subtask with this name already exists -> error message
            DialogCreation.createNameAlreadyExistsDialog(projectManager, "Subtask");
            return;
        }

        // create new subtask
        SubTask newSubTask = new SubTask(subTaskName, this);
        subTasks.add(newSubTask);

        // show new subtask in project manager
        tabView.addDescription(newSubTask.getDescription());

        projectManager.revalidate();
        projectManager.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newSubTaskButton) {
            // user clicked button to add new subtask
            String newSubtaskName = JOptionPane.showInputDialog(projectTaskPanel,"Subtask Name");
            if (newSubtaskName != null && newSubtaskName.length() > 0
                                       && !ProjectManager.isBlank(newSubtaskName)) {
                // user input is legal subtask name
                addNewSubTask(newSubtaskName);
            }
            else if (newSubtaskName != null && (newSubtaskName.length() == 0
                                            || ProjectManager.isBlank(newSubtaskName))) {
                // user input is illegal -> error message
                DialogCreation.createIllegalInputDialog(projectTaskPanel);
            }

        }
    }
}
