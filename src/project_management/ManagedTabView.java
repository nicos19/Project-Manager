package project_management;

import sun.security.krb5.internal.crypto.Des;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nico Sonner on 24.12.2021.
 *
 * A ManagedTabView instance contains a scrollable pane that shows either
 * the task manager view or the class manager view.
 */
class ManagedTabView {
    private JScrollPane basePane;
    private JPanel contentPanel = new JPanel();

    ManagedTabView() {
        // initialize basePane
        basePane = new JScrollPane (contentPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        basePane.getVerticalScrollBar().setUnitIncrement(10);

        // initialize contentPanel
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    }

    /**
     * Gets the basePane of this ManagedTabView.
     * @return the basePane
     */
    JScrollPane getBasePane() {
        return basePane;
    }

    /**
     * Adds the given description from the contentPanel.
     * @param description the Description instance to be added
     */
    void addDescription(Description description) {
        contentPanel.add(description.getBasePanel());
    }

    /**
     * Removes the given description from the contentPanel.
     * @param description the Description instance to be removed
     */
    void removeDescription(Description description) {
        contentPanel.remove(description.getBasePanel());
    }

    /**
     * Assign the task manager look for this ManagedTabView.
     */
    void setTaskManagerView() {
        JLabel label = new JLabel(" Task Manager");
        label.setForeground(Color.WHITE);

        basePane.setColumnHeaderView(label);
        basePane.getColumnHeader().setBackground(Color.BLACK);
    }

    /**
     * Assign the class manager look for this ManagedTabView.
     */
    void setClassManagerView() {
        JLabel label = new JLabel(" Class Manager");
        label.setForeground(Color.WHITE);

        basePane.setColumnHeaderView(label);
        basePane.getColumnHeader().setBackground(Color.BLACK);
    }
}
