package View.edition.project;

import model.DataManager;
import model.project.Category;
import View.edition.document.DocumentEnablePanel;
import View.edition.TransparentPanel;
import View.edition.TransparentScrollPanel;
import View.edition.project.category.CategoryPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Constructor encarregat de generar un panell on mostrar la informació d'un projecte
 */
public class ProjectPanel extends TransparentPanel implements DocumentEnablePanel {

    public final static String ACTION_PROJECT_BACK = "ProjectBack";
    public final static String ACTION_PROJECT_EDIT_NAME = "ProjectEditName";
    public final static String ACTION_PROJECT_BACKGROUND = "ProjectBackground";
    public final static String ACTION_PROJECT_DELETE = "ProjectDelete";
    public final static String ACTION_CATEGORY_ADD = "CategoryAdd";

    private final static int MAX_PROJECT_LENGTH = 30;

    private final static String PROJECT_TITLE = "Project";
    private final static String NEW_CATEGORY_TITLE = "New Category";
    private final static String ADD_TITLE = "+";
    private final static String BACK_BUTTON = "Back";
    private final static String EDIT_BUTTON = "Edit";
    private final static String BACKGROUND_BUTTON = "Choose background";
    private final static String DELETE_BUTTON = "Delete";
    private final static String CHECK_BUTTON = "Save changes";

    private Image editorIcon;
    private Image deleteIcon;
    private Image leftIcon;
    private Image rightIcon;
    private Image checkIcon;

    private final JTextField jtfProjectName;
    private final TransparentPanel tpProjectButtons;
    private final JButton jbProjectBack;
    private final JButton jbProjectEditor;
    private final JButton jbBackground;
    private final JButton jbProjectDelete;
    private final TransparentPanel tpCategories;
    private final JTextField jtfCategoryName;
    private final JButton jbCategoryAdder;

    private ArrayList<CategoryPanel> categoryPanels;

    private ActionListener actionListener;

    /**
     * Constructor que requereix unes imatges per als butons que usa (poden valdre null)
     * @param editorIcon Botó d'edició
     * @param backgroundIcon Botó de canvi de fons de pantalla
     * @param deleteIcon Botó d'eliminar
     * @param leftIcon Botó de moure a l'esquerre
     * @param rightIcon Botó de moure a la dreta
     * @param checkIcon Botó de finalitzat
     * @param backIcon Botó de tornar enrere
     */
    public ProjectPanel(Image editorIcon, Image backgroundIcon, Image deleteIcon, Image leftIcon, Image rightIcon,
                        Image checkIcon, Image backIcon) {

        //Project settings
        this.editorIcon = editorIcon;
        this.deleteIcon = deleteIcon;
        this.leftIcon = leftIcon;
        this.rightIcon = rightIcon;
        this.checkIcon = checkIcon;
        categoryPanels = new ArrayList<>();

        //Panel settings
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(PROJECT_TITLE));

        //Project title
        final TransparentPanel tpProjectTitle = new TransparentPanel();
        tpProjectTitle.setLayout(new BorderLayout());
        tpProjectTitle.setBorder(BorderFactory.createEmptyBorder(5,5, 5, 0));
        add(tpProjectTitle, BorderLayout.PAGE_START);

        //Project name
        jtfProjectName = new JTextField();
        jtfProjectName.setEditable(false);
        jtfProjectName.setOpaque(false);
        jtfProjectName.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        jtfProjectName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        tpProjectTitle.add(jtfProjectName, BorderLayout.CENTER);

        //Project buttons panel
        tpProjectButtons = new TransparentPanel();
        tpProjectButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
        tpProjectTitle.add(tpProjectButtons, BorderLayout.LINE_END);

