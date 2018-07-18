package View.edition.project.category.task;

import javax.swing.*;
import java.awt.*;

/**
 * Classe encarregada de fer un CellRenderer de tasques
 */
public class TaskListCellRenderer extends DefaultListCellRenderer {

    @SuppressWarnings("Duplicates")
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object object, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        TaskListComponent taskComponent = new TaskListComponent(object);
        taskComponent.setOpaque(true);

        if (isSelected) {
            taskComponent.setBackground(list.getSelectionBackground());
            taskComponent.setForeground(list.getSelectionForeground());
        } else {
            taskComponent.setBackground(list.getBackground());
            taskComponent.setForeground(list.getForeground());
        }

        return taskComponent;

    }

}