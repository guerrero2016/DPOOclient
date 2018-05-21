package Controller.edition.task;

import Controller.edition.EditionController;
import Controller.edition.color.ColorPreviewController;
import model.DataManager;
import model.project.Tag;
import model.project.Task;
import View.edition.color.ColorChooserPanel;
import View.edition.task.TaskPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de controlar els Action Event d'un TaskPanel
 */
public class TaskController implements ActionListener {

    private final static String TASK_DELETE_MESSAGE = "Do you want to delete";
    private final static String TASK_DELETE_TITLE = "Task Delete";
    private final static String TAG_CREATION_TITLE = "Tag Color";

    private EditionController mainController;
    private TaskPanel view;
    private Task task;

    /**
     * Constructor que requereix d'un controlador extern, la vista a controlar i la tasca origen
     * @param mainController Controlador extern
     * @param view Vista a controlar
     * @param task Tasca origen
     */
    public TaskController(EditionController mainController, TaskPanel view, Task task) {
        this.mainController = mainController;
        this.view = view;
        this.task = task;
        DataManager.getSharedInstance().setEditingTask(task);
    }

    /**
     * Mètode encarregat de distingir les diferents possibles accions
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_BACK)) {
            closeTask();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_EDIT_NAME)) {
            taskNameManagement();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TASK_DELETE)) {
            deleteTask();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_DESCRIPTION_EDITION)) {
            descriptionManagement();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_TAG_ADD)) {
            addTag();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_AFFIRMATIVE)) {
            taskDoneManagement();
        } else if(e.getActionCommand().equals(TaskPanel.ACTION_NEGATIVE)) {
            taskNotDoneManagement();
        }
    }

    /**
     * Mètode encarregat de tancar la tasca
     */
    private void closeTask() {
        view.setDescriptionEditable(false);
        view.setDescription(task.getDescription());
        view.setTaskNameEditable(false, task.getName());
        view.cleanNewTagName();
        mainController.showProjectContent();
        DataManager.getSharedInstance().setEditingTask(null);
    }

    /**
     * Mètode encarregat de la modificació del nom
     */
    private void taskNameManagement() {
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setTaskNameEditable(true, task.getName());
        } else {
            if(view.isTaskNameEditable()) {
                view.setTaskNameEditable(false, view.getTaskName());
                //Copiem la tasca en una variable auxiliar perquè sinó no es passa correctament.
                Task aux =  new Task(view.getTaskName(), task.getDescription(), task.getTags(), task.getUsers(),
                        task.getOrder());
                aux.setId(task.getID());
                task.setName(view.getTaskName());
                mainController.updateTask(aux);
                mainController.setEditingState(false);
            } else {
                JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE,
                        EditionController.EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Mètode encarregat d'eliminar la tasca
     */
    private void deleteTask() {
        int result = JOptionPane.showConfirmDialog(null,TASK_DELETE_MESSAGE + " '" +
                task.getName() + "'?", TASK_DELETE_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
            mainController.deleteTask();
        }
    }

    /**
     * Mètode encarregat de l'edició de la descripció
     */
    private void descriptionManagement() {
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setDescriptionEditable(true);
        } else {
            if(view.isDescriptionEditable()) {
                view.setDescriptionEditable(false);
                task.setDescription(view.getDescription());
                mainController.updateTask(task);
                mainController.setEditingState(false);
            } else {
                JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE,
                        EditionController.EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Mètode encarregat d'afegir una etiqueta
     */
    private void addTag() {

        if(!mainController.isEditing() && !view.getNewTagName().isEmpty()) {

            ColorChooserPanel colorChooserPanel = new ColorChooserPanel();
            ColorPreviewController colorPreviewController = new ColorPreviewController(colorChooserPanel);
            colorChooserPanel.getPalettePanel().registerActionController(colorPreviewController);
            int result = JOptionPane.showConfirmDialog(null, colorChooserPanel, TAG_CREATION_TITLE,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
                Tag tag;
                if(colorPreviewController.getColor() == null) {
                    tag = new Tag(view.getNewTagName(), Color.RED);
                } else {
                    tag = new Tag(view.getNewTagName(), colorPreviewController.getColor());
                }
                view.cleanNewTagName();
                mainController.sendTag(task, tag);
            }
        } else {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * Mètode encarregat d'assingar una tasca com a finalitzada
     */
    private void taskDoneManagement() {
        mainController.setTaskDoneInDB();
    }

    /**
     * Mètode encarregat d'assignar una tasca com a no finalitzada
     */
    private void taskNotDoneManagement() {
        mainController.setTaskNotDoneInDB();
    }

}