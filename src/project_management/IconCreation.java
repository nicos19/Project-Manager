package project_management;

import javax.swing.*;

/**
 * Created by Nico Sonner on 22.12.2021.
 *
 * Class used to create/load icons.
 */
class IconCreation {

    /**
     * Creates a green plus icon.
     * @return the icon
     */
    static Icon createGreenPlus() {
        IconCreation iconCreator = new IconCreation();

        return ResourceLoader.createImageIcon(iconCreator.getClass(),
                "/resources/plus-icon-small-16.png");
    }

    /**
     * Creates a blue plus icon.
     * @return the icon
     */
    static Icon createBluePlus() {
        IconCreation iconCreator = new IconCreation();

        return ResourceLoader.createImageIcon(iconCreator.getClass(),
                "/resources/plus-icon-small-16-blue.png");
    }

    /**
     * Creates a purple plus icon.
     * @return the icon
     */
    static Icon createPurplePlus() {
        IconCreation iconCreator = new IconCreation();

        return ResourceLoader.createImageIcon(iconCreator.getClass(),
                "/resources/plus-icon-small-16-purple.png");
    }

    /**
     * Creates a green checkmark icon.
     * @return the icon
     */
    static Icon createGreenCheckmark() {
        IconCreation iconCreator = new IconCreation();

        return ResourceLoader.createImageIcon(iconCreator.getClass(),
                "/resources/Checkmark-icon-small.png");
    }
}
