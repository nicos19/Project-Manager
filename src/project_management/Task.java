package project_management;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class Task {
    private String name;
    private boolean isOpen = false;
    private String description = "";

    // base panel -> contains contentPanel and the "Add new Project Task"-button
    private JPanel taskPanel = new JPanel(new BorderLayout());

    // contains descriptionPanel and
    // for project tasks only: contains also sub-tasks panels
    private JPanel contentPanel = new JPanel();

    // contains task description
    private JPanel descriptionPanel = new JPanel();

    public Task(String taskName) {
        name = taskName;

        // create colors (BG = background, FG = foreground)
        Color descriptionHeadlineColorBG = new Color(190, 220, 255);
        Color descriptionHeadlineColorFG = new Color(0, 0, 153);
        Color descriptionTextColorBG = new Color(204, 229, 255);

        // initialize taskPanel
        taskPanel.add(contentPanel, BorderLayout.CENTER);

        // initialize contentPanel
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(descriptionPanel);
        //contentPanel.setBackground(new Color(204, 229, 255));

        // initialize descriptionPanel
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        JPanel descriptionHeadlinePanel = new JPanel();
        descriptionHeadlinePanel.setMaximumSize(new Dimension(10000000, 20));
        descriptionHeadlinePanel.setBackground(descriptionHeadlineColorBG);
        //descriptionHeadlinePanel.setLayout(new BoxLayout(descriptionHeadlinePanel, BoxLayout.X_AXIS));
        descriptionPanel.add(descriptionHeadlinePanel);

        // set description headline
        JLabel label = new JLabel(taskName);
        label.setFont(label.getFont().deriveFont(14f));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        label.setForeground(descriptionHeadlineColorFG);
        //label.setOpaque(true);
        //label.setBackground(Color.DARK_GRAY);
        //label.setHorizontalAlignment(SwingConstants.RIGHT);
        descriptionHeadlinePanel.add(label);

        // set description text area

        JTextArea textArea = new JTextArea();
        textArea.setRows(4);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(descriptionTextColorBG);

        JScrollPane textScrollPane = new JScrollPane(textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textScrollPane.setMaximumSize(new Dimension(1000000000, 85));
        descriptionPanel.add(textScrollPane);

    }

    /**
     * Returns the name of this task.
     * @return the task name
     */
    public String getName() {
        return name;
    }

    public JPanel getTaskPanel() {
        return taskPanel;
    }

}
