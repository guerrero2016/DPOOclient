package View.edition.project;

import ModelAEliminar.Category;
import ModelAEliminar.Task;
import View.document.DocumentEnablePanel;
import View.edition.TransparentPanel;
import View.edition.TransparentScrollPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProjectPanel extends TransparentPanel implements DocumentEnablePanel {

    public final static String ACTION_PROJECT_EDIT_NAME = "ProjectEditName";
    public final static String ACTION_PROJECT_BACKGROUND = "ProjectBackground";
    public final static String ACTION_PROJECT_DELETE = "ProjectDelete";
    public final static String ACTION_CATEGORY_ADD = "CategoryAdd";

    private final static int MAX_PROJECT_LENGTH = 30;

    private final static String PROJECT_TITLE = "Project";
    private final static String NEW_CATEGORY_TITLE = "New Category";
    private final static String ADD_TITLE = "+";

    private Image editorIcon;
    private Image deleteIcon;
    private Image leftIcon;
    private Image rightIcon;
    private Image checkIcon;

    private final JLabel jlProjectName;
    private final JButton jbProjectEditor;
    private final JButton jbBackground;
    private final JButton jbProjectDelete;
    private final TransparentPanel tpCategories;
    private final JTextField jtfCategoryName;
    private final JButton jbCategoryAdder;

    private ArrayList<CategoryPanel> categoryPanels;

    public ProjectPanel(Image editorIcon, Image backgroundIcon, Image deleteIcon, Image leftIcon, Image rightIcon,
                        Image checkIcon) {

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
        jlProjectName = new JLabel();
        jlProjectName.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        jlProjectName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        tpProjectTitle.add(jlProjectName, BorderLayout.CENTER);

        //Project buttons panel
        final TransparentPanel tpProjectButtons = new TransparentPanel();
        tpProjectButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
        tpProjectTitle.add(tpProjectButtons, BorderLayout.LINE_END);

        //Project editor button
        jbProjectEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        jbProjectEditor.setBorder(null);
        jbProjectEditor.setActionCommand(ACTION_PROJECT_EDIT_NAME);
        tpProjectButtons.add(jbProjectEditor);

        //Project background button
        jbBackground = new JButton(new ImageIcon(backgroundIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        jbBackground.setBorder(null);
        jbBackground.setActionCommand(ACTION_PROJECT_BACKGROUND);
        tpProjectButtons.add(jbBackground);

        //Project delete button
        jbProjectDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        jbProjectDelete.setBorder(null);
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

    public void setProjectName(String projectName) {
        if(projectName != null) {
            if (projectName.length() <= MAX_PROJECT_LENGTH) {
                jlProjectName.setText(projectName);
            } else {
                String shortProjectName = projectName.substring(0, MAX_PROJECT_LENGTH) + "...";
                jlProjectName.setText(shortProjectName);
            }
        }
    }

    public int getTotalCategories() {
        return categoryPanels.size();
    }

    public CategoryPanel getCategoryPanel(int categoryIndex) {

        if(categoryIndex < categoryPanels.size()) {
            return categoryPanels.get(categoryIndex);
        }

        return null;

    }

    public void addCategory(Category category) {
        if(category != null) {
            categoryPanels.add(new CategoryPanel(category, editorIcon, deleteIcon, leftIcon, rightIcon, checkIcon));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = categoryPanels.size();
            gbc.weighty = 1;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.VERTICAL;
            tpCategories.add(categoryPanels.get(categoryPanels.size() - 1), gbc);
        }
    }

    public void removeCategory(int categoryIndex) {
        if(categoryIndex >= 0 && categoryIndex < categoryPanels.size()) {
            System.out.println(categoryIndex);
            CategoryPanel categoryPanel = categoryPanels.get(categoryIndex);
            categoryPanels.remove(categoryPanel);
            tpCategories.remove(categoryPanel);
            revalidate();
            repaint();
        }
    }

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

    public String getNewCategoryName() {
        return jtfCategoryName.getText();
    }

    public void cleanNewCategoryName() {
        jtfCategoryName.setText(null);
    }

    public void registerActionController(ActionListener actionListener) {
        jbProjectEditor.addActionListener(actionListener);
        jbBackground.addActionListener(actionListener);
        jbProjectDelete.addActionListener(actionListener);
        jbCategoryAdder.addActionListener(actionListener);
    }

    public void registerDocumentController(DocumentListener documentListener) {
        jtfCategoryName.getDocument().addDocumentListener(documentListener);
    }

    @Override
    public void setDocumentEnableState(boolean enableState) {
        jbCategoryAdder.setEnabled(enableState);
    }

}