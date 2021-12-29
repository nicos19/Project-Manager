package project_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class SubTask extends Task implements ActionListener{
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            // check if user clicked any toolbar button of this subtask
            ToolbarButtonsManager.checkToolbarButtonsCall(e, this);
        }
    }
}
