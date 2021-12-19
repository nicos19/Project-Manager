package project_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 09.12.2021.
 * This is a tool to manage projects. The user can create "Tasks" that specify which
 * features shall be implemented for the project.
 * The user can also create "Class Plans" to specify the classes that shall be
 * implemented.
 */
public class ProjectManager extends JFrame implements ItemListener, ActionListener {
    private JPanel mainPanel = new JPanel(new BorderLayout());;
    private JPanel navigationBarPanel = new JPanel();
    private JTabbedPane taskOrClassPane = new JTabbedPane();

    private JComboBox projectsComboBox = new JComboBox();
    private JButton newProjectButton = new JButton("Create new Project",
            ResourceLoader.createImageIcon(getClass(),
                                          "/resources/plus-icon-small-16.png"));
    private JToggleButton taskManagerButton = new JToggleButton("Task Manager");
    private JToggleButton classManagerButton = new JToggleButton("Class Manager");

    private JButton newTaskButton = new JButton("Add New Task",
            ResourceLoader.createImageIcon(getClass(),
                                          "/resources/plus-icon-small-16.png"));
    private JButton newClassPlanButton = new JButton("Add New Class Plan",
            ResourceLoader.createImageIcon(getClass(),
                                          "/resources/plus-icon-small-16.png"));

    private List<Project> projects = new ArrayList<>();
    private int indexOfSelectedProject = -1;


    public ProjectManager(String title) {
        super(title);

        mainPanel.add(navigationBarPanel, BorderLayout.NORTH);
        mainPanel.add(taskOrClassPane, BorderLayout.CENTER);

        initializeNavigationBar();
        newTaskButton.setForeground(new Color(0, 153, 0));
        newTaskButton.setBackground(new Color(0, 153, 0));
        newClassPlanButton.setForeground(new Color(0, 153, 0));
        newClassPlanButton.setBackground(new Color(0, 153, 0));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
    }

    /**
     * Initializes UI elements in the navigation bar at the top of the project manager
     * frame.
     */
    public void initializeNavigationBar() {
        navigationBarPanel.setLayout(new BoxLayout(navigationBarPanel,
                                     BoxLayout.X_AXIS));

        projectsComboBox.addItemListener(this);
        newProjectButton.addActionListener(this);
        taskManagerButton.addActionListener(this);
        classManagerButton.addActionListener(this);
        newTaskButton.addActionListener(this);
        newClassPlanButton.addActionListener(this);

        navigationBarPanel.add(newProjectButton);
        navigationBarPanel.add(projectsComboBox);
        navigationBarPanel.add(taskManagerButton);
        navigationBarPanel.add(classManagerButton);
    }

    /**
     * Selects the project that is at given index (in projects and in projectsComboBox)
     * @param index the index of the project to select
     */
    public void selectProject(int index) {
        if (index >= 0 && projects.size() > index) {
            indexOfSelectedProject = index;
            projectsComboBox.setSelectedIndex(index);
        }
    }

    /**
     * Returns the project that is currently selected by the user.
     * @return the currently selected project
     */
    public Project getSelectedProject() {
        return projects.get(indexOfSelectedProject);
    }

    /**
     * Adds a new project to the project manager.
     * @param projectName the name of the new project
     */
    public void addNewProject(String projectName) {
        if (hasProject(projectName)) {
            // project with this name already exists -> error message
            JOptionPane.showOptionDialog(this,
                    "Project with this name already exists!","Error",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                    null,null, null);
            return;
        }

        // create new project
        Project newProject = new Project(projectName);
        projects.add(newProject);
        projectsComboBox.addItem(projectName);
        // select the newly created project
        selectProject(projects.size() - 1);
        // draw task manager view or class manager view of the new project
        drawTaskOrClassPane();
        revalidate();
        repaint();
    }