        //Project back button
        if(backIcon != null) {
            jbProjectBack = new JButton(new ImageIcon(backIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            jbProjectBack.setBorder(null);
        } else {
            jbProjectBack = new JButton(BACK_BUTTON);
            jbProjectBack.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        }

        jbProjectBack.setActionCommand(ACTION_PROJECT_BACK);
        tpProjectButtons.add(jbProjectBack);

        //Project editor button
        if(editorIcon != null) {
            jbProjectEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            jbProjectEditor.setBorder(null);
        } else {
            jbProjectEditor = new JButton(EDIT_BUTTON);
            jbProjectEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        }

        jbProjectEditor.setActionCommand(ACTION_PROJECT_EDIT_NAME);
        tpProjectButtons.add(jbProjectEditor);
        jbProjectEditor.setVisible(false);

        //Project background button
        if(backgroundIcon != null) {
            jbBackground = new JButton(new ImageIcon(backgroundIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            jbBackground.setBorder(null);
        } else {
            jbBackground = new JButton(BACKGROUND_BUTTON);
            jbBackground.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        }

        jbBackground.setActionCommand(ACTION_PROJECT_BACKGROUND);
        tpProjectButtons.add(jbBackground);

        //Project delete button
        if (deleteIcon != null) {
            jbProjectDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            jbProjectDelete.setBorder(null);
        } else {
            jbProjectDelete = new JButton(DELETE_BUTTON);
            jbProjectDelete.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        }
        jbProjectDelete.setActionCommand(ACTION_PROJECT_DELETE);
        tpProjectButtons.add(jbProjectDelete);



        //Categories scrollable list
        TransparentScrollPanel tspCategories = new TransparentScrollPanel();
        add(tspCategories, BorderLayout.CENTER);

        //Categories list
        tpCategories = new TransparentPanel();
        tpCategories.setLayout(new GridBagLayout());
        tspCategories.getViewport().setView(tpCategories);

        //Category adder panel
        final TransparentPanel tpNewCategory = new TransparentPanel();
        tpNewCategory.setLayout(new BorderLayout());
        add(tpNewCategory, BorderLayout.PAGE_END);

        //New category title
        final JLabel jlCategoryTitle = new JLabel(NEW_CATEGORY_TITLE);
        jlCategoryTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlCategoryTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        tpNewCategory.add(jlCategoryTitle, BorderLayout.LINE_START);

        //New category name field
        jtfCategoryName = new JTextField();
        jtfCategoryName.setEditable(true);
        tpNewCategory.add(jtfCategoryName, BorderLayout.CENTER);

        //New category adder button
        jbCategoryAdder = new JButton(ADD_TITLE);
        jbCategoryAdder.setEnabled(false);
        jbCategoryAdder.setActionCommand(ACTION_CATEGORY_ADD);
        tpNewCategory.add(jbCategoryAdder, BorderLayout.LINE_END);
    }

    /**
     * Mètode que permet indicar els privilegis de l'usuari
     * @param owner Si és propietari
     */
    public void setProjectOwner(boolean owner) {
        jbProjectEditor.setVisible(owner);
        jbProjectDelete.setVisible(owner);
    }

    /**
     * Mètode encarregat d'establir el nom del projecte
     * @param projectName Nom del projecte
     */
    public void setProjectName(String projectName) {
        if(projectName != null) {
            if (projectName.length() <= MAX_PROJECT_LENGTH) {
                jtfProjectName.setText(projectName);
            } else {
                String shortProjectName = projectName.substring(0, MAX_PROJECT_LENGTH) + "...";
                jtfProjectName.setText(shortProjectName);
            }
        }
    }

    /**
     * Getter del nom del projecte
     * @return Nom del projecte
     */
    public String getProjectName() {
        return jtfProjectName.getText();
    }

    /**
     * Mètode que permet editar el nom d'un projecte
     * @param editableState Estat d'edició
     * @param completeProjectName Nom del projecte
     */
    public void setProjectNameEditable(boolean editableState, String completeProjectName) {

        if(editableState) {

            jtfProjectName.setText(completeProjectName);

            if(checkIcon != null) {
                jbProjectEditor.setText(null);
                jbProjectEditor.setIcon(new ImageIcon(checkIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                jbProjectEditor.setBorder(null);
            } else {
                jbProjectEditor.setIcon(null);
                jbProjectEditor.setText(CHECK_BUTTON);
                jbProjectEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
                jbProjectEditor.setBorder(new JButton().getBorder());
            }

        } else {

            setProjectName(completeProjectName);

            if(editorIcon != null) {
                jbProjectEditor.setText(null);
                jbProjectEditor.setIcon(new ImageIcon(editorIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                jbProjectEditor.setBorder(null);
            } else {
                jbProjectEditor.setIcon(null);
                jbProjectEditor.setText(EDIT_BUTTON);
                jbProjectEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
                jbProjectEditor.setBorder(new JButton().getBorder());
            }

        }
        jtfProjectName.setOpaque(editableState);
        jtfProjectName.setEditable(editableState);
    }

    /**
     * Mètode que indica si el nom del projecte és editable
     * @return Si és editable
     */
    public boolean isProjectNameEditable() {
        return jtfProjectName.isEditable();
    }

    /**
     * Getter d'un panell d'una categoria
     * @param categoryIndex Index del panell
     * @return Panell de la categoria
     */
    public CategoryPanel getCategoryPanel(int categoryIndex) {
        if(categoryIndex < categoryPanels.size()) {
            return categoryPanels.get(categoryIndex);
        }
        return null;
    }

    /**
     * Mètode que permet eliminar tots els panells de categories
     */
    public void cleanCategories() {
        categoryPanels = new ArrayList<>();
        tpCategories.removeAll();
        revalidate();
        repaint();
    }

    /**
     * Mètode que permet afegir una categoria
     * @param category Categoria a afegir
     */
    public void addCategoryToView(Category category) {
        if(category != null) {

            GridBagConstraints gbc;

            if(!categoryPanels.isEmpty()) {
                CategoryPanel categoryPanel = categoryPanels.get(categoryPanels.size() - 1);
                gbc = ((GridBagLayout) tpCategories.getLayout()).getConstraints(categoryPanel);
                gbc.gridx += 1;
            } else {
                gbc = new GridBagConstraints();
                gbc.gridy = 0;
                gbc.gridx = 0;
                gbc.weighty = 1;
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.fill = GridBagConstraints.VERTICAL;
            }

            categoryPanels.add(new CategoryPanel(category, editorIcon, deleteIcon, leftIcon, rightIcon, checkIcon));
            tpCategories.add(categoryPanels.get(categoryPanels.size() - 1), gbc);
            revalidate();
            repaint();

        }
    }

    /**
     * Mètode que permet eliminar una categoria
     * @param categoryIndex índex de la categoria
     */
    public void removeCategory(int categoryIndex) {
        if(categoryIndex >= 0 && categoryIndex < categoryPanels.size()) {
            CategoryPanel categoryPanel = categoryPanels.get(categoryIndex);
            categoryPanels.remove(categoryPanel);
            tpCategories.remove(categoryPanel);
            revalidate();
            repaint();
        }
    }

    /**
     * Mètode que permet canviar d'ordre 2 categories
     * @param firstCategoryIndex Index de la primera categoria
     * @param secondCategoryIndex Index de la segona categoria
     */
    public void swapCategories(int firstCategoryIndex, int secondCategoryIndex) {
        if(firstCategoryIndex >= 0 && firstCategoryIndex < categoryPanels.size() && secondCategoryIndex <
                categoryPanels.size() && secondCategoryIndex >= 0) {

            //Update panels order
            CategoryPanel categoryPanel1 = categoryPanels.get(firstCategoryIndex);
            CategoryPanel categoryPanel2 = categoryPanels.get(secondCategoryIndex);
            categoryPanels.set(firstCategoryIndex, categoryPanel2);
            categoryPanels.set(secondCategoryIndex, categoryPanel1);

            //Update view
            GridBagConstraints firstConstraints = ((GridBagLayout) tpCategories.getLayout()).getConstraints(
                    categoryPanels.get(secondCategoryIndex));
            GridBagConstraints secondConstraints = ((GridBagLayout) tpCategories.getLayout()).getConstraints(
                    categoryPanels.get(firstCategoryIndex));
            tpCategories.remove(categoryPanel1);
            tpCategories.remove(categoryPanel2);
            tpCategories.add(categoryPanel2, firstConstraints);
            tpCategories.add(categoryPanel1, secondConstraints);

            revalidate();
            repaint();

        }
    }

    /**
     * Getter del nom de la nova categoria
     * @return Nom de la categoria
     */
    public String getNewCategoryName() {
        return jtfCategoryName.getText();
    }

    /**
     * Mètode que neteja el nom de la nova categoria
     */
    public void cleanNewCategoryName() {
        jtfCategoryName.setText(null);
    }

    /**
     * Mètode encarregat d'eliminar l'actionListener de la vista
     */
    public void resetActionController() {
        jbProjectBack.removeActionListener(actionListener);
        jbProjectEditor.removeActionListener(actionListener);
        jbBackground.removeActionListener(actionListener);
        jbProjectDelete.removeActionListener(actionListener);
        jbCategoryAdder.removeActionListener(actionListener);
        actionListener = null;
    }

    /**
     * Mètode encarregat de registrar un actionListener
     * @param actionListener Controlador
     */
    public void registerActionController(ActionListener actionListener) {
        this.actionListener = actionListener;
        jbProjectBack.addActionListener(actionListener);
        jbProjectEditor.addActionListener(actionListener);
        jbBackground.addActionListener(actionListener);
        jbProjectDelete.addActionListener(actionListener);
        jbCategoryAdder.addActionListener(actionListener);
    }

    /**
     * Mètode encarregat de registrar un DocumentListener
     * @param documentListener Controlador
     */
    public void registerDocumentController(DocumentListener documentListener) {
        jtfCategoryName.getDocument().addDocumentListener(documentListener);
    }

    /**
     * Mètode que permet habilitar o deshabilitar l'afegir categories
     * @param enableState Estat
     */
    @Override
    public void setDocumentEnableState(boolean enableState) {
        jbCategoryAdder.setEnabled(enableState);
    }

}