package project_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Nico Sonner on 23.12.2021.
 *
 * A Description instance holds the information for the proper visual representation of
 * a ProjectTask, SubTask, ClassPlan, Method or Fields instance.
 */
class Description {
    private String title;
    private boolean isOpen = true;

    private JPanel basePanel = new JPanel();

    private JToolBar toolBar = new JToolBar();
    private JButton closeButton = new JButton("Close");
    private JButton deleteButton = new JButton("Delete");
    private JButton editNameButton = new JButton("Edit Name");

    private JPanel contentPanel = new JPanel();

    private JPanel titlePanel = new JPanel();
    private JLabel titleLabel;

    private JScrollPane textPane;

    private Color[] oldColors;


    Description(String descriptionTitle, ActionListener actionListener) {
        title = descriptionTitle;

        // initialize basePanel
        basePanel.setLayout(new BorderLayout());

        // initialize toolBar
        toolBar.setFloatable(false);

        // add buttons to toolbar
        toolBar.add(closeButton);
        toolBar.add(deleteButton);
        toolBar.add(editNameButton);

        // initialize buttons
        setButtonColors();
        closeButton.addActionListener(actionListener);
        deleteButton.addActionListener(actionListener);
        editNameButton.addActionListener(actionListener);

        // initialize titlePanel
        titleLabel = new JLabel(title);
        titlePanel.add(titleLabel);

        // initialize textPane
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1), Color.GRAY));
        textPane = new JScrollPane(textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // add titlePanel to textPane's header
        textPane.setColumnHeaderView(titlePanel);

        // set contentPanel
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(textPane);

        // add toolBar and contentPanel, which contains textPane
        // (and this contains titlePanel), to basePanel
        basePanel.add(toolBar, BorderLayout.NORTH);
        basePanel.add(contentPanel, BorderLayout.CENTER);
    }


    /**
     * Gets the basePanel.
     * @return the basePanel
     */
    JPanel getBasePanel() {
        return basePanel;
    }

    /**
     * Assigns a new title for this description.
     * @param newTitle the new title
     */
    void setNewTitle(String newTitle) {
        title = newTitle;
        if (isOpen) {
            titleLabel.setText(title);
        }
        else {
            titleLabel.setText(title + " - CLOSED");
        }
    }

    /**
     * Sets the colors for closeButton (green), deleteButton (red)
     * and editNameButton (blue).
     */
    private void setButtonColors() {
        closeButton.setBackground(new Color(153, 220, 153));
        deleteButton.setBackground(new Color(255, 153, 153));
        editNameButton.setBackground(new Color(220, 240, 255));
    }

    /**
     * Sets the number of rows the text area in textPane of this description shall have.
     * @param rows the number of rows for the text area
     */
    private void setTextAreaRows(int rows) {
        ((JTextArea)textPane.getViewport().getView()).setRows(rows);
    }

    /**
     * Sets the font size of titleLabel.
     * @param fontSize the new font size
     */
    private void setHeadlineFontSize(int fontSize) {
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, fontSize));
    }

    /**
     * Sets the visual appearance of this description so that it is clear
     * that it is a description for a project task.
     */
    void setProjectTaskLook() {
        // set headline font size
        setHeadlineFontSize(18);

        // set number of text area rows
        setTextAreaRows(4);

        // coloring: toolBar
        toolBar.setBackground(new Color(20, 40, 200));
        // contentPanel's border gets same color as toolBar
        contentPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7),
                new Color(20, 40, 200)));
        // coloring: titlePanel
        titlePanel.setBackground(new Color(190, 220, 255));
        // coloring: textArea in textPane
        ((JTextArea)textPane.getViewport().getView()).setBackground(
                new Color(220, 240, 255));

        basePanel.setPreferredSize(new Dimension(300, 145));
        basePanel.setMaximumSize(new Dimension(1000000, 145));
    }

    /**
     * Sets the visual appearance of this description so that it is clear
     * that it is a description for a subtask.
     */
    void setSubTaskLook() {
        // set border around basePanel
        basePanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),
                Color.WHITE));

        // set headline font size and color
        setHeadlineFontSize(14);
        titleLabel.setForeground(Color.WHITE);

        // set number of text area rows
        setTextAreaRows(2);

        // coloring: toolBar
        toolBar.setBackground(new Color(0, 0, 153));
        // contentPanel's border gets same color as toolBar
        contentPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7),
                new Color(0, 0, 153)));
        // coloring: titlePanel
        titlePanel.setBackground(new Color(51, 153, 255));
        // coloring: textArea in textPane
        ((JTextArea)textPane.getViewport().getView()).setBackground(
                new Color(153, 204, 255));

        basePanel.setPreferredSize(new Dimension(300, 110));
        basePanel.setMaximumSize(new Dimension(1000000, 110));
    }

    /**
     * Sets the visual appearance of this description so that it is clear
     * that it is a description for a class plan.
     */
    void setClassPlanLook() {
        // set headline font size  and color
        setHeadlineFontSize(18);
        titleLabel.setForeground(Color.WHITE);

        // set number of text area rows
        setTextAreaRows(4);

        // coloring: toolBar
        toolBar.setBackground(new Color(84, 0, 168));
        // contentPanel's border gets same color as toolBar
        contentPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7),
                new Color(84, 0, 168)));
        // coloring: titlePanel
        titlePanel.setBackground(new Color(136, 17, 255));
        // coloring: textArea in textPane
        ((JTextArea)textPane.getViewport().getView()).setBackground(
                new Color(220, 187, 255));

        basePanel.setPreferredSize(new Dimension(300, 145));
        basePanel.setMaximumSize(new Dimension(1000000, 145));
    }

    /**
     * Sets the visual appearance of this description so that it is clear
     * that it is a description for a Fields instance.
     */
    void setClassFieldsLook() {
        // Description instance for class fields has no "delete" or "edit" button
        closeButton.setText("Close");
        toolBar.remove(deleteButton);
        toolBar.remove(editNameButton);

        // set border around basePanel
        basePanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),
                Color.WHITE));

        // set headline font size
        setHeadlineFontSize(14);

        // set number of text area rows
        setTextAreaRows(4);

        // coloring: toolBar
        toolBar.setBackground(new Color(240, 240, 60));
        // contentPanel's border gets same color as toolBar
        contentPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7),
                new Color(240, 240, 60)));
        // coloring: titlePanel
        titlePanel.setBackground(new Color(255, 255, 155));
        // coloring: textArea in textPane
        ((JTextArea)textPane.getViewport().getView()).setBackground(
                new Color(255, 255, 204));

        basePanel.setPreferredSize(new Dimension(300, 146));
        basePanel.setMaximumSize(new Dimension(1000000, 146));
    }

    /**
     * Sets the visual appearance of this description so that it is clear
     * that it is a description for a method.
     */
    void setClassMethodLook() {
        // set border around basePanel
        basePanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),
                Color.WHITE));

        // set headline font size
        setHeadlineFontSize(14);

        // set number of text area rows
        setTextAreaRows(2);

        // coloring: toolBar
        toolBar.setBackground(new Color(252, 167, 7));
        // contentPanel's border gets same color as toolBar
        contentPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7),
                new Color(252, 167, 7)));
        // coloring: titlePanel
        titlePanel.setBackground(new Color(255, 211, 60));
        // coloring: textArea in textPane
        ((JTextArea)textPane.getViewport().getView()).setBackground(
                new Color(255, 233, 157));

        basePanel.setPreferredSize(new Dimension(300, 110));
        basePanel.setMaximumSize(new Dimension(1000000, 110));
    }

    /**
     * Sets the visual appearance of this description so that it is clear that
     * the task/class plan/etc. associated with the description was closed.
     */
    void setClosedLook() {
        isOpen = false;

        // change closeButton to "reopen" button
        closeButton.setText("Reopen");

        // change title
        titleLabel.setText(title + " - CLOSED");

        // remember old colors
        oldColors = new Color[]{toolBar.getBackground(), titlePanel.getBackground(),
                ((JTextArea)textPane.getViewport().getView()).getBackground()};

        // coloring: toolBar
        toolBar.setBackground(new Color(0, 102, 0));
        // contentPanel's border gets same color as toolBar
        contentPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7),
                new Color(0, 102, 0)));
        // coloring: titlePanel
        titlePanel.setBackground(new Color(0, 160, 0));
        // coloring: textArea in textPane
        ((JTextArea)textPane.getViewport().getView()).setBackground(
                new Color(170, 255, 170));
    }

    /**
     * Sets the visual appearance of this description so that it is clear that
     * the task/class plan/etc. associated with the description is not closed.
     */
    void setBeforeClosedLook() {
        isOpen = true;

        // reset closeButton text
        closeButton.setText("Close");

        // reset title
        titleLabel.setText(title);

        // reset coloring
        toolBar.setBackground(oldColors[0]);
        contentPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7),
                oldColors[0]));
        titlePanel.setBackground(oldColors[1]);
        ((JTextArea)textPane.getViewport().getView()).setBackground(oldColors[2]);
    }

}
