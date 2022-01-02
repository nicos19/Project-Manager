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
public class SavedProjectPart implements Serializable {
    private static final long serialVersionUID = 2L;
    private String name;
    private boolean isOpen;
    private String textAreaContent;
    private List<SavedProjectPart> children = new ArrayList<>();

    public SavedProjectPart(String projectPartName, Description description,
                            List<SavedProjectPart> projectPartChildren) {
        name = projectPartName;
        isOpen = description.isOpen();
        textAreaContent = description.getTextAreaContent();
        children = projectPartChildren;
    }
}
