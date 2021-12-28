package project_management;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Nico Sonner on 09.12.2021.
 * A ClassManager instance manages the class manager view in the project manager.
 */
public class ClassManager {
    private JTabbedPane basePane = new JTabbedPane(JTabbedPane.LEFT,
                                                   JTabbedPane.SCROLL_TAB_LAYOUT);
    private java.util.List<ClassPlan> classPlans = new ArrayList<>();
    private int indexOfSelectedClassPlan = -1;

    ClassManager() {
        /*JPanel panelGreen = new JPanel();
        panelGreen.setBackground(Color.GREEN);
        basePane.add(panelGreen);*/
    }

    /**
     * Returns the JTabbedPane that contains all relevant containers for the class
     * manager view.
     * @return the basePane
     */
    JTabbedPane getBasePane() {
        return basePane;
    }

    /**
     * Selects the class plan at given index.
     * @param index index of the class plan to select
     */
    private void selectClassPlan(int index) {
        if (index >= 0 && classPlans.size() > index) {
            indexOfSelectedClassPlan = index;
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
        basePane.setForegroundAt(classPlans.size() - 1, new Color(0, 0, 153));
        selectClassPlan(classPlans.size() - 1);
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
