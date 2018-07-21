package Controller.edition;

import Controller.MainViewController;
import Controller.utils.document.DocumentController;
import Controller.edition.project.ProjectActionController;
import Controller.edition.project.category.CategoryActionController;
import Controller.edition.project.category.CategoryMouseController;
import Controller.edition.project.category.task.TaskListController;
import Controller.edition.project.user.ProjectAddUserController;
import Controller.edition.project.user.ProjectRemoveUserController;
import Controller.edition.task.TaskController;
import Controller.edition.task.tag.TagController;
import Controller.edition.task.user.TaskAddUserController;
import Controller.edition.task.user.TaskRemoveUserController;
import Network.Communicators.*;
import model.DataManager;
import model.ServerObjectType;
import model.project.Category;
import model.project.Project;
import model.project.Tag;
import model.project.Task;
import model.user.User;
import View.edition.EditionPanel;
import View.edition.project.category.CategoryPanel;
import View.edition.project.ProjectPanel;
import View.edition.task.TaskPanel;
import View.edition.task.tag.TagPanel;
import View.edition.user.UserPanel;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encarregada de controlar tot allo relacionat amb un projecte i communicar els canvis a un altre controlador
 */
public class EditionController {

    public final static String EDITING_ON_MESSAGE = "You should finish editing before doing something else";
    public final static String EDITING_ON_TITLE = "Information";

    private final static String PROJECT_USERS_TITLE = "Project Users";
    private final static String TASK_USERS_TITLE = "Task Users";

    private EditionPanel editionPanel;
    private ProjectPanel projectPanel;
    private UserPanel projectUserPanel;
    private TaskPanel taskPanel;
    private UserPanel taskUserPanel;

    private boolean isEditing;

    private MainViewController mainController;
    private DataManager dataManager;

    /**
     * Constructor que deixa preparada la vista a espera d'inserir contingut
     * @param editionPanel Panell a controlar
     */
    public EditionController(EditionPanel editionPanel) {

        //Link variables
        this.editionPanel = editionPanel;
        projectPanel = editionPanel.getProjectPanel();
        projectUserPanel = editionPanel.getProjectUserPanel();
        taskPanel = editionPanel.getTaskPanel();
        taskUserPanel = editionPanel.getTaskUserPanel();
        dataManager = DataManager.getSharedInstance();

        //Config project panel
        projectPanel.registerDocumentController(new DocumentController(projectPanel));

        //Config project users panel
        projectUserPanel.setTitle(PROJECT_USERS_TITLE);
        projectUserPanel.registerDocumentListener(new DocumentController(projectUserPanel));

        //Config task panel
        taskPanel.registerDocumentController(new DocumentController(taskPanel));

        //Config task users panel
        taskUserPanel.setTitle(TASK_USERS_TITLE);
        taskUserPanel.registerDocumentListener(new DocumentController(taskUserPanel));
    }

    /**
     * Afegeix els communicators pertinents per a la comunicacio quan s'edita el projecte
     */
    public void addCommunicators() {
        mainController.addCommunicator(new CategoryDeleteCommunicator(), ServerObjectType.DELETE_CATEGORY);
        mainController.addCommunicator(new CategorySetCommunicator(), ServerObjectType.SET_CATEGORY);
        mainController.addCommunicator(new CategorySwapCommunicator(), ServerObjectType.SWAP_CATEGORY);
        mainController.addCommunicator(new TaskSetCommunicator(), ServerObjectType.SET_TASK);
        mainController.addCommunicator(new TaskDeletedCommunicator(), ServerObjectType.DELETE_TASK);
        mainController.addCommunicator(new TagSetCommunicator(), ServerObjectType.SET_TAG);
    }

    /**
     * Elimina els communicators que nomes serveixen per a l'edicio de projectes
     */
    public void removeCommunicators () {
        mainController.removeCommunicator(ServerObjectType.DELETE_CATEGORY);
        mainController.removeCommunicator(ServerObjectType.SET_CATEGORY);
        mainController.removeCommunicator(ServerObjectType.SWAP_CATEGORY);
        mainController.removeCommunicator(ServerObjectType.SET_TASK);
        mainController.removeCommunicator(ServerObjectType.DELETE_TASK);
        mainController.removeCommunicator(ServerObjectType.SET_TAG);
    }

