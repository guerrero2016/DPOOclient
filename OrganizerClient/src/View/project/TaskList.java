package View.project;

import Model.Task;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TaskList implements ListCellRenderer<Task> {

    private Font font;

    public static class TaskListComponent extends JPanel {

        private final static String IMG_PATH = "img/";
        private final static String UP_ICON_FILE = "up_icon.png";
        private final static String DOWN_ICON_FILE = "down_icon.png";

        public TaskListComponent(Task task, Font font) {

            //Main container
            setLayout(new FlowLayout(FlowLayout.LEFT));

            try {

                //Load icons
                Image mediumUpIcon = ImageIO.read(new File(IMG_PATH + UP_ICON_FILE)).
                        getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                Image mediumDownIcon = ImageIO.read(new File(IMG_PATH + DOWN_ICON_FILE)).
                        getScaledInstance(16, 16, Image.SCALE_SMOOTH);

                //Order buttons
                final JButton jbTaskUp = new JButton(new ImageIcon(mediumUpIcon));
                jbTaskUp.setBorder(BorderFactory.createEmptyBorder());
                add(jbTaskUp);

                final JButton jbTaskDown = new JButton(new ImageIcon(mediumDownIcon));
                jbTaskDown.setBorder(BorderFactory.createEmptyBorder());
                add(jbTaskDown);

            } catch(IOException e) {
                //Tasks can't be ordered
            }

            //Tags
            int totalTags = task.getTotalTags();
            for(int i = 0; i < totalTags; i++) {
                final JPanel jpTag = new JPanel();
                jpTag.setPreferredSize(new Dimension(16, 16));
                jpTag.setBackground(task.getTag(i).getColor());
                add(jpTag);
            }

            //Task name
            final JLabel jlTask = new JLabel(task.getName());
            jlTask.setFont(font);
            add(jlTask);

        }

    }

    public TaskList(Font font) {
        this.font = font;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task task, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

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
