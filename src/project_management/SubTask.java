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

        // set task description details
        colorTaskDescription(Color.WHITE,       // headline color
                Color.RED,   // headline background color
                Color.YELLOW);  // textArea color
        setDescriptionTextAreaRows(2);
        setDescriptionTextAreaMaxHeight(52);

        //
        getTaskPanel().setMaximumSize(new Dimension(1000000000, 72));
    }
}
