package project_management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nico Sonner on 29.12.2021.
 *
 * A Fields instance is used to describe the fields of a class.
 * Each class plan has exactly one Fields instance.
 */
public class Fields implements ActionListener {
    private boolean isOpen = false;
    private Description description;
    private ClassPlan parentClassPlan;


    Fields(ClassPlan parentPlan) {
        description = new Description("Fields", this);
        parentClassPlan = parentPlan;

        // initialize fields' description
        getDescription().setClassFieldsLook();
    }


    /**
     * Gets the description of these fields.
     * @return the description
     */
    Description getDescription() {
        return description;
    }

    /**
     * Gets the class plan these fields are part of.
     * @return the parentClassPlan
     */
    ClassPlan getParentClassPlan() {
        return parentClassPlan;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            // check if user clicked any toolbar button of these fields
            // (has only "Close" button)
            ToolbarButtonsManager.checkToolbarButtonsCall(e, this);
        }
    }
}
