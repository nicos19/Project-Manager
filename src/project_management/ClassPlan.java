package project_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 27.12.2021.
 *
 * A class plan is used to describe a class and how it shall look like.
 */
class ClassPlan implements ActionListener {
    private String name;
    private boolean isOpen = false;
    private Description description;

    private ProjectManager projectManager;
    private Fields fields;
    private List<Method> methods = new ArrayList<>();
    private JButton newMethodButton = new JButton("Add new Method",
            IconCreation.createPurplePlus());

    // tabView.basePane contains class plan's visual representation and the
    // representation of all its fields and methods
    private ManagedTabView tabView = new ManagedTabView();

    // contains tabView.basePane and newMethodButton
    private JPanel classPlanPanel = new JPanel(new BorderLayout());


    ClassPlan(String className, ProjectManager pm) {
        name = className;
        description = new Description(name, this);
        projectManager = pm;

        // initialize classPlanPanel
        classPlanPanel.add(tabView.getBasePane(), BorderLayout.CENTER);
        classPlanPanel.add(newMethodButton, BorderLayout.SOUTH);

        // initialize class plan's description
        description.setClassPlanLook();

        // initialize class plan's fields description
        fields = new Fields(this);
        fields.getDescription().setClassFieldsLook();

        // initialize tabView
        // (add class plans's description and fields' description to tabView)
        tabView.setClassManagerView();
        tabView.addDescription(description);
        tabView.addDescription(fields.getDescription());

        // initialize newMethodButton
        newMethodButton.addActionListener(this);
        newMethodButton.setBackground(new Color(84, 0, 168));
        newMethodButton.setForeground(new Color(84, 0, 168));
    }

    /**
     * Gets the name of this class plan.
     * @return the task name
     */
    String getName() {
        return name;
    }

    /**
     * Gets the description of this class plan.
     * @return the description
     */
    Description getDescription() {
        return description;
    }

    /**
     * Gets the panel that contains the visual representation of the class plan
     * (including all fields and methods) and with the newMethodButton.
     * @return the classPlanPanel
     */
    JPanel getClassPlanPanel() {
        return classPlanPanel;
    }

    /**
     * Gets the project manager that is responsible for this class plan.
     * @return the projectManager
     */
    ProjectManager getProjectManager() {
        return projectManager;
    }

    /**
     * Rename this class plan.
     * @param newName the new name
     */
    void rename(String newName) {
        name = newName;
        description.setNewTitle(newName);
    }

    /**
     * Adds a new method to this class plan.
     * @param methodName the name of the new method
     */
    private void addNewMethod(String methodName) {
        if (ClassManager.hasMethod(methodName, methods)) {
            // method with this name already exists -> error message
            DialogCreation.createNameAlreadyExistsDialog(projectManager, "Method");
            return;
        }

        // create new method
        Method newMethod = new Method(methodName, this);
        methods.add(newMethod);

        // show new method in project manager
        tabView.addDescription(newMethod.getDescription());

        projectManager.revalidate();
        projectManager.repaint();
    }

    /**
     * Deletes the given method.
     * @param method the method to be deleted
     */
    void deleteMethod(Method method) {
        methods.remove(method);
        tabView.removeDescription(method.getDescription());

        projectManager.revalidate();
        projectManager.repaint();
    }

    /**
     * Rename the given method.
     * @param method the method to be renamed
     * @param newMethodName the new name for method
     */
    void renameMethod(Method method, String newMethodName) {
        if (ClassManager.hasMethod(newMethodName, methods)) {
            // method with this name already exists -> error message
            DialogCreation.createNameAlreadyExistsDialog(projectManager, "Method");
            return;
        }

        method.rename(newMethodName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newMethodButton) {
            // user clicked button to add new method
            String newMethodName = JOptionPane.showInputDialog(classPlanPanel,"Method Name");
            if (newMethodName != null && newMethodName.length() > 0
                    && !ProjectManager.isBlank(newMethodName)) {
                // user input is legal method name
                addNewMethod(newMethodName);
            }
            else if (newMethodName != null && (newMethodName.length() == 0
                    || ProjectManager.isBlank(newMethodName))) {
                // user input is illegal -> error message
                DialogCreation.createIllegalInputDialog(classPlanPanel);
            }
        }
        else if (e.getSource() instanceof JButton) {
            // check if user clicked any toolbar button of this class plan
            ToolbarButtonsManager.checkToolbarButtonsCall(e, this);
        }
    }
}
