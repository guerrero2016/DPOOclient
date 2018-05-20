package Controller.edition;

import Controller.MainViewController;
import Controller.edition.document.DocumentController;
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
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encarregada de controlar tot allò relacionat amb un projecte i communicar els canvis a un altre controlador
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

    private Project project;
    private Category category;
    private Task task;

    private TaskController taskController;

    private boolean isEditing;

    private MainViewController mainController;

    /**
     * Constructor que deixa preparada la vista a espera d'inserir contingut
     * @param editionPanel
     */
    public EditionController(EditionPanel editionPanel) {

        //Link variables
        this.editionPanel = editionPanel;
        projectPanel = editionPanel.getProjectPanel();
        projectUserPanel = editionPanel.getProjectUserPanel();
        taskPanel = editionPanel.getTaskPanel();
        taskUserPanel = editionPanel.getTaskUserPanel();

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

    //TODO
    private void addCommunicators() {
        mainController.addCommunicator(new ProjectDeletedCommunicator(), ServerObjectType.DELETE_PROJECT);
        mainController.addCommunicator(new CategoryDeleteCommunicator(), ServerObjectType.DELETE_CATEGORY);
        mainController.addCommunicator(new CategorySetCommunicator(), ServerObjectType.SET_CATEGORY);
        mainController.addCommunicator(new CategorySwapCommunicator(), ServerObjectType.SWAP_CATEGORY);
        mainController.addCommunicator(new TaskSetCommunicator(), ServerObjectType.SET_TASK);
        mainController.addCommunicator(new TaskDeletedCommunicator(), ServerObjectType.DELETE_TASK);
    }

    //TODO
    public void removeCommunicators () {
        mainController.removeCommunicator(ServerObjectType.SWAP_CATEGORY);
        mainController.removeCommunicator(ServerObjectType.DELETE_PROJECT);
        mainController.removeCommunicator(ServerObjectType.SET_CATEGORY);
        mainController.removeCommunicator(ServerObjectType.DELETE_CATEGORY);
        mainController.removeCommunicator(ServerObjectType.DELETE_TASK);
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
     * @param mainController Controlador que gestionarà aquest controlador
     */
    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
        addCommunicators();
    }

    /**
     * Mètode encarregat d'afegir una categoria amb els corresponents canvis
     * @param category Categoria a afegir
     */
    public void addCategory(Category category) {
        projectPanel.cleanNewCategoryName();
        projectPanel.addCategoryToView(category);
        project.setCategory(category);
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoriesSize() - 1);
        categoryPanel.registerActionController(new CategoryActionController(this, categoryPanel, category));
        categoryPanel.registerMouseController(new CategoryMouseController(this, category));
        categoryPanel.registerDocumentController(new DocumentController(categoryPanel));
        categoryPanel.resetDnDController();
        categoryPanel.registerDnDController(new TaskListController(this, category,
                categoryPanel.getListComponent()));
    }

    /**
     * Mètode encarregat d'afegir una tasca a una determinada categoria
     * @param categoryIndex Index de la categoria
     * @param task Tasca a afegir
     */
    public void addTask(int categoryIndex, Task task) {
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(categoryIndex);
        categoryPanel.cleanNewTaskName();
        categoryPanel.addNewTask(task);
    }

    /**
     * Mètode encarregat d'afegir el contingut d'un projecte a la vista
     * @param project Projecte a mostrar
     */
    public void loadProject(Project project) {

        //Default config
        projectPanel.cleanCategories();
        projectUserPanel.cleanUserList();
        this.project = project;
        category = null;
        task = null;

        //Config project content
        projectPanel.hideDeleteButton();
        projectPanel.setProjectOwner(project.isOwner());
        editionPanel.setBackgroundImage(project.getBackground());
        projectPanel.setProjectName(project.getName());
        projectPanel.cleanCategories();

        for(int i = 0; i < project.getCategoriesSize(); i++) {
            projectPanel.addCategoryToView(project.getCategory(i));
            CategoryPanel categoryPanel = projectPanel.getCategoryPanel(i);
            categoryPanel.resetActionController();
            System.out.println(project.getCategory(i).getId());
            categoryPanel.registerActionController(new CategoryActionController(this, categoryPanel,
                    project.getCategory(i)));
            categoryPanel.resetMouseController();
            categoryPanel.registerMouseController(new CategoryMouseController(this, project.
                    getCategory(i)));
            categoryPanel.registerDocumentController(new DocumentController(categoryPanel));
            categoryPanel.resetDnDController();
            categoryPanel.registerDnDController(new TaskListController(this, project.getCategory(i),
                    categoryPanel.getListComponent()));
        }

        //Config project controllers
        projectPanel.resetActionController();
        projectPanel.registerActionController(new ProjectActionController(this, projectPanel, project));

        //Config users panel
        projectUserPanel.setUserList(project.getUsers());
        projectUserPanel.resetActionController();
        projectUserPanel.registerActionController(new ProjectAddUserController(project));
        projectUserPanel.resetMouseController();
        projectUserPanel.registerMouseController(new ProjectRemoveUserController(this, projectUserPanel,
                project));
        projectUserPanel.registerDocumentListener(new DocumentController(projectUserPanel));

        //Configure user permissions
        projectUserPanel.setEditionState(project.isOwner());

    }

    /**
     * Mètode encarregat de mostrar la vista d'un projecte prèviament carregat
     */
    public void showProjectContent() {
        System.out.println("Project content");
        isEditing = false;
        category = null;
        task = null;
        editionPanel.showProjectPanel();
    }

    /**
     * Mètode encarregat d'afegir el contingut d'una tasca a la vista
     * @param category Categoria on pertany la tasca
     * @param task Tasca a mostrar
     */
    public void setTaskContent(Category category, Task task) {

        //Default config
        this.category = category;
        this.task = task;

        //Config task content
        taskPanel.setTaskName(task.getName());
        taskPanel.setTaskFinished(task.isFinished());
        taskPanel.setDescription(task.getDescription());
        taskPanel.cleanTagsList();
        taskPanel.setTagsList(task.getTags());

        //Link controllers
        taskPanel.resetActionController();
        taskController = new TaskController(this, editionPanel.getTaskPanel(),
                task);
        taskPanel.registerActionController(taskController);

        for(int i = 0; i < task.getTagsSize(); i++) {
            TagPanel tagPanel = editionPanel.getTaskPanel().getTagPanel(i);
            tagPanel.resetActionController();
            tagPanel.registerActionController(new TagController(this, task.getTag(i)));
        }

        //Config task users controllers
        taskUserPanel.setUserList(task.getUsers());
        taskUserPanel.resetActionController();
        taskUserPanel.resetMouseController();

        if(project.isOwner()) {
            taskUserPanel.registerActionController(new TaskAddUserController(this, taskUserPanel, task));
            taskUserPanel.registerMouseController(new TaskRemoveUserController(this));
        }
    }

    /**
     * Mètode encarregat de mostrar la vista d'una tasca prèviament carregada
     */
    public void showTaskContent() {
        isEditing = false;
        editionPanel.showTaskPanel();
    }

    /**
     * Mètode que indica si algun camp d'edició està encara habilitat
     * @return Indica si algun camp està sent editat
     */
    public boolean isEditing() {
        return isEditing;
    }

    /**
     * Mètode que permet canviar l'estat d'edició del projecte
     * @param enableState Estat d'edició
     */
    public void setEditingState(boolean enableState) {
        isEditing = enableState;
    }

    /**
     * Mètode encarregat d'actualitzar el contingut d'un projecte al servidor
     * @param p Projecte a actualitzar
     */
    public void updateProject(Project p) {
        if(mainController != null) {
            try {
                Project aux = new Project(p.getId(), p.getName(), p.getColor(),
                        p.getCategories(), p.getUsers(), p.isOwner());
                aux.setBackground(p.getBackground());
                mainController.sendToServer(ServerObjectType.SET_PROJECT, aux);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mètode encarregat d'eliminar un usuari del projecte al servidor
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
     * Mètode encarregat d'actualitzar una tasca al servidor
     * @param task Tasca a actualitzar
     */
    public void updateTask(Task task) {
        if(mainController != null) {
            try {
                mainController.sendToServer(ServerObjectType.SET_TASK, category.getId());
                mainController.sendToServer(null, task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mètode encarregat d'actualitzar una categoria al servidor
     * @param category Categoria a actualitzar
     */
    public void updateCategory(Category category) {
        if(mainController != null) {
            try {
                if (category.getOrder() == -1) {
                    category.setOrder(project.getCategoriesSize());
                }
                mainController.sendToServer(ServerObjectType.SET_CATEGORY, category);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mètode encarregat d'actualitzar el projecte de la vista
     * @param p Projecte a actualitzar
     */
    public void updateProjectView(Project p) {
        project = p;
        editionPanel.getProjectPanel().setProjectName(p.getName());
        editionPanel.setBackgroundImage(p.getBackground());
    }

    /**
     * Mètode encarregat de crear una tasca al servidor
     * @param task Tasca a afegir
     * @param category Categoria on pertany la tasca
     */
    public void createTask (Task task, Category category) {
        if (task.getOrder() == -1) {
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
     * Mètode encarregat d'enviar una etiqueta al servidor
     * @param task Tasca on pertany l'etiqueta
     * @param tag Etiqueta a enviar
     */
    public void sendTag (Task task, Tag tag) {
        try {
            mainController.addCommunicator(new TagSetCommunicator(), ServerObjectType.SET_TAG);
            mainController.sendToServer(ServerObjectType.SET_TAG, task.getID());
            mainController.sendToServer(null, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode encarregat d'actualitzar una tasca a la vista
     * @param categoryId Id de la categoria de la tasca
     * @param task Tasca a actualitzar
     */
    public void updateTaskInView(String categoryId, Task task) {

        if(this.task != null && this.task.getID().equals(task.getID())) {
            taskPanel.setTaskName(task.getName());
            taskPanel.setDescription(task.getDescription());
        }

        Category targetCategory = project.getCategoryWithId(categoryId);
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoryIndex(
                targetCategory));
        categoryPanel.updateTask(targetCategory.getTaskIndex(task), task);

    }

    /**
     * Mètode encarregat d'actualitzar una categoria a la vista
     * @param category Categoria a actualitzar
     */
    public void updateCategoryInView(Category category) {
        projectPanel.getCategoryPanel(category.getOrder()).setCategoryName(category.getName());
    }

    /**
     * Mètode encarregat d'eliminar una tasca al servidor
     */
    public void deleteTask() {
        if(mainController != null) {
            try {
                mainController.sendToServer(ServerObjectType.DELETE_TASK, task);
                mainController.sendToServer(null, category.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mètode encarregat d'eliminar una tasca de la vista
     */
    public void deleteTaskInView() {
        CategoryPanel categoryPanel = projectPanel.getCategoryPanel(project.getCategoryIndex(category));
        categoryPanel.removeTask(task);
        category.deleteTask(task);
    }

    /**
     * Mètode encarregat d'eliminar una categoria de la vista
     * @param i Index de la categoria
     */
    public void deleteCategoryInView(int i) {
        projectPanel.removeCategory(i);
    }

    public void deleteCategory(String id_category) {
        Category category = DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(id_category);
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
     * Mètode encarregat d'obtenir un usuari del projecte si existeix
     * @param userName Nom de l'usuari a buscar
     * @return Usuari del projecte (null si no existeix)
     */
    public User getProjectUser(String userName) {
        for(int i = 0; i < project.getUsers().size(); i++) {
            if(userName.equals(project.getUser(i).getUserName())) {
                return project.getUser(i);
            }
        }
        if(userName.equals(project.getOwnerName())) {
            return new User(project.getOwnerName());
        }
        return null;
    }

    //TODO
    public void userJoinedProject(User user) {
        projectUserPanel.addUser(user);
    }

    /**
     * Mètode encarregat d'obtenir l'índex d'una categoria
     * @param category Categoria a buscar
     * @return Índex de la categoria al projecte
     */
    public int getCategoryIndex(Category category) {
        return project.getCategoryIndex(category);
    }

    /**
     * Mètode encarregat de canviar d'ordre 2 categories
     * @param firstCategoryIndex Índex del projecte de la primera categoria
     * @param secondCategoryIndex Índex del projecte de la segona categoria
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
     * Mètode encarregat de canviar d'ordre 2 categories a la vista
     * @param firstCategoryIndex Índex del projecte de la primera categoria
     * @param secondCategoryIndex Índex del projecte de la segona categoria
     */
    public void swapCategoriesInView(int firstCategoryIndex, int secondCategoryIndex) {
        project.swapCategories(firstCategoryIndex, secondCategoryIndex);
        projectPanel.swapCategories(firstCategoryIndex, secondCategoryIndex);
    }

    /**
     * Mètode encarregat de mostrar la vista de selecció de projectes
     */
    public void showProjectSelection() {
        if (mainController != null) {
            try {
                project = null;
                category = null;
                task = null;
                mainController.sendToServer(ServerObjectType.EXIT_PROJECT, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.exit(0);
        }
    }

    /**
     * Mètode encarregat d'eliminar un projecte al servidor
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
     * Mètode encarregat d'afegir un usuari d'una tasca  al servidor
     * @param user Usuari a afegir
     */
    public void addMemberInDB(User user) {
        if(mainController != null) {
            mainController.addMemberInDB(category.getId(), task.getID(), user);
        } else {
            task.addUser(user);
            taskUserPanel.addUser(user);
        }
    }

    /**
     * MNètode encarregat d'afegir un usuari d'una tasca al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca
     * @param user Usuari de la tasca a afegir
     */
    public void addMemberInProject(String categoryId, String taskId, User user) {

        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);
        targetTask.addUser(user);

        if(task != null && task.getID().equals(taskId)) {
            taskUserPanel.addUser(user);
        }

    }

    /**
     * Mètode encarregat d'eliminar un usuari d'una tasca al servidor
     * @param user
     */
    public void removeMemberInDB(User user) {
        if(mainController != null) {
            mainController.removeMemberInDB(category.getId(), task.getID(), user);
        } else {
            taskUserPanel.removeUser(task.getUserIndex(user));
            task.removeUser(user);
        }
    }

    /**
     * Mètode encarregat d'elimina un usuari d'una tasca al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca
     * @param user Usuari de la tasca a eliminar
     */
    public void removeMemberInProject(String categoryId, String taskId, User user) {

        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getID().equals(taskId)) {

            TaskRemoveUserController controller = (TaskRemoveUserController) taskUserPanel.getMouseListener();

            if(controller.isRemovingUser(user)) {
                controller.closeDialog();
            }

            taskUserPanel.removeUser(task.getUserIndex(user));

        }

        targetTask.removeUser(user);

    }

    /**
     * Mètode encarregat d'eliminar una etiqueta al servidor
     * @param tag Etiqueta a eliminar
     */
    public void removeTagInDB(Tag tag) {
        if(mainController != null) {
            mainController.removeTagInDB(category.getId(), task.getID(), tag);
        } else {
            taskPanel.removeTag(task.getTagIndex(tag));
            task.removeTag(tag);
        }
    }

    /**
     * Mètode encarregat d'eliminar una etiqueta al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca de l'etiqueta
     * @param tag Etiqueta a eliminar
     */
    public void removeTagInProject(String categoryId, String taskId, Tag tag) {

        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getID().equals(taskId)) {

            TagController controller = (TagController) taskPanel.getTagPanel(task.getTagIndex(tag)).getActionListener();

            if(controller.isRemovingTag(tag)) {
                controller.closeDialog();
            }

            taskPanel.removeTag(task.getTagIndex(tag));

        }

        targetTask.removeTag(tag);
        projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory)).
                updateTask(targetCategory.getTaskIndex(targetTask), targetTask);

    }

    /**
     * Mètode encarregat d'actualitzar una etiqueta al servidor
     * @param tag Etiqueta a actualitzar
     */
    public void editTagInDB(Tag tag) {
        mainController.editTagInDB(task.getID(), tag);
    }

    /**
     * Mètode encarregat d'editar una etiqueta al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca de l'etiqueta
     * @param tag Etiqueta a actualitzar
     */
    public void editTagInProject(String categoryId, String taskId, Tag tag) {
        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);
        Tag targetTag = targetTask.getTagWithId(tag.getId());

        if(task != null && task.getID().equals(taskId)) {
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
     * Mètode encarregat d'afegir una etiqueta al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca de l'etiqueta
     * @param tag Etiqueta a afegir
     */
    public void addTagInProject(String categoryId, String taskId, Tag tag) {

        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getID().equals(taskId)) {
            taskPanel.addTag(tag);
            TagPanel tagPanel = taskPanel.getTagPanel(task.getTagIndex(tag));
            tagPanel.registerActionController(new TagController(this, tag));
        }

        projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory)).updateTask(targetCategory.
                getTaskIndex(targetTask), targetTask);

    }

    /**
     * Mètode encarregat d'establir una tasca com a finalitzada al servidor
     */
    public void setTaskDoneInDB() {
        if(mainController != null) {
            mainController.setTaskDoneInDB(category.getId(), task.getID());
        }
    }

    /**
     * Mètode encarregat d'establir una tasca com a finalitzada al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca finalitzada
     */
    public void setTaskDoneInProject(String categoryId, String taskId) {

        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getID().equals(taskId)) {
            taskPanel.setTaskFinished(true);
        }

        targetTask.setFinished(true);
        projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory)).updateTask(targetCategory.
                getTaskIndex(targetTask), targetTask);

    }

    /**
     * Mètode encarregat d'establir una tasca com a no finalitzada al servidor
     */
    public void setTaskNotDoneInDB() {
        if(mainController != null) {
            mainController.setTaskNotDoneInDB(category.getId(), task.getID());
        }
    }

    /**
     * Mètode encarregat d'establir una tasca com a no finalitzada al projecte
     * @param categoryId Id de la categoria de la tasca
     * @param taskId Id de la tasca no finalitzada
     */
    public void setTaskNotDoneInProject(String categoryId, String taskId) {

        Category targetCategory = project.getCategoryWithId(categoryId);
        Task targetTask = targetCategory.getTaskWithId(taskId);

        if(task != null && task.getID().equals(taskId)) {
            taskPanel.setTaskFinished(false);
        }

        targetTask.setFinished(false);
        projectPanel.getCategoryPanel(project.getCategoryIndex(targetCategory)).updateTask(targetCategory.
                getTaskIndex(targetTask), targetTask);

    }

    /**
     * Mètode encarregat de canviar l'ordre de les tasques al servidor
     * @param category Categoria on pertanyen les tasques
     */
    public void swapTask(Category category) {
        try {
            mainController.sendToServer(ServerObjectType.SWAP_TASK, category.getTasks());
            mainController.sendToServer(null, category.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode encarregat de canviar l'ordre de les tasques al projecte
     * @param tasks Tasques reordenades
     * @param categoryID Id de la categoria de les tasques
     */
    public void swapTasksInView(ArrayList<Task> tasks, String categoryID) {
        projectPanel.getCategoryPanel(DataManager.getSharedInstance().getSelectedProject().
                getCategoryIndex(DataManager.getSharedInstance().getSelectedProject().getCategoryWithId(categoryID))).
                updateTasksList(tasks);
    }

    //TODO
    public void userLeftProject(User user) {

        projectUserPanel.removeUser(project.getUserIndex(user));
        project.deleteUser(project.getUserIndex(user));

        if(task != null) {
            if(task.getUserIndex(user) != Task.INVALID_INDEX) {
                taskUserPanel.removeUser(task.getUserIndex(user));
            }
        }

        for(int i = 0; i < project.getCategoriesSize(); i++) {
            for(int j = 0; j < project.getCategory(i).getTasksSize(); j++) {
                project.getCategory(i).getTask(j).removeUser(user);
            }
        }

    }

}