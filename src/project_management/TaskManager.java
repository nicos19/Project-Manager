package project_management;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 10.12.2021.
 * A TaskManager instance manages the task manager view in the project manager.
 */
public class TaskManager {
    private JTabbedPane basePane = new JTabbedPane(JTabbedPane.LEFT,
                                                   JTabbedPane.SCROLL_TAB_LAYOUT);
    private List<ProjectTask> tasks = new ArrayList<>();
    private int indexOfSelectedTask = -1;

    public TaskManager() {
        /*JPanel panelBlue = new JPanel();
        panelBlue.setBackground(Color.BLUE);
        basePane.add("Task 1", panelBlue);
        basePane.setForegroundAt(0, Color.BLACK);

        Icon icon = ResourceLoader.createImageIcon(getClass(), "/resources/Checkmark-icon-small.png");
        basePane.setIconAt(0, icon);*/
    }

    /**
     * Returns the JTabbedPane that contains all relevant containers for the task
     * manager view.
     * @return the basePane
     */
    public JTabbedPane getBasePane() {
        return basePane;
    }

    /**
     * Selects the tasks at given index.
     * @param index index of the task to select
     */
    public void selectTask(int index) {
        if (index >= 0 && tasks.size() > index) {
            indexOfSelectedTask = index;
            basePane.setSelectedIndex(index);
            basePane.revalidate();
            basePane.repaint();
        }
    }

    public ProjectTask getSelectedTask() {
        return tasks.get(indexOfSelectedTask);
    }

    /**
     * Creates a new project task for the project associated with this task manager.
     * @param projectTaskName the name of the new task
     */
    public void addNewProjectTask(String projectTaskName, ProjectManager projectManager) {
        if (hasTask(projectTaskName)) {
            // task with this name already exists -> error message
            JOptionPane.showOptionDialog(projectManager,
                                        "Task with this name already exists!","Error",
                                         JOptionPane.DEFAULT_OPTION,
                                         JOptionPane.ERROR_MESSAGE,
                                        null,null, null);
            return;
        }

        // create new task
        ProjectTask newTask = new ProjectTask(projectTaskName);
        tasks.add(newTask);
        basePane.add(projectTaskName, newTask.getTaskPanel());
        selectTask(tasks.size() - 1);
    }

    /**
     * Checks whether tasks list contains a project task with given name.
     * @param name the possible task name
     * @return true if tasks list has a task called "name", false otherwise
     */
    public boolean hasTask(String name) {
        for (ProjectTask t : tasks) {
            if (t.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


}
