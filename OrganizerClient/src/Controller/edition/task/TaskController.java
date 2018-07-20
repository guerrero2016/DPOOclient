package Controller.edition.task;

import Controller.edition.EditionController;
import Controller.utils.color.ColorPreviewController;
import model.DataManager;
import model.project.Project;
import model.project.Tag;
import model.project.Task;
import View.utils.color.ColorChooserPanel;
import View.edition.task.TaskPanel;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe encarregada de controlar els ActionEvent d'un TaskPanel
 */
public class TaskController implements ActionListener {

    private final static String TASK_DELETE_MESSAGE = "Do you want to delete";
    private final static String TASK_DELETE_TITLE = "Task Delete";
    private final static String TAG_CREATION_TITLE = "Tag Color";

    private EditionController mainController;
    private TaskPanel view;

    /**
     * Constructor que requereix d'un controlador extern, la vista a controlar i la tasca origen
     * @param mainController Controlador extern
     * @param view Vista a controlar
     */
    public TaskController(EditionController mainController, TaskPanel view) {
        this.mainController = mainController;
        this.view = view;
    }

    /**
     * Metode encarregat de distingir les diferents possibles accions
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
     * Metode encarregat de tancar la tasca
     */
    private void closeTask() {
        Task task = DataManager.getSharedInstance().getEditingTask();
        view.setDescriptionEditable(false);
        view.setDescription(task.getDescription());
        view.setTaskNameEditable(false, task.getName());
        view.cleanNewTagName();
        mainController.showProjectContent();
        DataManager.getSharedInstance().setEditingTask(null);
    }

    /**
     * Metode encarregat de la modificacio del nom
     */
    private void taskNameManagement() {
        Task task = DataManager.getSharedInstance().getEditingTask();
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setTaskNameEditable(true, task.getName());
        } else {
            if(view.isTaskNameEditable()) {
                String name = view.getTaskName();
                if(name.isEmpty()) {
                    name = task.getName();
                }
                view.setTaskNameEditable(false, name);
                Task t =  new Task(task);
                t.setName(view.getTaskName());
                mainController.updateTask(t);
                mainController.setEditingState(false);
            } else {
                JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE,
                        EditionController.EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Metode encarregat d'eliminar la tasca
     */
    private void deleteTask() {
        Task task = DataManager.getSharedInstance().getEditingTask();
        int result = JOptionPane.showConfirmDialog(null,TASK_DELETE_MESSAGE + " '" +
                task.getName() + "'?", TASK_DELETE_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION) {
            mainController.deleteTask();
        }
    }

    /**
     * Metode encarregat de l'edició de la descripcio
     */
    private void descriptionManagement() {
        if(!mainController.isEditing()) {
            mainController.setEditingState(true);
            view.setDescriptionEditable(true);
        } else {
            Task task = DataManager.getSharedInstance().getEditingTask();
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
     * Metode encarregat d'afegir una etiqueta
     */
    private void addTag() {

        Project project = DataManager.getSharedInstance().getSelectedProject();
        Task task = DataManager.getSharedInstance().getEditingTask();

        if(!mainController.isEditing() && !view.getNewTagName().isEmpty()) {

            ColorChooserPanel colorChooserPanel = new ColorChooserPanel();
            ColorPreviewController colorPreviewController = new ColorPreviewController(colorChooserPanel);
            colorChooserPanel.getPalettePanel().registerActionController(colorPreviewController);
            int result = JOptionPane.showConfirmDialog(null, colorChooserPanel, TAG_CREATION_TITLE,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if(result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION &&
                    DataManager.getSharedInstance().getSelectedProject() != null &&
                    DataManager.getSharedInstance().getSelectedProject().equals(project)) {

                String name = view.getNewTagName();
                view.cleanNewTagName();
                Color color;

                if(colorPreviewController.getColor() == null) {
                    color = Color.RED;
                } else {
                    color = colorPreviewController.getColor();
                }

                mainController.sendTag(task, new Tag(name, color));

            }
        } else {
            JOptionPane.showMessageDialog(null, EditionController.EDITING_ON_MESSAGE, EditionController.
                    EDITING_ON_TITLE, JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * Metode encarregat d'assingar una tasca com a finalitzada
     */
    private void taskDoneManagement() {
        mainController.setTaskDoneInDB();
    }

    /**
     * Metode encarregat d'assignar una tasca com a no finalitzada
     */
    private void taskNotDoneManagement() {
        mainController.setTaskNotDoneInDB();
    }

}