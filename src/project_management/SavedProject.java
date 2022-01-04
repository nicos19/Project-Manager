package project_management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 01.01.2022.
 *
 * A SavedProject instances can be serialized to save the status of project.
 */
class SavedProject implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<SavedProjectPart> savedProjectTasks = new ArrayList<>();
    private List<SavedProjectPart> savedClassPlans = new ArrayList<>();

    SavedProject(Project project) {
        name = project.getName();

        // save project tasks of project
        saveProjectTasks(project);

        // save class plans of project
        saveClassPlans(project);
    }

    /**
     * Saves the project tasks of given project as SavedProjectPart instances
     * into projectTasks.
     * @param project the project whose tasks shall be saved
     */
    private void saveProjectTasks(Project project) {
        for(ProjectTask pt : project.getTaskManager().getTasks()) {
            // save subtasks of pt
            List <SavedProjectPart> savedSubTasks = new ArrayList<>();
            for (SubTask st : pt.getSubTasks()) {
                savedSubTasks.add(new SavedProjectPart(st.getName(), st.getDescription(),
                        new ArrayList<SavedProjectPart>()));
            }

            savedProjectTasks.add(new SavedProjectPart(pt.getName(), pt.getDescription(),
                    savedSubTasks));
        }
    }

    /**
     * Saves the class plans of given project as SavedProjectPart instances
     * into classPlans.
     * @param project the project whose class plans shall be saved
     */
    private void saveClassPlans(Project project) {
        for(ClassPlan cp : project.getClassManager().getClassPlans()) {
            List <SavedProjectPart> savedFieldsAndMethods = new ArrayList<>();

            // save fields of cp
            Fields f = cp.getFields();
            savedFieldsAndMethods.add(new SavedProjectPart("Fields", f.getDescription(),
                    new ArrayList<SavedProjectPart>()));

            // save methods of cp
            for (Method m : cp.getMethods()) {
                savedFieldsAndMethods.add(new SavedProjectPart(m.getName(), m.getDescription(),
                        new ArrayList<SavedProjectPart>()));
            }

            savedClassPlans.add(new SavedProjectPart(cp.getName(), cp.getDescription(),
                    savedFieldsAndMethods));
        }
    }

    /**
     * Restores the project that was saved with this SavedProject instance.
     * @return the restored project
     */
    Project restoreProject(ProjectManager projectManager) {
        Project restoredProject = new Project(name);

        // restore all project tasks
        for (SavedProjectPart savedProjectTask : savedProjectTasks) {
            ProjectTask restoredProjectTask = savedProjectTask.restoreAsProjectTask(
                    projectManager);
            restoredProject.getTaskManager().addRestoredProjectTask(restoredProjectTask);
        }

        // restore all class plans
        for (SavedProjectPart savedClassPlan : savedClassPlans) {
            ClassPlan restoredClassPlan = savedClassPlan.restoreAsClassPlan(
                    projectManager);
            restoredProject.getClassManager().addRestoredClassPlan(restoredClassPlan);
        }

        return restoredProject;
    }

}
