package project_management;

import com.sun.prism.paint.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 10.12.2021.
 *
 * A TaskManager instance manages the task manager view in the project manager.
 */
class TaskManager {
    private JTabbedPane basePane = new JTabbedPane(JTabbedPane.LEFT,
                                                   JTabbedPane.SCROLL_TAB_LAYOUT);
    private List<ProjectTask> tasks = new ArrayList<>();

    TaskManager() {    }

    /**
     * Gets the JTabbedPane that contains all relevant containers for the task
     * manager view.
     * @return the basePane
     */
    JTabbedPane getBasePane() {
        return basePane;
    }

    /**
     * Gets the list of all project tasks.
     * @return the tasks
     */
    List<ProjectTask> getTasks() {
        return tasks;
    }

    /**
     * Updates the title of currently selected tab.
     * @param newTitle the new title
     */
    private void updateTabTitleOfSelectedTab(String newTitle) {
        basePane.setTitleAt(basePane.getSelectedIndex(), newTitle);
    }

    /**
     * Selects the tasks at given index.
     * @param index index of the task to select
     */
    private void selectTask(int index) {
        if (index >= 0 && tasks.size() > index) {
            basePane.setSelectedIndex(index);
            basePane.revalidate();
            basePane.repaint();
        }
    }

    /**
     * Creates a new project task for the project associated with this task manager.
     * @param projectTaskName the name of the new task
     * @param projectManager the project manager of this task manager instance
     */
    void addNewProjectTask(String projectTaskName, ProjectManager projectManager) {
        if (hasTask(projectTaskName)) {
            // project task with this name already exists -> error message
            DialogCreation.createNameAlreadyExistsDialog(projectManager, "Project Task");
            return;
        }

        // create new task
        ProjectTask newTask = new ProjectTask(projectTaskName, projectManager);
        tasks.add(newTask);
        basePane.add(projectTaskName, newTask.getProjectTaskPanel());
        basePane.setForegroundAt(tasks.size() - 1, new Color(20, 40, 200));
        selectTask(tasks.size() - 1);
    }

    /**
     * Adds the given restored project task to this task manager.
     * @param restoredProjectTask the restored project task to be added
     */
    void addRestoredProjectTask(ProjectTask restoredProjectTask) {
        tasks.add(restoredProjectTask);
        basePane.add(restoredProjectTask.getName(),
                restoredProjectTask.getProjectTaskPanel());
        basePane.setForegroundAt(tasks.size() - 1, new Color(20, 40, 200));
        selectTask(tasks.size() - 1);

        if (!restoredProjectTask.getDescription().isOpen()) {
            // restoredProjectTask is closed -> add checkmark at project task's tab
            basePane.setIconAt(basePane.getSelectedIndex(),
                    IconCreation.createGreenCheckmark());
        }
    }

    /**
     * Deletes the given project task.
     * @param projectTask the project task to be deleted
     */
    void deleteProjectTask(ProjectTask projectTask) {
        // delete projectTask
        int indexOfDeletedTask = tasks.indexOf(projectTask);
        basePane.remove(projectTask.getProjectTaskPanel());
        tasks.remove(projectTask);

        // select other project task (if existing)
        if (tasks.size() > 0) {
            if (indexOfDeletedTask == tasks.size()) {  // last task was deleted
                selectTask(tasks.size() - 1);
            }
            else {
                selectTask(indexOfDeletedTask);
            }
        }
    }

    /**
     * Renames the given project task.
     * @param projectTask the project task to be renamed
     * @param newProjectTaskName the new name for projectTask
     * @param projectManager the project manager responsible for this task manager
     */
    void renameProjectTask(ProjectTask projectTask, String newProjectTaskName,
                           ProjectManager projectManager) {
        if (hasTask(newProjectTaskName)) {
            // project task with this name already exists -> error message
            DialogCreation.createNameAlreadyExistsDialog(projectManager, "Project Task");
            return;
        }

        projectTask.rename(newProjectTaskName);
        // update tab title of projectTask's tab
        updateTabTitleOfSelectedTab(newProjectTaskName);
    }

    /**
     * Checks whether tasks list contains a project task with given name.
     * @param name the possible task name
     * @return true if tasks list has a task called "name", false otherwise
     */
    private boolean hasTask(String name) {
        for (ProjectTask t : tasks) {
            if (t.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the given list of subtasks contains a subtask with given name.
     * @param name the possible subtask name
     * @param subTasks the list of subtasks
     * @return true if subTasks has a subtask called "name", false otherwise
     */
    static boolean hasTask(String name, List<SubTask> subTasks) {
        for (SubTask s : subTasks) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


}
