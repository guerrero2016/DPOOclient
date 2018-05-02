package View.edition.project.category.task;

import ModelAEliminar.Tag;
import ModelAEliminar.Task;
import View.edition.TransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TaskListComponent extends JPanel implements Transferable {

    public static DataFlavor localObjectFlavor;
    static {
        try {
            localObjectFlavor = new DataFlavor (DataFlavor.javaJVMLocalObjectMimeType);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static DataFlavor[] supportedFlavors = {localObjectFlavor};
    private Task task;

    public TaskListComponent(Object object) {

        if(!(object instanceof Task)) {
            return;
        }

        task = (Task) object;

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
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(localObjectFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if(isDataFlavorSupported(flavor)) {
            return task;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

}