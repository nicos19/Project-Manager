package project_management;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nico Sonner on 09.12.2021.
 * A ClassManager instance manages the class manager view in the project manager.
 */
public class ClassManager {
    private JTabbedPane basePane = new JTabbedPane(JTabbedPane.LEFT,
                                                   JTabbedPane.SCROLL_TAB_LAYOUT);

    public ClassManager() {
        JPanel panelGreen = new JPanel();
        panelGreen.setBackground(Color.GREEN);
        basePane.add(panelGreen);
    }

    /**
     * Returns the JTabbedPane that contains all relevant containers for the class
     * manager view.
     * @return the basePane
     */
    public JTabbedPane getBasePane() {
        return basePane;
    }
}
