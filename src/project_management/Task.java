package project_management;

import javax.management.Descriptor;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Nico Sonner on 11.12.2021.
 */
public class Task {
    private String name;
    private boolean isOpen = false;
    private Description description;


    Task(String taskName) {
        name = taskName;
        description = new Description(name);
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

}
