package project_management;

import javax.swing.*;

/**
 * Created by Nico Sonner on 17.12.2021.
 *
 * A class with methods to load resources such as images to use as icons etc.
 */
class ResourceLoader {

    /**
     * Creates an ImageIcon with resource at given path if possible.
     * @param c any class (to access c.getResource()
     * @param path the path where the resource is
     * @return an ImageIcon object or null if the path was invalid
     */
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
