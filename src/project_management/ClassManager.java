package project_management;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Nico Sonner on 09.12.2021.
 *
 * A ClassManager instance manages the class manager view in the project manager.
 */
class ClassManager {
    private JTabbedPane basePane = new JTabbedPane(JTabbedPane.LEFT,
                                                   JTabbedPane.SCROLL_TAB_LAYOUT);
    private java.util.List<ClassPlan> classPlans = new ArrayList<>();

    ClassManager() {    }

    /**
     * Returns the JTabbedPane that contains all relevant containers for the class
     * manager view.
     * @return the basePane
     */
    JTabbedPane getBasePane() {
        return basePane;
    }

    /**
     * Gets the list of all class plans.
     * @return the classPlans
     */
    List<ClassPlan> getClassPlans() {
        return classPlans;
    }

    /**
     * Updates the title of currently selected tab.
     * @param newTitle the new title
     */
    private void updateTabTitleOfSelectedTab(String newTitle) {
        basePane.setTitleAt(basePane.getSelectedIndex(), newTitle);
    }

    /**
     * Selects the class plan at given index.
     * @param index index of the class plan to select
     */
    private void selectClassPlan(int index) {
        if (index >= 0 && classPlans.size() > index) {
            basePane.setSelectedIndex(index);
            basePane.revalidate();
            basePane.repaint();
        }
    }

    /**
     * Creates a new class plan for the project associated with this class manager.
     * @param classPlanName the name of the new class plan
     * @param projectManager the project manager of this class manager instance
     */
    void addNewClassPlan(String classPlanName, ProjectManager projectManager) {
        if (hasClassPlan(classPlanName)) {
            // class plan with this name already exists -> error message
            DialogCreation.createNameAlreadyExistsDialog(projectManager, "Class Plan");
            return;
        }

        // create new class plan
        ClassPlan newClassPlan = new ClassPlan(classPlanName, projectManager);
        classPlans.add(newClassPlan);
        basePane.add(classPlanName, newClassPlan.getClassPlanPanel());
        basePane.setForegroundAt(classPlans.size() - 1, new Color(84, 0, 168));
        selectClassPlan(classPlans.size() - 1);
    }

    /**
     * Deletes the given class plan.
     * @param classPlan the class plan to be deleted
     */
    void deleteClassPlan(ClassPlan classPlan) {
        // delete classPlan
        int indexOfDeletedPlan = classPlans.indexOf(classPlan);
        basePane.remove(classPlan.getClassPlanPanel());
        classPlans.remove(classPlan);

        // select other class plan (if existing)
        if (classPlans.size() > 0) {
            if (indexOfDeletedPlan == classPlans.size()) {  // last plan was deleted
                selectClassPlan(classPlans.size() - 1);
            }
            else {
                selectClassPlan(indexOfDeletedPlan);
            }
        }
    }

    /**
     * Renames the given the class plan.
     * @param classPlan the class plan to be renamed
     * @param newClassPlanName the new name for classPlan
     * @param projectManager the project manager responsible for this class manager
     */
    void renameClassPlan(ClassPlan classPlan, String newClassPlanName,
                         ProjectManager projectManager) {
        if (hasClassPlan(newClassPlanName)) {
            // class plan with this name already exists -> error message
            DialogCreation.createNameAlreadyExistsDialog(projectManager, "Class Plan");
            return;
        }

        classPlan.rename(newClassPlanName);
        // update tab title of classPlan's tab
        updateTabTitleOfSelectedTab(newClassPlanName);
    }

    /**
     * Checks whether class plan list contains a class plan with given name.
     * @param name the possible class plan name
     * @return true if class plan list has a plan called "name", false otherwise
     */
    private boolean hasClassPlan(String name) {
        for (ClassPlan p : classPlans) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the given list of methods contains a method with given name.
     * @param name the possible method name
     * @param methods the list of methods
     * @return true if methods has a method called "name", false otherwise
     */
    static boolean hasMethod(String name, List<Method> methods) {
        for (Method m : methods) {
            if (m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
