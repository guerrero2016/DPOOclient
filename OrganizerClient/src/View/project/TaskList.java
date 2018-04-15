package View.project;

import Model.Tag;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TaskList implements ListCellRenderer<Task> {

    public static class TaskListComponent extends JPanel implements Transferable {

        public final static DataFlavor LIST_ITEM_DATA_FLAVOR = new DataFlavor(Task.class, "java/Task");

        public TaskListComponent(Task task) {

            //Main container
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            //Task name
            final JLabel jlTask = new JLabel(task.getName());
            jlTask.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
            add(jlTask, BorderLayout.CENTER);

            //Tags panel
            final TransparentPanel tpTags = new TransparentPanel();
            tpTags.setLayout(new FlowLayout(FlowLayout.LEFT));
            add(tpTags, BorderLayout.PAGE_END);

            //Tags
            for(int i = 0; i < task.getTotalTags(); i++) {

                Tag tag = task.getTag(i);

                final JPanel jpTag = new JPanel(new FlowLayout(FlowLayout.CENTER));
                jpTag.setBackground(tag.getColor());
                tpTags.add(jpTag);

                final JLabel jlTagName = new JLabel(tag.getName());
                jlTagName.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
                jpTag.add(jlTagName);

            }

        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[0];
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return false;
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            return null;
        }

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task task, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        TaskListComponent taskComponent = new TaskListComponent(task);
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