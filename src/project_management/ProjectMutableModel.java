package project_management;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 09.12.2021.
 *
 * This is a implementation of a MutableComboBoxModel for the projectsComboBox field
 * of a ProjectManager instance.
 */
public class ProjectMutableModel implements MutableComboBoxModel {
    private List<String> projects = new ArrayList<>();
    private int index = -1;  // index of selected item


    @Override
    public void addElement(Object item) {
        if (!projects.contains((String)item)) {
            projects.add((String)item);
        }
    }

    @Override
    public void removeElement(Object obj) {
        if (projects.contains((String)obj)) {
            projects.remove((String)obj);
        }
    }

    @Override
    public void insertElementAt(Object item, int index) {
        if (!projects.contains((String)item)) {
            projects.add(index, (String)item);
        }
    }

    @Override
    public void removeElementAt(int index) {
        if (projects.size() > index) {
            projects.remove(index);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        for (int i = 0; i < projects.size(); i++) {
            if ((projects.get(i)).equals((String)anItem)) {
                index = i;
                break;
            }
        }
    }

    @Override
    public Object getSelectedItem() {
        if (index == -1) {
            // no item selected
            return "";
        }
        else {
            return projects.get(index);
        }
    }

    @Override
    public int getSize() {
        return projects.size();
    }

    @Override
    public Object getElementAt(int index) {
        if (projects.size() > index) {
            return projects.get(index);
        }
        else {
            // no element at index
            return "";
        }
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
