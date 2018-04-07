package View.project;

import Model.Task;

import javax.swing.*;
import java.awt.*;

public class TaskList implements ListCellRenderer<Task> {

    private Font font;

    public TaskList(Font font) {
        this.font = font;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task task, int index, boolean isSelected, boolean cellHasFocus) {

        TaskListComponent taskComponent = new TaskListComponent(task, font);
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
