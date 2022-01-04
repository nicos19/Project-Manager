package project_management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 01.01.2022.
 *
 * A SavedProjectPart instance can be serialized to save the status of project parts
 * such as project tasks, subtasks, class plans, fields and methods
 * (Fields and Methods instances).
 */
class SavedProjectPart implements Serializable {
    private static final long serialVersionUID = 2L;
    private String name;
    private boolean isOpen;
    private String textAreaContent;
    private List<SavedProjectPart> children = new ArrayList<>();

    SavedProjectPart(String projectPartName, Description description,
                            List<SavedProjectPart> projectPartChildren) {
        name = projectPartName;
        isOpen = description.isOpen();
        textAreaContent = description.getTextAreaContent();
        children = projectPartChildren;
    }

    /**
     * Restores the project task that was saved with this SavedProjectPart instance.
     * @param projectManager the responsible project manager
     * @return the restored project task
     */
    ProjectTask restoreAsProjectTask(ProjectManager projectManager) {
        ProjectTask restoredProjectTask = new ProjectTask(name, projectManager);

        if (!isOpen) {
            // project task must be closed
            restoredProjectTask.getDescription().setClosedLook();
        }

        // set original text area content in project task's description
        restoredProjectTask.getDescription().setTextAreaContent(textAreaContent);

        // set original subtasks
        for (SavedProjectPart child : children) {
            SubTask restoredSubTask = child.restoreAsSubTask(restoredProjectTask);
            restoredProjectTask.addRestoredSubTask(restoredSubTask);
        }

        return restoredProjectTask;
    }

    /**
     * Restores the subtask that was saved with this SavedProjectPart instance.
     * @param parentTask the project task that contains the restored subtask
     * @return the restored subtask
     */
    private SubTask restoreAsSubTask(ProjectTask parentTask) {
        SubTask restoredSubTask = new SubTask(name, parentTask);

        if (!isOpen) {
            // subtask must be closed
            restoredSubTask.getDescription().setClosedLook();
        }

        // set original text area content in subtask's description
        restoredSubTask.getDescription().setTextAreaContent(textAreaContent);

        return restoredSubTask;
    }

    /**
     * Restores the class plan that was saved with this SavedProjectPart instance.
     * @param projectManager the responsible project manager
     * @return the restored class plan
     */
    ClassPlan restoreAsClassPlan(ProjectManager projectManager) {
        ClassPlan restoredClassPlan = new ClassPlan(name, projectManager);

        if (!isOpen) {
            // class plan must be closed
            restoredClassPlan.getDescription().setClosedLook();
        }

        // set original text area content in class plan's description
        restoredClassPlan.getDescription().setTextAreaContent(textAreaContent);

        // set original fields
        SavedProjectPart savedFields = children.get(0);
        restoredClassPlan.setFieldsText(savedFields.textAreaContent);
        children.remove(0);  // remove child representing fields

        // set original methods
        for (SavedProjectPart child : children) {
            Method restoredMethod = child.restoreAsMethod(restoredClassPlan);
            restoredClassPlan.addRestoredMethod(restoredMethod);
        }

        return restoredClassPlan;
    }

    /**
     * Restores the method that was saved with this SavedProjectPart instance.
     * @param parentPlan the class plan that contains the restored method
     * @return the restored method
     */
    private Method restoreAsMethod(ClassPlan parentPlan) {
        Method restoredMethod = new Method(name, parentPlan);

        if (!isOpen) {
            // method must be closed
            restoredMethod.getDescription().setClosedLook();
        }

        // set original text area content in method's description
        restoredMethod.getDescription().setTextAreaContent(textAreaContent);

        return restoredMethod;
    }

}
