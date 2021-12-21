package project_management;

import java.awt.*;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class SubTask extends Task {
    private ProjectTask parentTask;

    SubTask(String taskName, ProjectTask projectTask) {
        super(taskName, 14);
        parentTask = projectTask;

        // set task description details
        colorTaskDescription(Color.WHITE,                          // headline color
                             new Color(51, 153, 255),    // headline background color
                             new Color(153, 204, 255));  // textArea color
        setDescriptionTextAreaRows(2);
        setDescriptionTextAreaMaxHeight(52);

        // ensure that there is no space between subtasks
        // (since taskPanel would have empty area under the descriptionPanel)
        getTaskPanel().setMaximumSize(new Dimension(1000000000, 72));
    }
}