    /**
     * Draws the the task manager view of the currently selected project to the project
     * manager frame.
     */
    public void drawTaskPane() {
        if (indexOfSelectedProject == -1) {
            // no project selected
            return;
        }
        mainPanel.remove(taskOrClassPane);
        mainPanel.remove(newClassPlanButton);
        taskOrClassPane = getSelectedProject().getTaskManager().getBasePane();
        mainPanel.add(taskOrClassPane, BorderLayout.CENTER);
        mainPanel.add(newTaskButton, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    /**
     * Draws the the class manager view of the currently selected project to the project
     * manager frame.
     */
    public void drawClassPane() {
        if (indexOfSelectedProject == -1) {
            // no project selected
            return;
        }
        mainPanel.remove(taskOrClassPane);
        mainPanel.remove(newTaskButton);
        taskOrClassPane = getSelectedProject().getClassManager().getBasePane();
        mainPanel.add(taskOrClassPane, BorderLayout.CENTER);
        mainPanel.add(newClassPlanButton, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    /**
     * Draws the task manager view or the class manager view depending on the users
     * selection.
     */
    public void drawTaskOrClassPane() {
        if (taskManagerButton.isSelected()) {
            // user currently uses task manager
            drawTaskPane();
        }
        else if (classManagerButton.isSelected()) {
            // user currently uses class manager
            drawClassPane();
        }
    }


    /**
     * Manages occurring ItemEvents.
     * @param e an ItemEvent
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItemSelectable() != projectsComboBox) {
            // projectsComboBox is not originator of e
            return;
        }

        if (e.getStateChange() == ItemEvent.SELECTED) {
            // user selected project in projectsComboBox
            indexOfSelectedProject = projectsComboBox.getSelectedIndex();

            // draw or task manager or class manager of the selected project
            drawTaskOrClassPane();
        }
    }

    /**
     * Manages occurring ActionEvents.
     * @param e an ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == taskManagerButton) {
            if (taskManagerButton.isSelected()) {
                // click selected taskManagerButton
                drawTaskPane();

                // deselect classManagerButton
                classManagerButton.setSelected(false);
            }
            else {
                // click would deselect taskManagerButton -> avoid this
                taskManagerButton.setSelected(true);
            }
        }
        else if (e.getSource() == classManagerButton) {
            if (classManagerButton.isSelected()) {
                // click selected classManagerButton
                drawClassPane();

                // deselect taskManagerButton
                taskManagerButton.setSelected(false);
            }
            else {
                // click would deselect classManagerButton -> avoid this
                classManagerButton.setSelected(true);
            }
        }
        else if (e.getSource() == newProjectButton) {
            // user clicked button to create new Project
            String newProjectName = JOptionPane.showInputDialog(this, "Project Name");
            if (newProjectName != null && newProjectName.length() > 0 && !isBlank(newProjectName)) {
                // user input is legal project name
                addNewProject(newProjectName);
            }
            else if (newProjectName != null && (newProjectName.length() == 0 || isBlank(newProjectName))) {
                // user input is empty or blank -> illegal name -> error message
                JOptionPane.showOptionDialog(this,
                                             "Illegal project name!","Error",
                                              JOptionPane.DEFAULT_OPTION,
                                              JOptionPane.ERROR_MESSAGE,
                                             null,null, null);
            }
        }
        else if (e.getSource() == newTaskButton) {
            // user clicked button to create new project task
            String newTaskName = JOptionPane.showInputDialog(this, "Task Name");
            if (newTaskName != null && newTaskName.length() > 0) {
                getSelectedProject().getTaskManager().addNewProjectTask(newTaskName, this);
            }
        }
        else if (e.getSource() == newClassPlanButton) {
            // user clicked button to create new class plan

        }
    }

    /**
     * Checks whether projects list contains a project with given name.
     * @param name the possible project name
     * @return true if project list has a project called "name", false otherwise
     */
    public boolean hasProject(String name) {
        for (Project p : projects) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if given string consists of blank chars only.
     * @param s the string to check
     * @return true if s is blank, false otherwise
     */
    public static boolean isBlank(String s) {
        for(int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                // non-blank char found
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        // set Look & Feel
        try {
            // set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println("Error when trying to load System Look & Feel!");
        }

        ProjectManager pm = new ProjectManager(("Project Manager"));
        pm.setSize(600, 200);
        pm.setVisible(true);

        pm.addNewProject("First Project");
        pm.addNewProject("Second Project");
        //pm.projects.get(0).addNewProjectTask("Task 1");
        //pm.projects.get(0).addNewProjectTask("Task 2");


    }
}
