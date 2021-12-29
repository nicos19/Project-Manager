package project_management;

import javax.management.Descriptor;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class Task implements ActionListener{
    private String name;
    private boolean isOpen = false;
    private Description description;


    Task(String taskName) {
        name = taskName;
        description = new Description(name, this);
    }

    /**
     * Gets the name of this task.
     * @return the task name
     */
    String getName() {
        return name;
    }

    /**
     * Gets the description of this task.
     * @return the description
     */
    Description getDescription() {
        return description;
    }

    /**
     * Rename this task.
     * @param newName the new name
     */
    void rename(String newName) {
        name = newName;
        description.setNewTitle(newName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
