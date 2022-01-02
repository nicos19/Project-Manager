package project_management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 01.01.2022.
 *
 * A SavedProject instances can be serialized to save the status of project.
 */
public class SavedProject implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<SavedProjectPart> projectTasks = new ArrayList<>();
    private List<SavedProjectPart> classPlans = new ArrayList<>();

    public SavedProject(Project project) {
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

            projectTasks.add(new SavedProjectPart(pt.getName(), pt.getDescription(),
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

            classPlans.add(new SavedProjectPart(cp.getName(), cp.getDescription(),
                    savedFieldsAndMethods));
        }
    }

}
