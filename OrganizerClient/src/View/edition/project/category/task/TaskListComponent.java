package View.edition.project.category.task;

import model.project.Tag;
import model.project.Task;
import View.edition.TransparentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Classe encarregada de generar els components d'una llista de tasques
 */
public class TaskListComponent extends JPanel implements Transferable {

    private final static String TASK_FINISHED = "Task finished";
    private final static String TASK_NOT_FINISHED = "Task not finished";

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

    /**
     * Constructor a partir d'un objecte
     * @param object Objecte
     */
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
        add(jlTask, BorderLayout.PAGE_START);

        //Task state panel
        final TransparentPanel tpTaskState = new TransparentPanel();
        tpTaskState.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(tpTaskState, BorderLayout.CENTER);
        final JLabel jlTaskState = new JLabel();
        jlTaskState.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
        tpTaskState.add(jlTaskState);

        if(task.isFinished()) {
            jlTaskState.setText(TASK_FINISHED);
        } else {
            jlTaskState.setText(TASK_NOT_FINISHED);
        }

        //Tags panel
        final TransparentPanel tpTags = new TransparentPanel();
        tpTags.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(tpTags, BorderLayout.PAGE_END);

        //Tags
        for(int i = 0; i < task.getTagsSize(); i++) {

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