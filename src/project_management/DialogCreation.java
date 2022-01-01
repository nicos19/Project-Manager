package project_management;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nico Sonner on 20.12.2021.
 *
 * A class used to create option dialogs and to further process inputs from dialogs.
 */
class DialogCreation {

    /**
     * Creates an error message dialog telling that the user's input is illegal.
     * @param source the component that wants to create the dialog
     */
    static void createIllegalInputDialog(Component source) {
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
    static void createNameAlreadyExistsDialog(Component source, String nameType) {
        JOptionPane.showOptionDialog(source,
                nameType + " with this name already exists!","Error",
                 JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null,null, null);
    }

    /**
     * Check if inputString is legal, i. e. inputString must not be the empty string
     * and must not be blank. null is considered as not legal.
     * Attention: If a string is not legal that does not mean that it is illegal
     * (with regard to isInputIllegal(String inputString)).
     * @param inputString the string to check
     * @return true if inputString is legal, false otherwise
     */
    static boolean isInputLegal(String inputString) {
        return inputString != null && inputString.length() > 0 && !ProjectManager.isBlank(inputString);
    }

    /**
     * Check if inputString is illegal, i. e. inputString must be either the empty
     * string or a blank string. null is considered as NOT illegal.
     * Attention: If a string is not illegal that does not mean that it is legal
     * (with regard to isInputLegal(String inputString)).
     * @param inputString the string to check
     * @return true if string is illegal, false otherwise
     */
    static boolean isInputIllegal(String inputString) {
        return inputString != null && (inputString.length() == 0 || ProjectManager.isBlank(inputString));
    }
}
