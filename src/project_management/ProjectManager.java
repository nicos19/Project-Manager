package project_management;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 09.12.2021.
 *
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
    private JButton newProjectButton = new JButton("Create new Project");
    private JButton deleteProjectButton = new JButton("Delete Project");
    private JToggleButton taskManagerButton = new JToggleButton("Task Manager");
    private JToggleButton classManagerButton = new JToggleButton("Class Manager");

    private JButton newTaskButton = new JButton("Add new Project Task",
            IconCreation.createGreenPlus());
    private JButton newClassPlanButton = new JButton("Add new Class Plan",
            IconCreation.createGreenPlus());

    private List<Project> projects = new ArrayList<>();
    private String selectedProject = "";


    private ProjectManager(String title) {
        super(title);

        // initialize mainPanel
        mainPanel.add(navigationBarPanel, BorderLayout.NORTH);
        mainPanel.add(taskOrClassPane, BorderLayout.CENTER);

        initializeNavigationBar();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
    }

    /**
     * Initializes UI elements in the navigation bar at the top of
     * the project manager frame.
     */
    private void initializeNavigationBar() {
        navigationBarPanel.setLayout(new BoxLayout(navigationBarPanel,
                                     BoxLayout.X_AXIS));

        // add listeners to UI elements
        projectsComboBox.addItemListener(this);
        newProjectButton.addActionListener(this);
        deleteProjectButton.addActionListener(this);
        taskManagerButton.addActionListener(this);
        classManagerButton.addActionListener(this);
        newTaskButton.addActionListener(this);
        newClassPlanButton.addActionListener(this);

        //projectsComboBox.setModel(new ProjectMutableModel());

        // set button colors
        newProjectButton.setForeground(new Color(0, 153, 0));
        newProjectButton.setBackground(new Color(0, 153, 0));
        deleteProjectButton.setForeground(new Color(174, 0, 30));
        deleteProjectButton.setBackground(new Color(174, 0, 30));
        newTaskButton.setForeground(new Color(0, 153, 0));
        newTaskButton.setBackground(new Color(0, 153, 0));
        newClassPlanButton.setForeground(new Color(0, 153, 0));
        newClassPlanButton.setBackground(new Color(0, 153, 0));

        // add UI elements to navigation bar
        navigationBarPanel.add(newProjectButton);
        navigationBarPanel.add(deleteProjectButton);
        navigationBarPanel.add(projectsComboBox);
        navigationBarPanel.add(taskManagerButton);
        navigationBarPanel.add(classManagerButton);
    }


    /**
     * Selects the project with given name in projects and in projectsComboBox.
     * @param projectName the name of the project to be selected
     */
    private void selectProject(String projectName) {
        if (hasProject(projectName)) {
            //selectedProject = projectName;
            projectsComboBox.setSelectedItem(projectName);
        }
    }

    /**
     * Returns the project that is currently selected by the user.
     * Throws RuntimeException if no project is selected.
     * @return the currently selected project
     */
    Project getSelectedProject() {
        for (Project p : projects) {
            if (p.getName().equals(selectedProject)) {
                return p;
            }
        }
        throw new RuntimeException("Project with name '"
                + selectedProject + "' (selectedProject) not found. ");
    }

    /**
     * Adds a new project to the project manager.
     * @param projectName the name of the new project
     */
    private void addNewProject(String projectName) {
        if (hasProject(projectName)) {
            // project with this name already exists -> error message
            DialogCreation.createNameAlreadyExistsDialog(this, "Project");
            return;
        }

        // create new project
        Project newProject = new Project(projectName);
        projects.add(newProject);
        projectsComboBox.addItem(projectName);
        // select the newly created project
        selectProject(projectName);
        // draw task manager view or class manager view of the new project
        drawTaskOrClassPane();
        revalidate();
        repaint();
    }

    /**
     * Deletes the currently selected project.
     */
    private void deleteSelectedProject() {
        projects.remove(getSelectedProject());
        projectsComboBox.removeItem(projectsComboBox.getSelectedItem());

        // if no project exists
        if (projects.size() == 0) {
            // remember that no project is selected
            selectedProject = "";

            drawTaskOrClassPane();  // removes drawn task/class pane if drawn before

            revalidate();
            repaint();
        }
    }

    /**
     * Draws the the task manager view of the currently selected project to the project
     * manager frame.
     */
    private void drawTaskPane() {
        if (selectedProject.equals("")) {
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
    private void drawClassPane() {
        if (selectedProject.equals("")) {
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
    private void drawTaskOrClassPane() {
        if (taskManagerButton.isSelected()) {
            // user currently uses task manager
            if (selectedProject.equals("")) {
                // no project selected
                mainPanel.remove(taskOrClassPane);
                mainPanel.remove(newTaskButton);
                return;
            }
            drawTaskPane();
        }
        else if (classManagerButton.isSelected()) {
            // user currently uses class manager
            if (selectedProject.equals("")) {
                // no project selected
                mainPanel.remove(taskOrClassPane);
                mainPanel.remove(newClassPlanButton);
                return;
            }
            drawClassPane();
        }

        revalidate();
        repaint();
    }


    /**
     * Manages occurring ItemEvents.
     * Especially ItemEvents associated with projectsComboBox.
     * @param e an ItemEvent
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItemSelectable() != projectsComboBox) {
            // projectsComboBox is not originator of e
            return;
        }

        if (e.getStateChange() == ItemEvent.SELECTED) {
            // user selected project in projectsComboBox (or a new project was added)
            selectedProject = (String)projectsComboBox.getSelectedItem();

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
            if (DialogCreation.isInputLegal(newProjectName)) {
                // user input is legal project name
                addNewProject(newProjectName);
            }
            else if (DialogCreation.isInputIllegal(newProjectName)) {
                // user input is empty or blank -> illegal name -> error message
                DialogCreation.createIllegalInputDialog(this);
            }
        }
        else if (e.getSource() == deleteProjectButton) {
            // user clicked button to delete currently open project
            // ask for confirmation
            int delete = JOptionPane.showOptionDialog(this, "Delete this Project?",
                    "Please confirm", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null, null);
            if (delete == JOptionPane.OK_OPTION) {
                // delete project
                deleteSelectedProject();
            }
        }
        else if (e.getSource() == newTaskButton) {
            // user clicked button to create new project task
            String newTaskName = JOptionPane.showInputDialog(this, "Task Name");
            if (DialogCreation.isInputLegal(newTaskName)) {
                // user input is legal project task name
                getSelectedProject().getTaskManager().addNewProjectTask(newTaskName, this);
            }
            else if (DialogCreation.isInputIllegal(newTaskName)) {
                // user input is empty or blank -> illegal name -> error message
                DialogCreation.createIllegalInputDialog(this);
            }
        }
        else if (e.getSource() == newClassPlanButton) {
            // user clicked button to create new class plan
            String newClassPlanName = JOptionPane.showInputDialog(this, "Class Plan Name");
            if (DialogCreation.isInputLegal(newClassPlanName)) {
                // user input is legal class plan name
                getSelectedProject().getClassManager().addNewClassPlan(newClassPlanName, this);
            }
            else if (DialogCreation.isInputIllegal(newClassPlanName)) {
                // user input is empty or blank -> illegal name -> error message
                DialogCreation.createIllegalInputDialog(this);
            }
        }
    }

    /**
     * Checks whether projects list contains a project with given name.
     * @param name the possible project name
     * @return true if project list has a project called "name", false otherwise
     */
    private boolean hasProject(String name) {
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
    static boolean isBlank(String s) {
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
            // set System L&F, attention: program only optimized for Windows L & F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println("Error when trying to load System Look & Feel!");
        }

        ProjectManager pm = new ProjectManager(("Project Manager"));
        pm.setSize(600, 800);
        pm.setVisible(true);

        pm.addNewProject("First Project");
        pm.addNewProject("Second Project");

    }
}