    /**
     * Getter del controlador que gestiona EditionController
     * @return Controlador que gestiona aquest controlador
     */
    public MainViewController getMainController() {
        return mainController;
    }

    /**
     * Setter del controlador que gestiona EditionController
     * @param mainController Controlador que gestionara aquest controlador
     */
    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
    }

    /**
     * Metode encarregat d'afegir una categoria amb els corresponents canvis
     * @param category Categoria a afegir
     */
    public void addCategory(Category category) {
        Project project = dataManager.getSelectedProject();
        project.addCategory(category);
        projectPanel.cleanNewCategoryName();
        projectPanel.addCategoryToView(category);
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoriesSize() - 1);
        categoryPanel.registerActionController(new CategoryActionController(this, categoryPanel, category.getId()));
        categoryPanel.registerMouseController(new CategoryMouseController(this, category.getId()));
        categoryPanel.registerDocumentController(new DocumentController(categoryPanel));
        categoryPanel.resetDnDController();
        categoryPanel.registerDnDController(new TaskListController(this, category.getId(),
                categoryPanel.getListComponent()));
    }

    /**
     * Metode encarregat d'afegir una tasca a una determinada categoria
     * @param categoryIndex Index de la categoria
     * @param task Tasca a afegir
     */
    public void addTask(int categoryIndex, Task task) {
        Project project = dataManager.getSelectedProject();
        Category targetCategory = project.getCategory(categoryIndex);
        targetCategory.setTask(task);
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(categoryIndex);
        categoryPanel.cleanNewTaskName();
        categoryPanel.addNewTask(task);
    }

    /**
     * Metode encarregat d'afegir el contingut d'un projecte a la vista
     * @param project Projecte a mostrar
     * @param user Usuari del client
     */
    public void loadProject(Project project, User user) {

        //Default config
        projectPanel.cleanCategories();
        projectUserPanel.cleanUserList();
        dataManager.setSelectedProject(project);
        dataManager.setEditingCategory(null);
        dataManager.setEditingTask(null);

        //Config project content
        projectPanel.setProjectOwner(project.isOwner());
        editionPanel.setBackgroundImage(project.getBackground());
        projectPanel.setProjectName(project.getName());
        projectPanel.cleanCategories();

        for(int i = 0; i < project.getCategoriesSize(); i++) {
            projectPanel.addCategoryToView(project.getCategory(i));
            CategoryPanel categoryPanel = projectPanel.getCategoryPanel(i);
            categoryPanel.resetActionController();
            categoryPanel.registerActionController(new CategoryActionController(this, categoryPanel,
                    project.getCategory(i).getId()));
            categoryPanel.resetMouseController();
            categoryPanel.registerMouseController(new CategoryMouseController(this, project.
                    getCategory(i).getId()));
            categoryPanel.registerDocumentController(new DocumentController(categoryPanel));
            categoryPanel.resetDnDController();
            categoryPanel.registerDnDController(new TaskListController(this, project.getCategory(i).getId(),
                    categoryPanel.getListComponent()));
        }

        //Config project controllers
        projectPanel.resetActionController();
        projectPanel.registerActionController(new ProjectActionController(this, projectPanel));

        //Config users panel
        projectUserPanel.setUserList(project.getOtherUsers(user));
        projectUserPanel.resetActionController();
        projectUserPanel.registerActionController(new ProjectAddUserController());
        projectUserPanel.resetMouseController();

        if(project.isOwner()) {
            projectUserPanel.registerMouseController(new ProjectRemoveUserController(this));
        }

        projectUserPanel.registerDocumentListener(new DocumentController(projectUserPanel));

        //Configure user permissions
        projectUserPanel.setEditionState(project.isOwner());

    }

    /**
     * Metode encarregat de mostrar la vista d'un projecte previament carregat
     */
    public void showProjectContent() {
        isEditing = false;
        dataManager.setEditingCategory(null);
        dataManager.setEditingTask(null);
        editionPanel.showProjectPanel();
    }

    /**
     * Mètode encarregat d'afegir el contingut d'una tasca a la vista
     * @param categoryId Id de la categoria on pertany la tasca
     * @param taskId Id de la tasca a mostrar
     */
    public void setTaskContent(String categoryId, String taskId) {

        Category category = dataManager.getSelectedProject().getCategoryWithId(categoryId);
        Task task = category.getTaskWithId(taskId);

        //Default config
        dataManager.setEditingCategory(category);
        dataManager.setEditingTask(task);

        //Config task content
        taskPanel.setTaskFinished(task.isFinished());
        taskPanel.setDescription(task.getDescription());
        taskPanel.cleanTagsList();
        taskPanel.setTagsList(task.getTags());

        //Link controllers
        taskPanel.resetActionController();
        TaskController taskController = new TaskController(this, editionPanel.getTaskPanel());
        taskPanel.registerActionController(taskController);

        for(int i = 0; i < task.getTagsSize(); i++) {
            TagPanel tagPanel = editionPanel.getTaskPanel().getTagPanel(i);
            tagPanel.resetActionController();
            tagPanel.registerActionController(new TagController(this, task.getTag(i)));
        }

        //Config task users controllers
        taskUserPanel.cleanNewUser();
        taskUserPanel.setUserList(task.getUsers());
        taskUserPanel.resetActionController();
        taskUserPanel.resetMouseController();
        taskUserPanel.registerActionController(new TaskAddUserController(this, taskUserPanel));
        taskUserPanel.registerMouseController(new TaskRemoveUserController(this));

        //Reset editing state
        taskPanel.setDescriptionEditable(false);
        taskPanel.setTaskNameEditable(false, task.getName());

    }

    /**
     * Metode encarregat de mostrar la vista d'una tasca previament carregada
     */
    public void showTaskContent() {
        isEditing = false;
        editionPanel.showTaskPanel();
    }

    /**
     * Metode que indica si algun camp d'edicio esta encara habilitat
     * @return Indica si algun camp està sent editat
     */
    public boolean isEditing() {
        return isEditing;
    }

    /**
     * Metode que permet canviar l'estat d'edicio del projecte
     * @param enableState Estat d'edicio
     */
    public void setEditingState(boolean enableState) {
        isEditing = enableState;
    }

    /**
     * Metode encarregat d'actualitzar el contingut d'un projecte al servidor
     * @param p Projecte a actualitzar
     */
    public void updateProject(Project p) {
        if(mainController != null) {
            try {
                mainController.sendToServer(ServerObjectType.SET_PROJECT, p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metode encarregat d'eliminar un usuari del projecte al servidor
     * @param user Usuari a a eliminar
     */
    public void deleteUser(User user) {
        try {
            mainController.sendToServer(ServerObjectType.DELETE_USER, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat d'actualitzar una tasca al servidor
     * @param task Tasca a actualitzar
     */
    public void updateTask(Task task) {
        if(mainController != null) {
            try {
                mainController.sendToServer(ServerObjectType.SET_TASK, dataManager.getEditingCategory().getId());
                mainController.sendToServer(null, task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metode encarregat d'actualitzar una categoria al servidor
     * @param category Categoria a actualitzar
     */
    public void updateCategory(Category category) {
        if(mainController != null) {
            try {
                if(category.getOrder() == -1) { //New category case
                    projectPanel.cleanNewCategoryName();
                    category.setOrder(dataManager.getSelectedProject().getCategoriesSize());
                }
                mainController.sendToServer(ServerObjectType.SET_CATEGORY, category);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metode encarregat d'actualitzar el projecte de la vista
     * @param p Projecte a actualitzar
     */
    public void updateProjectView(Project p) {
        dataManager.setSelectedProject(p);
        editionPanel.getProjectPanel().setProjectName(p.getName());
        editionPanel.setBackgroundImage(p.getBackground());
        for(int i = 0; i < p.getCategoriesSize(); i++) {
            CategoryPanel categoryPanel = projectPanel.getCategoryPanel(i);
            categoryPanel.updateTasksList(p.getCategory(i).getTasks());
        }
    }

    /**
     * Metode encarregat de crear una tasca al servidor
     * @param task Tasca a afegir
     * @param category Categoria on pertany la tasca
     */
    public void createTask (Task task, Category category) {
        if(task.getOrder() == -1) {
            task.setOrder(category.getTasksSize());
        }
        try {
            mainController.sendToServer(ServerObjectType.SET_TASK, category.getId());
            mainController.sendToServer(null, task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat d'enviar una etiqueta al servidor
     * @param task Tasca on pertany l'etiqueta
     * @param tag Etiqueta a enviar
     */
    public void sendTag(Task task, Tag tag) {
        try {
            mainController.sendToServer(ServerObjectType.SET_TAG, task.getId());
            mainController.sendToServer(null, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat d'actualitzar una tasca a la vista
     * @param categoryId Id de la categoria de la tasca
     * @param task Tasca a actualitzar
     */
    public void updateTaskInView(String categoryId, Task task) {

        Project project = dataManager.getSelectedProject();
        Task editingTask = dataManager.getEditingTask();

        if(editingTask != null && editingTask.getId().equals(task.getId())) {
            taskPanel.setTaskName(task.getName());
            taskPanel.setDescription(task.getDescription());
        }

        Category targetCategory = project.getCategoryWithId(categoryId);
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoryIndex(
                targetCategory));
        categoryPanel.updateTask(targetCategory.getTaskIndex(task), task);

    }

    /**
     * Metode encarregat d'actualitzar una categoria a la vista
     * @param category Categoria a actualitzar
     */
    public void updateCategoryInView(Category category) {
        projectPanel.getCategoryPanel(category.getOrder()).setCategoryName(category.getName());
    }

    /**
     * Metode encarregat d'eliminar una tasca al servidor
     */
    public void deleteTask() {
        if(mainController != null) {
            try {
                mainController.sendToServer(ServerObjectType.DELETE_TASK, dataManager.getEditingTask());
                mainController.sendToServer(null, dataManager.getEditingCategory().getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metode encarregat d'eliminar una tasca de la vista
     * @param targetTask Tasca a eliminar
     * @param categoryId Id de la categoria
     */
    public void deleteTaskInProject(Task targetTask, String categoryId) {

        Project project = dataManager.getSelectedProject();
        Category targetCategory = project.getCategoryWithId(categoryId);
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory));
        categoryPanel.removeTask(targetTask);
        Task editingTask = dataManager.getEditingTask();

        if(editingTask != null && editingTask.getId().equals(targetTask.getId())) {
            showProjectContent();
        }

        targetCategory.deleteTask(targetTask);

    }

    /**
     * Metode encarregat d'eliminar una categoria de la vista
     * @param i Index de la categoria
     */
    public void deleteCategoryInView(int i) {
        projectPanel.removeCategory(i);
    }

    /**
     * MEtode encarregat d'eliminar una categoria a partir d'una Id
     * @param categoryId Id
     */
    public void deleteCategory(String categoryId) {
        Project project = dataManager.getSelectedProject();
        Category category = project.getCategoryWithId(categoryId);
        int index = project.getCategoryIndex(category);
        if(index >= 0 && index < project.getCategoriesSize()) {
            if(mainController != null) {
                try {
                    mainController.sendToServer(ServerObjectType.DELETE_CATEGORY, category);
                    mainController.sendToServer(null, project.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Metode encarregat d'obtenir un usuari del projecte si existeix
     * @param userName Nom de l'usuari a buscar
     * @return Usuari del projecte (null si no existeix)
     */
    public User getProjectUser(String userName) {
        Project project = dataManager.getSelectedProject();
        for(int i = 0; i < project.getUsers().size(); i++) {
            if(userName.equals(project.getUser(i).getUserName())) {
                return project.getUser(i);
            }
        }
        return null;
    }

    /**
     * Metode encarregat d'agregar un usuari a un projecte
     * @param user Usuari
     */
    public void userJoinedProject(User user) {
        projectUserPanel.addUser(user);
    }

    /**
     * Metode encarregat d'obtenir l'index d'una categoria
     * @param category Categoria a buscar
     * @return Index de la categoria al projecte
     */
    public int getCategoryIndex(Category category) {
        Project project = dataManager.getSelectedProject();
        return project.getCategoryIndex(category);
    }

    /**
     * Metode encarregat de canviar d'ordre 2 categories
     * @param firstCategoryIndex Index del projecte de la primera categoria
     * @param secondCategoryIndex Index del projecte de la segona categoria
     */
    public void swapCategories(int firstCategoryIndex, int secondCategoryIndex) {
        try {
            Category c1 = DataManager.getSharedInstance().getSelectedProject().getCategories().get(firstCategoryIndex);
            Category c2 = DataManager.getSharedInstance().getSelectedProject().getCategories().get(secondCategoryIndex);
            Category c1_aux = new Category(c1.getId(), c1.getName(), c1.getOrder(), c1.getTasks());
            Category c2_aux = new Category(c2.getId(), c2.getName(), c2.getOrder(), c2.getTasks());
            mainController.sendToServer(ServerObjectType.SWAP_CATEGORY,c1_aux);
            mainController.sendToServer(null,c2_aux);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat de canviar d'ordre 2 categories a la vista
     * @param firstCategoryIndex Index del projecte de la primera categoria
     * @param secondCategoryIndex Index del projecte de la segona categoria
     */
    public void swapCategoriesInView(int firstCategoryIndex, int secondCategoryIndex) {
        Project project = dataManager.getSelectedProject();
        project.swapCategories(firstCategoryIndex, secondCategoryIndex);
        projectPanel.swapCategories(firstCategoryIndex, secondCategoryIndex);
    }

    /**
     * Metode encarregat de mostrar la vista de seleccio de projectes
     */
    public void showProjectSelection() {
        if (mainController != null) {
            try {
                dataManager.setEditingTask(null);
                dataManager.setEditingCategory(null);
                dataManager.setSelectedProject(null);
                mainController.sendToServer(ServerObjectType.EXIT_PROJECT, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.exit(0);
        }
    }

    /**
     * Metode encarregat d'eliminar un projecte al servidor
     */
    public void deleteProject() {
        if(mainController != null) {
            try {
                mainController.sendToServer(ServerObjectType.DELETE_PROJECT,
                        DataManager.getSharedInstance().getSelectedProject().getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.exit(0);
        }
    }

    /**
     * Metode encarregat d'afegir un usuari d'una tasca  al servidor
     * @param user Usuari a afegir
     */
    public void addMemberInDB(User user) {
        Category category = dataManager.getEditingCategory();
        Task task = dataManager.getEditingTask();
        if(mainController != null) {
            mainController.addMemberInDB(category.getId(), task.getId(), user);
        } else {
            task.addUser(user);
            taskUserPanel.addUser(user);
        }
    }

    /**
     * Metode encarregat d'afegir un usuari d'una tasca al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca
     * @param user Usuari de la tasca a afegir
     */
    public void addMemberInProject(String categoryId, String taskId, User user) {

        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getId().equals(taskId)) {
            if(!targetTask.getUsers().contains(user)) {
                taskUserPanel.addUser(user);
            }
        }

        targetTask.addUser(user);

    }

    /**
     * Metode encarregat d'eliminar un usuari d'una tasca al servidor
     * @param user Usuari
     */
    public void removeMemberInDB(User user) {
        Category category = dataManager.getEditingCategory();
        Task task = dataManager.getEditingTask();
        if(mainController != null) {
            mainController.removeMemberInDB(category.getId(), task.getId(), user);
        } else {
            taskUserPanel.removeUser(user);
            task.removeUser(user);
        }
    }

    /**
     * Metode encarregat d'eliminar un usuari d'una tasca al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca
     * @param user Usuari de la tasca a eliminar
     */
    public void removeMemberInProject(String categoryId, String taskId, User user) {

        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getId().equals(taskId)) {

            TaskRemoveUserController controller = (TaskRemoveUserController) taskUserPanel.getMouseListener();

            if(controller.isRemovingUser(user)) {
                controller.closeDialog();
            }

            taskUserPanel.removeUser(user);

        }

        targetTask.removeUser(user);

    }

    /**
     * Metode encarregat d'eliminar una etiqueta al servidor
     * @param tag Etiqueta a eliminar
     */
    public void removeTagInDB(Tag tag) {
        Category category = dataManager.getEditingCategory();
        Task task = dataManager.getEditingTask();
        if(mainController != null) {
            mainController.removeTagInDB(category.getId(), task.getId(), tag);
        } else {
            taskPanel.removeTag(task.getTagIndex(tag));
            task.removeTag(tag);
        }
    }

    /**
     * Metode encarregat d'eliminar una etiqueta al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca de l'etiqueta
     * @param tag Etiqueta a eliminar
     */
    public void removeTagInProject(String categoryId, String taskId, Tag tag) {

        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getId().equals(taskId)) {

            TagController controller = (TagController) taskPanel.getTagPanel(targetTask.getTagIndex(tag)).getActionListener();

            if(controller.isRemovingTag(tag)) {
                controller.closeDialog();
            }

            taskPanel.removeTag(targetTask.getTagIndex(tag));

        }

        targetTask.removeTag(tag);
        projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory)).
                updateTask(targetCategory.getTaskIndex(targetTask), targetTask);

    }

    /**
     * Metode encarregat d'actualitzar una etiqueta al servidor
     * @param tag Etiqueta a actualitzar
     */
    public void editTagInDB(Tag tag) {
        mainController.editTagInDB(dataManager.getEditingTask().getId(), tag);
    }

    /**
     * Metode encarregat d'editar una etiqueta al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca de l'etiqueta
     * @param tag Etiqueta a actualitzar
     */
    public void editTagInProject(String categoryId, String taskId, Tag tag) {

        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);
        Tag targetTag = targetTask.getTagWithId(tag.getId());

        if(task != null && task.getId().equals(taskId)) {
            TagPanel tagPanel = taskPanel.getTagPanel(targetTask.getTagIndex(tag));
            tagPanel.setTagName(tag.getName());
            tagPanel.setTagColor(tag.getColor());
            TagController controller = (TagController) taskPanel.getTagPanel(task.getTagIndex(tag)).getActionListener();
            if(!controller.isEditingTag(tag)) {
                tagPanel.revalidate();
                tagPanel.repaint();
            }
        }
        targetTag.setName(tag.getName());
        targetTag.setColor(tag.getColor());
        projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory)).
                updateTask(targetCategory.getTaskIndex(targetTask), targetTask);
    }

    /**
     * Metode encarregat d'afegir una etiqueta al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca de l'etiqueta
     * @param tag Etiqueta a afegir
     */
    public void addTagInProject(String categoryId, String taskId, Tag tag) {

        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);
        targetTask.addTag(tag);

        if(task != null && task.getId().equals(taskId)) {
            taskPanel.addTag(tag);
            TagPanel tagPanel = taskPanel.getTagPanel(targetTask.getTagIndex(tag));
            tagPanel.registerActionController(new TagController(this, tag));
        }

        projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory)).updateTask(targetCategory.
                getTaskIndex(targetTask), targetTask);

    }

    /**
     * Metode encarregat d'establir una tasca com a finalitzada al servidor
     */
    public void setTaskDoneInDB() {
        if(mainController != null) {
            Category category = dataManager.getEditingCategory();
            Task task = dataManager.getEditingTask();
            mainController.setTaskDoneInDB(category.getId(), task.getId());
        }
    }

    /**
     * Metode encarregat d'establir una tasca com a finalitzada al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca finalitzada
     */
    public void setTaskDoneInProject(String categoryId, String taskId) {

        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getId().equals(taskId)) {
            taskPanel.setTaskFinished(true);
        }

        targetTask.setFinished(true);
        projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory)).updateTask(targetCategory.
                getTaskIndex(targetTask), targetTask);

    }

    /**
     * Metode encarregat d'establir una tasca com a no finalitzada al servidor
     */
    public void setTaskNotDoneInDB() {
        if(mainController != null) {
            Category category = dataManager.getEditingCategory();
            Task task = dataManager.getEditingTask();
            mainController.setTaskNotDoneInDB(category.getId(), task.getId());
        }
    }

    /**
     * Metode encarregat d'establir una tasca com a no finalitzada al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca no finalitzada
     */
    public void setTaskNotDoneInProject(String categoryId, String taskId) {

        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getId().equals(taskId)) {
            taskPanel.setTaskFinished(false);
        }

        targetTask.setFinished(false);
        projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory)).updateTask(targetCategory.
                getTaskIndex(targetTask), targetTask);

    }

    /**
     * Metode encarregat de canviar l'ordre de les tasques al servidor
     * @param category Categoria on pertanyen les tasques
     * @param oldIndex Antic index de la tasca
     * @param newIndex Nou undex de la tasca
     */
    public void swapTask(Category category, int oldIndex, int newIndex) {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            for(int i = 0; i < category.getTasksSize(); i++) {
                Task task;
                if(i >= oldIndex && i < newIndex) {
                    task = new Task(category.getTask(i + 1));
                } else if(i > newIndex && i <= oldIndex) {
                    task = new Task(category.getTask(i - 1));
                } else if(i == newIndex) {
                    task = new Task(category.getTask(oldIndex));
                } else {    //Interval without change
                    task = new Task(category.getTask(i));
                }
                task.setOrder(i);
                tasks.add(task);
            }

            mainController.sendToServer(ServerObjectType.SWAP_TASK, tasks);
            mainController.sendToServer(null, category.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode encarregat de canviar l'ordre de les tasques al projecte
     * @param categoryID Id de la categoria de les tasques
     * @param tasks llista de tasques de la categoria
     */
    public void swapTasksInView(String categoryID, ArrayList<Task> tasks) {
        Project project = dataManager.getSelectedProject();
        CategoryPanel categoryPanel =
                projectPanel.getCategoryPanel(project.getCategoryIndex(project.getCategoryWithId(categoryID)));
        categoryPanel.updateTasksList(tasks);
    }

    /**
     * Metode encarregat d'eliminar un usuari d'un projecte
     * @param deletedUser Usuari
     */
    public void userLeftProject(User deletedUser) {

        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        projectUserPanel.removeUser(deletedUser);
        project.deleteUser(project.getUserIndex(deletedUser));

        if(task != null) {
            if(task.getUserIndex(deletedUser) != Task.INVALID_INDEX) {
                taskUserPanel.removeUser(deletedUser);
            }
        }

        for(int i = 0; i < project.getCategoriesSize(); i++) {
            for(int j = 0; j < project.getCategory(i).getTasksSize(); j++) {
                project.getCategory(i).getTask(j).removeUser(deletedUser);
            }
        }

    }

    /**
     * Metode auxliar que indica si ja està afegit l'usuari a la tasca en edicio
     * @param user Usuari a comprovar
     * @return Si esta afegit
     */
    public boolean isUserAdded(User user) {
        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        Category category = dataManager.getEditingCategory();
        Task targetTask = project.getCategoryWithId(category.getId()).getTaskWithId(task.getId());
        return targetTask.getUsers().contains(user);
    }

    /**
     * Metode encarregat d'actualitzar els controladors de la tasca en edicio d'una categoria
     * @param categoryId Id de la categoria
     */
    public void updateTaskControllers(String categoryId) {
        Project project = dataManager.getSelectedProject();
        Task task = dataManager.getEditingTask();
        if(task != null) {
            Category targetCategory = project.getCategoryWithId(categoryId);
            Task targetTask = targetCategory.getTaskWithId(task.getId());
        }
    }

}