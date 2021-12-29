package project_management;

import javax.swing.*;

/**
 * Created by Nico Sonner on 17.12.2021.
 */
public class ResourceLoader {

    /** Returns an ImageIcon, or null if the path was invalid. */
    static ImageIcon createImageIcon(Class c, String path) {
        java.net.URL imgURL = c.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
