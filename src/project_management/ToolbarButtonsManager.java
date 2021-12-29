package project_management;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Nico Sonner on 28.12.2021.
 *
 * A class with methods to process buttons clicks on toolbars, i. e. clicks on
 * "Close", "Reopen", "Delete" or "Edit Name" buttons.
 */
class ToolbarButtonsManager {

    /**
     * Processes a button click on the toolbars, i. e. when the user clicked a
     * "Close", "Reopen", "Delete" or "Edit Name" button.
     * @param e the action event fired by a button that shall be processed
     * @param toolbarOwner the object that the toolbar with the clicked button is part of
     *                     (can be of types ProjectTask, SubTask, ClassPlan or Method)
     */
    static void checkToolbarButtonsCall(ActionEvent e, Object toolbarOwner) {
        Description d;
        ProjectManager pm;

        if (toolbarOwner instanceof ProjectTask) {
            d = ((ProjectTask)toolbarOwner).getDescription();
            pm = ((ProjectTask)toolbarOwner).getProjectManager();
        }
        else if (toolbarOwner instanceof SubTask) {
            d = ((SubTask)toolbarOwner).getDescription();
            pm = ((SubTask)(toolbarOwner)).getParentTask().getProjectManager();
        }
        else if (toolbarOwner instanceof ClassPlan) {
            d = ((ClassPlan)toolbarOwner).getDescription();
            pm = ((ClassPlan)toolbarOwner).getProjectManager();
        }
        else if (toolbarOwner instanceof Method) {
            d = ((Method)toolbarOwner).getDescription();
            pm = ((Method)toolbarOwner).getParentClassPlan().getProjectManager();
        }
        else {
            throw new IllegalArgumentException(toolbarOwner.toString()
                    + "is not an legal argument.");
        }

        if (((JButton) e.getSource()).getText().equals("Close")) {
            // close task
            d.setClosedLook();
        }
        else if (((JButton) e.getSource()).getText().equals("Reopen")) {
            // reopen task
            d.setBeforeClosedLook();
        }
        else if (((JButton) e.getSource()).getText().equals("Delete")) {
            tryDelete(toolbarOwner, pm);
        }
        else if (((JButton) e.getSource()).getText().equals("Edit Name")) {
            tryRename(toolbarOwner, pm);
        }
    }

    /**
     * Opens a dialog that asks if objectToDelete shall be deleted. If user confirms,
     * the object is deleted.
     * @param objectToDelete the object that user chose to delete
     * @param pm the project manager responsible for objectToDelete
     */
    private static void tryDelete(Object objectToDelete, ProjectManager pm) {
        String dialogMessage;
        if (objectToDelete instanceof ProjectTask) {
            dialogMessage = "Delete this Project Task (including all its Subtasks)?";
        }
        else if (objectToDelete instanceof SubTask) {
            dialogMessage = "Delete this Subtask?";
        }
        else if (objectToDelete instanceof ClassPlan) {
            dialogMessage = "Delete this Class Plan?";
        }
        else if (objectToDelete instanceof Method) {
            dialogMessage = "Delete this Method?";
        }
        else {
            throw new IllegalArgumentException(objectToDelete.toString()
                    + "is not an legal argument.");
        }

        // ask for delete confirmation
        int delete = JOptionPane.showOptionDialog(pm, dialogMessage,
                "Please confirm", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE, null, null, null);
        if (delete == JOptionPane.OK_OPTION) {
            // delete objectToDelete
            if (objectToDelete instanceof ProjectTask) {
                pm.getSelectedProject().getTaskManager().deleteProjectTask(
                        (ProjectTask)objectToDelete);
            }
            else if (objectToDelete instanceof SubTask) {
                ((SubTask)objectToDelete).getParentTask().deleteSubTask(
                        (SubTask)objectToDelete);
            }
            else if (objectToDelete instanceof ClassPlan) {
                pm.getSelectedProject().getClassManager().deleteClassPlan(
                        (ClassPlan)objectToDelete);
            }
            else if (objectToDelete instanceof Method) {
                ((Method)objectToDelete).getParentClassPlan().deleteMethod(
                        (Method)objectToDelete);
            }
            else {
                throw new IllegalArgumentException(objectToDelete.toString()
                        + "is not an legal argument.");
            }
        }
    }

    /**
     * Opens a dialog that ask for a new name. If user input is a legal name,
     * the objectToBeRenamed gets the new name.
     * @param objectToBeRenamed the object to be renamed
     * @param pm the project manager responsible for objectToBeRenamed
     */
    private static void tryRename(Object objectToBeRenamed, ProjectManager pm) {
        // ask for new  name
        String newName = JOptionPane.showInputDialog(pm, "New Name");

        // try renaming
        if (DialogCreation.isInputLegal(newName)) {
            // user input is legal name -> rename
            if (objectToBeRenamed instanceof ProjectTask) {
                ((ProjectTask)objectToBeRenamed).rename(newName);
                pm.getSelectedProject().getTaskManager().updateTabTitleOfSelectedTab(newName);
            }
            else if (objectToBeRenamed instanceof SubTask) {
                ((SubTask)objectToBeRenamed).rename(newName);
            }
            else if (objectToBeRenamed instanceof ClassPlan) {
                ((ClassPlan)objectToBeRenamed).rename(newName);
                pm.getSelectedProject().getClassManager().updateTabTitleOfSelectedTab(newName);
            }
            else if (objectToBeRenamed instanceof Method) {
                ((Method)objectToBeRenamed).rename(newName);
            }
            else {
                throw new IllegalArgumentException(objectToBeRenamed.toString()
                        + "is not an legal argument.");
            }
        }
        else if (DialogCreation.isInputIllegal(newName)) {
            // user input is empty or blank -> illegal name -> error message
            DialogCreation.createIllegalInputDialog(pm);
        }
    }
}
