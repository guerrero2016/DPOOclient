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

/**
 * Classe encarregada de controlar el drag and drop d'una llista de tasques
 */
public class TaskListController extends TransferHandler {

    private EditionController mainController;
    private Category category;
    private JList<Task> jlTasks;

    private int index;
    private boolean beforeIndex = false;

    /**
     * Constructor quie requereix d'un controlador extern, la categoria on pertany la llista i el component de la llista
     * @param mainController Controlador extern
     * @param category Categoria de la llista
     * @param jlTasks Llista de tasques (JList<Task>)
     */
    public TaskListController(EditionController mainController, Category category, JList<Task> jlTasks) {
        this.mainController = mainController;
        this.category = category;
        this.jlTasks = jlTasks;
    }

    /**
     * Mètode encarregat de retornar el tipus d'acció DnD
     * @param comp Component
     * @return Tipus d'acció
     */
    @Override
    public int getSourceActions(JComponent comp) {
        return MOVE;
    }

    /**
     * Mètode encarregat d'iniciar el DnD
     * @param comp Component a moure
     * @return Component que es mou
     */
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

    /**
     * Mètode llençat quan s'ha pogut començar un DnD exitosament
     * @param comp Component
     * @param trans Objecte que es mou
     * @param action Acció
     */
    @Override
    public void exportDone(JComponent comp, Transferable trans, int action) {
        if (action == MOVE) {
            if(beforeIndex) {
                ((DefaultListModel<Task>) jlTasks.getModel()).remove(index + 1);
            } else {
                ((DefaultListModel<Task>) jlTasks.getModel()).remove(index);
            }
        }
        mainController.swapTask(category);
    }

    /**
     * Mètode que indica si l'objecte que es mou es pot colocar on està el cursor
     * @param support Objecte que es mou
     * @return Si es pot colocar
     */
    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDrop() && support.isDataFlavorSupported(TaskListComponent.localObjectFlavor);
    }

    /**
     * Mètode que coloca l'objecte que s'ha deixat anar
     * @param support Objecte a colocar
     * @return Si s'ha pogut colocar
     */
    @Override
    public boolean importData(TransferSupport support) {
        try {

            Task transferredTask = (Task) support.getTransferable().getTransferData(TaskListComponent.localObjectFlavor);
            JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
            DefaultListModel<Task> model = (DefaultListModel<Task>) jlTasks.getModel();

            if(model.contains(transferredTask)) {

                model.add(dl.getIndex(), transferredTask);
                beforeIndex = dl.getIndex() < index;
                DefaultListModel<Task> tasks = (DefaultListModel<Task>) jlTasks.getModel();
                for (int i = 0; i < tasks.getSize(); i++) {
                    Task task = tasks.getElementAt(i);
                    task.setOrder(i - 1);
                }
                return true;
            }

        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}