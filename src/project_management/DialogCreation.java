package project_management;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nico Sonner on 20.12.2021.
 *
 * A class used to create option dialogs.
 */
public class DialogCreation {

    /**
     * Creates an error message dialog telling that the user's input is illegal.
     * @param source the component that wants to create the dialog
     */
    public static void createIllegalInputDialog(Component source) {
        JOptionPane.showOptionDialog(source,
                "Illegal input!","Error",
                 JOptionPane.DEFAULT_OPTION,
                 JOptionPane.ERROR_MESSAGE,
                null,null, null);
    }

    /**
     * Creates an error message dialog telling that a name the user chose already exists.
     * @param source the component that wants to create the dialog
     * @param nameType "Project" or "Task" or "Subtask"
     */
    public static void createNameAlreadyExistsDialog(Component source, String nameType) {
        JOptionPane.showOptionDialog(source,
                nameType + " with this name already exists!","Error",
                 JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null,null, null);
    }
}
