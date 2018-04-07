package View.project;

import Model.Tag;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaskListComponent extends JPanel {

    public TaskListComponent(Task task, Font font) {

        //Main container
        setLayout(new FlowLayout(FlowLayout.LEFT));

        //Tags
        ArrayList<Tag> totalTags = task.getTags();
        for(int i = 0; i < totalTags.size(); i++) {
            final JPanel jpTag = new JPanel();
            jpTag.setPreferredSize(new Dimension(16, 16));
            jpTag.setBackground(totalTags.get(i).getColor());
            add(jpTag);
        }

        //Task name
        final JLabel jlTask = new JLabel(task.getName());
        jlTask.setFont(font);
        add(jlTask);

    }

}