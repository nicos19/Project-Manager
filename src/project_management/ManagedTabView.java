package project_management;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nico Sonner on 24.12.2021.
 */
public class ManagedTabView {
    private JScrollPane basePane;
    private JPanel contentPanel = new JPanel();
    private Description mainDescription;

    ManagedTabView() {
        // initialize basePane
        basePane = new JScrollPane (contentPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        basePane.getVerticalScrollBar().setUnitIncrement(10);


        basePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // initialize contentPanel
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        /*Description task1 = new Description("Task1");
        task1.setProjectTaskLook();

        Description subtask1 = new Description("subtask1");
        subtask1.setSubTaskLook();
        Description subtask2 = new Description("subtask2");
        subtask2.setSubTaskLook();

        contentPanel.add(task1.getBasePanel());
        contentPanel.add(subtask1.getBasePanel());
        contentPanel.add(subtask2.getBasePanel());*/

        /*Description cPlan = new Description("Class Plan 1");
        cPlan.setClassPlanLook();

        Description field1 = new Description("Fields");
        field1.setClassFieldLook();

        Description method1 = new Description("method1");
        method1.setClassMethodLook();
        Description method2 = new Description("method2");
        method2.setClassMethodLook();

        method1.setClosedLook();
        method1.setBeforeClosedLook();

        contentPanel.add(cPlan.getBasePanel());
        contentPanel.add(field1.getBasePanel());
        contentPanel.add(method1.getBasePanel());
        contentPanel.add(method2.getBasePanel());*/

        //setClassManagerView();
    }

    JScrollPane getBasePane() {
        return basePane;
    }

    void addDescription(Description description) {
        contentPanel.add(description.getBasePanel());
    }

    void setTaskManagerView() {
        JLabel label = new JLabel(" Task Manager");
        label.setForeground(Color.WHITE);

        basePane.setColumnHeaderView(label);
        basePane.getColumnHeader().setBackground(Color.BLACK);
    }

    void setClassManagerView() {
        JLabel label = new JLabel(" Class Manager");
        label.setForeground(Color.WHITE);

        basePane.setColumnHeaderView(label);
        basePane.getColumnHeader().setBackground(Color.BLACK);
    }
}
