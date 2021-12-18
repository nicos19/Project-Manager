package project_management;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Sonner on 09.12.2021.
 * This is a implementation of a MutableComboBoxModel for the projectsComboBox field
 * of a ProjectManager instance.
 */
public class ProjectMutableModel implements MutableComboBoxModel {
    private List<Object> projects = new ArrayList<>();
    private int index = -1;  // index of selected item


    @Override
    public void addElement(Object item) {
        if (!projects.contains(item)) {
            projects.add(item);
        }
    }

    @Override
    public void removeElement(Object obj) {
        if (projects.contains(obj)) {
            projects.remove(obj);
        }
    }

    @Override
    public void insertElementAt(Object item, int index) {
        if (!projects.contains(item)) {
            projects.add(index, item);
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
            if (((Project)projects.get(i)).getName() == anItem) {
                index = i;
                System.out.println(index);
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
            return ((Project)projects.get(index)).getName();
        }
    }

    @Override
    public int getSize() {
        return projects.size();
    }

    @Override
    public Object getElementAt(int index) {
        if (projects.size() > index) {
            System.out.println(((Project)projects.get(index)).getName());
            return ((Project)projects.get(index)).getName();
        }
        else {
            // no element at index
            System.out.println("-");
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
