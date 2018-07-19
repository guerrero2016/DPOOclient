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
 * Classe encarregada de controlar el drag and drop (DnD) d'una llista de tasques
 */
public class TaskListController extends TransferHandler {

    private EditionController mainController;
    private Category category;
    private JList<Task> jlTasks;

    private int oldIndex;
    private int newIndex;
    private boolean beforeIndex = false;

    /**
     * Constructor que requereix d'un controlador extern, la categoria on pertany la llista i el component de la llista
     * @param mainController Controlador extern
     * @param category Categoria de la llista
     * @param jlTasks Llista de tasques
     */
    public TaskListController(EditionController mainController, Category category, JList<Task> jlTasks) {
        this.mainController = mainController;
        this.category = category;
        this.jlTasks = jlTasks;
    }

    /**
     * Metode encarregat de retornar el tipus d'accio DnD
     * @param comp Component
     * @return Tipus d'accio
     */
    @Override
    public int getSourceActions(JComponent comp) {
        return MOVE;
    }

    /**
     * Metode encarregat d'iniciar el DnD
     * @param comp Component a moure
     * @return Component que es mou
     */
    @Override
    public Transferable createTransferable(JComponent comp) {

        oldIndex = jlTasks.getSelectedIndex();
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
     * Metode llencat quan s'ha pogut comencar un DnD exitosament
     * @param comp Component
     * @param trans Objecte que es mou
     * @param action Accio
     */
    @Override
    public void exportDone(JComponent comp, Transferable trans, int action) {
        if(newIndex > oldIndex) {
            newIndex--;
        }
        if(action == MOVE && oldIndex != newIndex) {
            mainController.swapTask(category, oldIndex, newIndex);
        }
    }

    /**
     * Metode que indica si l'objecte que es mou es pot colocar on esta el cursor
     * @param support Objecte que es mou
     * @return Si es pot colocar
     */
    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDrop() && support.isDataFlavorSupported(TaskListComponent.localObjectFlavor);
    }

    /**
     * Metode que coloca l'objecte que s'ha deixat anar
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
                newIndex = dl.getIndex();
                return true;
            }

        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}