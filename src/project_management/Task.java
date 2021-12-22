package project_management;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class Task implements DocumentListener {
    private String name;
    private boolean isOpen = false;
    private String description = "";

    // base panel -> contains contentPanel and the "Add new Project Task"-button
    private JPanel taskPanel = new JPanel(new BorderLayout());

    // contains descriptionPanel and
    // for project tasks only: contains also sub-tasks panels
    private JPanel contentPanel = new JPanel();
    private JScrollPane scrollPane;

    // contains task description
    private JPanel descriptionPanel = new JPanel();

    // contains description headline (task name), is part of descriptionPanel
    private JPanel descriptionHeadlinePanel = new JPanel();

    // contains textArea of description, is part of descriptionPanel
    private JTextArea descriptionTextArea = new JTextArea();

    private JButton editTaskButton = new JButton("Edit");


    Task(String taskName, float headlineFontSize) {
        name = taskName;

        JToolBar tbar = new JToolBar();
        tbar.setBackground(new Color(0, 0, 153));
        tbar.setFloatable(false);
        JButton firstButton = new JButton("Close Task");
        firstButton.setBackground(new Color(220, 240, 255));
        tbar.add(firstButton);
        tbar.add(new JButton("Delete Task"));
        //tbar.addSeparator();
        tbar.add(new JButton("Edit Name"));
        taskPanel.add(tbar, BorderLayout.NORTH);

        // initialize taskPanel
        scrollPane = new JScrollPane(contentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentPanel.setPreferredSize(new Dimension(500, 500));

        //taskPanel.add(contentPanel, BorderLayout.CENTER);
        scrollPane.setBackground(Color.RED);
        contentPanel.setBackground(Color.YELLOW);
        taskPanel.setBackground(Color.GREEN);
        taskPanel.add(scrollPane, BorderLayout.CENTER);



        // initialize contentPanel
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(descriptionPanel);
        contentPanel.setBackground(Color.WHITE);

        // initialize descriptionPanel
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        descriptionPanel.add(descriptionHeadlinePanel);

        // initialize descriptionHeadlinePanel
        descriptionHeadlinePanel.setLayout(new BoxLayout(descriptionHeadlinePanel, BoxLayout.X_AXIS));
        descriptionHeadlinePanel.setMaximumSize(new Dimension(10000000, 30));
        descriptionHeadlinePanel.setMinimumSize(new Dimension(0, 30));
        descriptionHeadlinePanel.add(editTaskButton);

        // set description headline label
        JLabel label = new JLabel(taskName);
        label.setFont(label.getFont().deriveFont(headlineFontSize));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        descriptionHeadlinePanel.add(label);

        // set description text area
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.getDocument().addDocumentListener(this);
        JScrollPane textScrollPane = new JScrollPane(descriptionTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descriptionPanel.add(textScrollPane);
    }

    /**
     * Gets the name of this task.
     * @return the task name
     */
    String getName() {
        return name;
    }

    /**
     * Gets the JPanel that is the visual representation of the task instance.
     * @return the JPanel (taskPanel)
     */
    JPanel getTaskPanel() {
        return taskPanel;
    }

    /**
     * Gets the contentPanel which contains the task description and the subtasks panels.
     * @return the contentPanel
     */
    JPanel getContentPanel() {
        return contentPanel;
    }

    /**
     * Colors the descriptionPanel of this task,
     * i. e. the edit button, the headline (and its background) and the textArea
     * @param headlineColor the color for the label inside of descriptionHeadlinePanel
     * @param headlineBackgroundColor the background color for descriptionHeadlinePanel
     * @param textAreaColor the background color for descriptionTextArea
     */
    void colorTaskDescription(Color headlineColor, Color headlineBackgroundColor,
                              Color textAreaColor) {
        descriptionHeadlinePanel.getComponent(1).setForeground(headlineColor);
        descriptionHeadlinePanel.setBackground(headlineBackgroundColor);
        descriptionTextArea.setBackground(textAreaColor);

        // edit button gets same color as headline
        editTaskButton.setForeground(headlineColor);
        editTaskButton.setBackground(headlineColor);
    }

    /**
     * Sets the number of rows for descriptionTextArea
     * @param rows the number of rows
     */
    void setDescriptionTextAreaRows(int rows) {
        descriptionTextArea.setRows(rows);
    }

    /**
     * Sets the maximum height of descriptionTextArea.
     * @param height the maximum height
     */
    void setDescriptionTextAreaMaxHeight(int height) {
        descriptionPanel.getComponent(1).setMaximumSize(new Dimension(1000000000, height));
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        // user inserted new text in text area
        Document document = e.getDocument();
        try {
            // remember updated text
            description = document.getText(0, document.getLength());
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // user removed from text area
        Document document = e.getDocument();
        try {
            // remember updated text
            description = document.getText(0, document.getLength());
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // no need to implement this here since no StyledDocuments are used
    }
}
