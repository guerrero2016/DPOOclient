package Controller.edition.project.category.task;

import Controller.edition.EditionController;
import model.project.Category;
import model.project.Task;
import View.edition.project.category.task.TaskListComponent;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Objects;

public class TaskListController extends TransferHandler {

    private EditionController mainController;
    private Category category;
    private JList<Task> jlTasks;

    private int index;
    private boolean beforeIndex = false;

    public TaskListController(EditionController mainController, model.project.Category category, JList<Task> jlTasks) {
        this.mainController = mainController;
        this.category = category;
        this.jlTasks = jlTasks;
    }

    @Override
    public int getSourceActions(JComponent comp) {
        return MOVE;
    }

    @Override
    public Transferable createTransferable(JComponent comp) {

        index = jlTasks.getSelectedIndex();
        Task taskTransfer = jlTasks.getSelectedValue();

        return new Transferable() {

            @Override public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[] {TaskListComponent.localObjectFlavor};
            }

            @Override public boolean isDataFlavorSupported(DataFlavor flavor) {
                return Objects.equals(TaskListComponent.localObjectFlavor, flavor);
            }

            @Override public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
                if (isDataFlavorSupported(flavor)) {
                    return taskTransfer;
                } else {
                    throw new UnsupportedFlavorException(flavor);
                }
            }

        };

    }

    @Override
    public void exportDone(JComponent comp, Transferable trans, int action) {
        if (action == MOVE) {
            if(beforeIndex) {
                ((DefaultListModel<Task>) jlTasks.getModel()).remove(index + 1);
            } else {
                ((DefaultListModel<Task>) jlTasks.getModel()).remove(index);
            }
        }
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDrop() && support.isDataFlavorSupported(TaskListComponent.localObjectFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {

        try {

            Task transferredTask = (Task) support.getTransferable().getTransferData(TaskListComponent.localObjectFlavor);
            JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
            ((DefaultListModel<Task>) jlTasks.getModel()).add(dl.getIndex(), transferredTask);
            beforeIndex = dl.getIndex() < index ? true : false;
            DefaultListModel<Task> tasks = (DefaultListModel<Task>) jlTasks.getModel();

            for(int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getElementAt(i);
                task.setOrder(i);
            }

            return true;

        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }

        mainController.updatedCategory(category);

        return false;

    }

}