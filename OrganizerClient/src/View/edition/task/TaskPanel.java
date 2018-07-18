package View.edition.task;

import model.project.Tag;
import View.edition.document.DocumentEnablePanel;
import View.utils.TransparentPanel;
import View.utils.TransparentScrollPanel;
import View.edition.task.tag.TagPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe que representa el panell de l'edicio d'una tasca
 */
public class TaskPanel extends TransparentPanel implements DocumentEnablePanel {

    public final static String ACTION_TASK_BACK = "TaskBack";
    public final static String ACTION_TASK_EDIT_NAME = "TaskEditName";
    public final static String ACTION_TASK_DELETE = "TaskDelete";
    public final static String ACTION_AFFIRMATIVE = "StateAffirmative";
    public final static String ACTION_NEGATIVE = "StateNegative";
    public final static String ACTION_DESCRIPTION_EDITION = "DescriptionEdition";
    public final static String ACTION_TAG_ADD = "TagAdd";

    private final static int MAX_TASK_LENGTH = 20;
    private final static int MAX_TAGS = 5;

    private final static String TASK_STATE_TITLE = "Tasca finalitzada?";
    private final static String AFFIRMATIVE_RADIO_BUTTON = "Yes";
    private final static String NEGATIVE_RADIO_BUTTON = "No";
    private final static String TASK_TITLE = "Task";
    private final static String DESCRIPTION_TITLE = "Description";
    private final static String TAGS_TITLE = "Tags";
    private final static String TAG_ADDER_TITLE = "New Tag";
    private final static String ADD_TITLE = "+";
    private final static String BACK_BUTTON = "Back";
    private final static String EDIT_BUTTON = "Edit";
    private final static String DELETE_BUTTON = "Delete";
    private final static String CHECK_BUTTON = "Save changes";

    private final JButton jbTaskBack;
    private final JTextField jtfTaskName;
    private final JButton jbTaskEditor;
    private final JButton jbTaskDelete;
    private final JButton jbDescriptionEditor;
    private final JTextArea jtaDescription;
    private final TransparentPanel tpTagsList;
    private final JTextField jtfTagName;
    private final JButton jbTagAdder;
    private final JRadioButton jrbAffirmative;
    private final JRadioButton jrbNegative;

    private Image editorIcon;
    private Image checkIcon;
    private Image deleteIcon;

    private ArrayList<TagPanel> tagPanels;

    private ActionListener actionListener;

    /**
     * Crea el panell d'edicio de tasca amb tots els botons, tags, icones...
     * @param backIcon Icona per a tornar enrere
     * @param editorIcon Icona per a editar
     * @param deleteIcon Icona per eliminar
     * @param checkIcon Icona per a revisar
     */
    public TaskPanel(Image backIcon, Image editorIcon, Image deleteIcon, Image checkIcon) {

        //Save needed icons
        this.editorIcon = editorIcon;
        this.checkIcon = checkIcon;
        this.deleteIcon = deleteIcon;

        //Panel settings
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(TASK_TITLE));

        //Task title panel
        final TransparentPanel tpTaskTitle = new TransparentPanel();
        tpTaskTitle.setLayout(new BorderLayout());
        tpTaskTitle.setBorder(BorderFactory.createEmptyBorder(5,5, 5, 0));
        add(tpTaskTitle, BorderLayout.PAGE_START);

        //Task name
        jtfTaskName = new JTextField();
        jtfTaskName.setOpaque(false);
        jtfTaskName.setBorder(BorderFactory.createEmptyBorder());
        jtfTaskName.setEditable(false);
        jtfTaskName.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        tpTaskTitle.add(jtfTaskName, BorderLayout.CENTER);

        //Task buttons panel
        final TransparentPanel tpTaskButtons = new TransparentPanel();
        tpTaskButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
        tpTaskTitle.add(tpTaskButtons, BorderLayout.LINE_END);

        //Task back button
        if(backIcon != null) {
            jbTaskBack = new JButton(new ImageIcon(backIcon.getScaledInstance(20, 20,
                    Image.SCALE_SMOOTH)));
            jbTaskBack.setBorder(null);
        } else {
            jbTaskBack = new JButton(BACK_BUTTON);
            jbTaskBack.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        }

        jbTaskBack.setActionCommand(ACTION_TASK_BACK);
        tpTaskButtons.add(jbTaskBack);

        //Task editor button
        if(editorIcon != null) {
            jbTaskEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(20, 20,
                    Image.SCALE_SMOOTH)));
            jbTaskEditor.setBorder(null);
        } else {
            jbTaskEditor = new JButton(EDIT_BUTTON);
            jbTaskEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        }

        jbTaskEditor.setActionCommand(ACTION_TASK_EDIT_NAME);
        tpTaskButtons.add(jbTaskEditor);

        //Task delete icon
        if(deleteIcon != null) {
            jbTaskDelete = new JButton(new ImageIcon(deleteIcon.getScaledInstance(20, 20,
                    Image.SCALE_SMOOTH)));
            jbTaskDelete.setBorder(null);
        } else {
            jbTaskDelete = new JButton(DELETE_BUTTON);
            jbTaskDelete.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        }

        jbTaskDelete.setActionCommand(ACTION_TASK_DELETE);
        tpTaskButtons.add(jbTaskDelete);

        //Content panel
        final TransparentPanel tpContent = new TransparentPanel();
        tpContent.setLayout(new GridBagLayout());
        add(tpContent, BorderLayout.CENTER);

        //Task state panel
        final TransparentPanel tpTaskState = new TransparentPanel();
        tpTaskState.setLayout(new FlowLayout(FlowLayout.LEFT));

        GridBagConstraints gbcTaskState = new GridBagConstraints();
        gbcTaskState.gridx = 0;
        gbcTaskState.gridy = 0;
        gbcTaskState.weightx = 1;
        gbcTaskState.weighty = 0.05;
        gbcTaskState.fill = GridBagConstraints.BOTH;

        tpContent.add(tpTaskState, gbcTaskState);

        //Task state title
        final JLabel jlTaskState = new JLabel(TASK_STATE_TITLE);
        jlTaskState.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        tpTaskState.add(jlTaskState);

        //Affirmative button
        jrbAffirmative = new JRadioButton(AFFIRMATIVE_RADIO_BUTTON);
        jrbAffirmative.setOpaque(false);
        jrbAffirmative.setActionCommand(ACTION_AFFIRMATIVE);
        tpTaskState.add(jrbAffirmative);

        //Negative button
        jrbNegative = new JRadioButton(NEGATIVE_RADIO_BUTTON);
        jrbNegative.setOpaque(false);
        jrbNegative.setActionCommand(ACTION_NEGATIVE);
        tpTaskState.add(jrbNegative);

        //Button group
        final ButtonGroup bgTaskState = new ButtonGroup();
        bgTaskState.add(jrbAffirmative);
        bgTaskState.add(jrbNegative);
        jrbNegative.setSelected(true);

        //Description panel
        final TransparentPanel tpDescription = new TransparentPanel();
        tpDescription.setLayout(new BorderLayout());

        GridBagConstraints gbcDescription = new GridBagConstraints();
        gbcDescription.gridx = 0;
        gbcDescription.weightx = 1;
        gbcDescription.weighty = 0.35;
        gbcDescription.fill = GridBagConstraints.BOTH;

        tpContent.add(tpDescription, gbcDescription);

        //Description title panel
        final TransparentPanel tpDescriptionTitle = new TransparentPanel();
        tpDescriptionTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        tpDescription.add(tpDescriptionTitle, BorderLayout.PAGE_START);

        //Description title
        final JLabel jlDescription = new JLabel(DESCRIPTION_TITLE);
        jlDescription.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        jlDescription.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        tpDescriptionTitle.add(jlDescription);

        //Description editor button
        if(editorIcon != null) {
            jbDescriptionEditor = new JButton(new ImageIcon(editorIcon.getScaledInstance(16, 16,
                    Image.SCALE_SMOOTH)));
            jbDescriptionEditor.setBorder(null);
        } else {
            jbDescriptionEditor = new JButton(EDIT_BUTTON);
            jbDescriptionEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 8));
        }

        jbDescriptionEditor.setActionCommand(ACTION_DESCRIPTION_EDITION);
        tpDescriptionTitle.add(jbDescriptionEditor);

        //Scrollable description text
        final JScrollPane jspDescription = new JScrollPane();
        tpDescription.add(jspDescription);

        //Description text
        jtaDescription = new JTextArea();
        jtaDescription.setEditable(false);
        jspDescription.getViewport().setView(jtaDescription);

        //Tags panel
        final TransparentPanel tpTags = new TransparentPanel();
        tpTags.setLayout(new BorderLayout());

        GridBagConstraints gbcTags = new GridBagConstraints();
        gbcTags.gridx = 0;
        gbcTags.weightx = 1;
        gbcTags.weighty = 0.6;
        gbcTags.fill = GridBagConstraints.BOTH;

        tpContent.add(tpTags, gbcTags);

        //Tags title panel
        final TransparentPanel tpTagsTitle = new TransparentPanel();
        tpTagsTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        tpTags.add(tpTagsTitle, BorderLayout.PAGE_START);

        //Tags title
        final JLabel jlTagsTitle = new JLabel(TAGS_TITLE);
        jlTagsTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        jlTagsTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        tpTagsTitle.add(jlTagsTitle);

        //Scrollable tags list
        final TransparentScrollPanel tspTags = new TransparentScrollPanel();
        tpTags.add(tspTags, BorderLayout.CENTER);

        //Tags list
        tpTagsList = new TransparentPanel();
        tpTagsList.setLayout(new GridBagLayout());
        tspTags.getViewport().setView(tpTagsList);
        tagPanels = new ArrayList<>();

        //Tag adder panel
        final JPanel jpTagsAdder = new JPanel(new BorderLayout());
        tpTags.add(jpTagsAdder, BorderLayout.PAGE_END);

        //Tag adder title
        final JLabel jlTagAdderTitle = new JLabel(TAG_ADDER_TITLE);
        jlTagAdderTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jlTagAdderTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jpTagsAdder.add(jlTagAdderTitle, BorderLayout.LINE_START);

        //Tag adder field
        jtfTagName = new JTextField();
        jtfTagName.setEditable(true);
        jpTagsAdder.add(jtfTagName, BorderLayout.CENTER);

        //Tag adder button
        jbTagAdder = new JButton(ADD_TITLE);
        jbTagAdder.setEnabled(false);
        jbTagAdder.setActionCommand(ACTION_TAG_ADD);
        jpTagsAdder.add(jbTagAdder, BorderLayout.LINE_END);

    }

    /**
     * Funcio que recupera el nom de la tasca
     * @return Nom de la tasca
     */
    public String getTaskName() {

        if(jtfTaskName.getText() != null) {
            return jtfTaskName.getText();
        }

        return null;

    }

    /**
     * Procediment que assigna un nom a la tasca. Si es massa llarg l'escurca
     * @param taskName Nom de la tasca
     */
    public void setTaskName(String taskName) {
        if(taskName.length() <= MAX_TASK_LENGTH) {
            jtfTaskName.setText(taskName);
        } else {
            String shortTaskName = taskName.substring(0, MAX_TASK_LENGTH) + "...";
            jtfTaskName.setText(shortTaskName);
        }
    }

    /**
     * Procediment que assigna si el nom de la tasca es editable o no. Quan acaba de ser editat, s'envia les dades al
     * servidor
     * @param editableState Indica si es editable o no
     * @param completeTaskName Nom de la tasca complet
     */
    @SuppressWarnings("Duplicates")
    public void setTaskNameEditable(boolean editableState, String completeTaskName) {

        if(editableState) {

            jtfTaskName.setText(completeTaskName);

            if(checkIcon != null) {
                jbTaskEditor.setText(null);
                jbTaskEditor.setIcon(new ImageIcon(checkIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                jbTaskEditor.setBorder(null);
            } else {
                jbTaskEditor.setIcon(null);
                jbTaskEditor.setText(CHECK_BUTTON);
                jbTaskEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
                jbTaskEditor.setBorder(new JButton().getBorder());
            }

        } else {

            setTaskName(completeTaskName);

            if(editorIcon != null) {
                jbTaskEditor.setText(null);
                jbTaskEditor.setIcon(new ImageIcon(editorIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                jbTaskEditor.setBorder(null);
            } else {
                jbTaskEditor.setIcon(null);
                jbTaskEditor.setText(EDIT_BUTTON);
                jbTaskEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
                jbTaskEditor.setBorder(new JButton().getBorder());
            }

        }

        jtfTaskName.setOpaque(editableState);
        jtfTaskName.setEditable(editableState);

    }

    /**
     * Funcio que retorna si el nom de la tasca es editable o no.
     * @return Si es editable o no
     */
    public boolean isTaskNameEditable() {
        return jtfTaskName.isEditable();
    }

    /**
     * Procediment que assigna si una tasca esta assignada o no. Modifica els RadioButton de la vista
     * @param finishedState Estat
     */
    public void setTaskFinished(boolean finishedState) {
        if(finishedState) {
            jrbAffirmative.setSelected(finishedState);
        } else {
            jrbNegative.setSelected(!finishedState);
        }
    }

    /**
     * Funcio que recupera la descripcio de la tasca
     * @return Descripcio de la tasca
     */
    public String getDescription() {
        return jtaDescription.getText();
    }

    /**
     * Procediment que assigna una descripcio al TextArea
     * @param description Descripcio
     */
    public void setDescription(String description) {
        jtaDescription.setText(description);
    }

    /**
     * Procediment que neteja la llista d'etiquetes
     */
    public void cleanTagsList() {
        tagPanels = new ArrayList<>();
        tpTagsList.removeAll();
        revalidate();
        repaint();
    }

    /**
     * Procediment que assigna la llista d'etiquetes a la vista
     * @param tags Llista d'etiquetes
     */
    public void setTagsList(ArrayList<Tag> tags) {
        if(tags != null) {

            tagPanels = new ArrayList<>();

            for (int i = 0; i < tags.size(); i++) {
                addTag(tags.get(i));
            }

            revalidate();
            repaint();

        }
    }

    /**
     * Procediment encarregat d'afegir una etiqueta a la llista d'etiquetes
     * @param tag Etiqueta a afegir
     */
    public void addTag(Tag tag) {
        TagPanel tagPanel = new TagPanel(editorIcon, deleteIcon, tag);
        tagPanels.add(tagPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = (tagPanels.size() - 1) % MAX_TAGS;
        gbc.gridy = (tagPanels.size() - 1) / MAX_TAGS;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
        tpTagsList.add(tagPanel, gbc);
        revalidate();
        repaint();
    }

    /**
     * Procediment encarregat d'eliminar una etiqueta de la llista
     * @param tagIndex Index de l'etiqueta
     */
    public void removeTag(int tagIndex) {
        if(tagIndex < tagPanels.size()) {

            tpTagsList.setVisible(false);
            tpTagsList.removeAll();
            tagPanels.remove(tagIndex);

            for(int i = 0; i < tagPanels.size(); i++) {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = i % MAX_TAGS;
                gbc.gridy = i / MAX_TAGS;
                gbc.weightx = 1;
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.fill = GridBagConstraints.NONE;
                tpTagsList.add(tagPanels.get(i), gbc);
            }

            tpTagsList.setVisible(true);
            revalidate();
            repaint();

        }
    }

    /**
     * Funcio que recupera el nom de la nova etiqueta creada
     * @return Nom de l'etiqueta
     */
    public String getNewTagName() {
        return jtfTagName.getText();
    }

    /**
     * Procediment que neteja el camp d'afegir etiqueta
     */
    public void cleanNewTagName() {
        jtfTagName.setText(null);
    }

    /**
     * Procediment que assigna si la descripcio de la tasca es editable o no. Quan acaba de ser editada s'envien les
     * dades al servidor
     * @param editableState Estat de la descripcio
     */
    @SuppressWarnings("Duplicates")
    public void setDescriptionEditable(boolean editableState) {

        if(editableState) {
            if(checkIcon != null) {
                jbDescriptionEditor.setText(null);
                jbDescriptionEditor.setIcon(new ImageIcon(checkIcon.getScaledInstance(16, 16,
                        Image.SCALE_SMOOTH)));
                jbDescriptionEditor.setBorder(null);
            } else {
                jbTaskEditor.setIcon(null);
                jbTaskEditor.setText(CHECK_BUTTON);
                jbTaskEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 8));
                jbTaskEditor.setBorder(new JButton().getBorder());
            }
        } else {
            if(editorIcon != null) {
                jbDescriptionEditor.setText(null);
                jbDescriptionEditor.setIcon(new ImageIcon(editorIcon.getScaledInstance(16, 16,
                        Image.SCALE_SMOOTH)));
                jbDescriptionEditor.setBorder(null);
            } else {
                jbTaskEditor.setIcon(null);
                jbTaskEditor.setText(EDIT_BUTTON);
                jbTaskEditor.setFont(new Font(Font.DIALOG, Font.BOLD, 8));
                jbTaskEditor.setBorder(new JButton().getBorder());
            }
        }
        jtaDescription.setEditable(editableState);
    }

    /**
     * Funcio que recupera si la descripcio s'està editant o no
     * @return True si s'esta editant, false si no
     */
    public boolean isDescriptionEditable() {
        return jtaDescription.isEditable();
    }

    /**
     * Funcio que recupera una etiqueta de la llista. Retorna null si no existeix
     * @param tagIndex Index de l'etiqueta
     * @return Panell de l'etiqueta
     */
    public TagPanel getTagPanel(int tagIndex) {
        if(tagIndex < tagPanels.size()) {
            return tagPanels.get(tagIndex);
        }
        return null;
    }

    /**
     * Procediment que reseteja l'ActionListener
     */
    public void resetActionController() {
        jbTaskBack.removeActionListener(actionListener);
        jbTaskEditor.removeActionListener(actionListener);
        jbTaskDelete.removeActionListener(actionListener);
        jbDescriptionEditor.removeActionListener(actionListener);
        jbTagAdder.removeActionListener(actionListener);
        jrbAffirmative.removeActionListener(actionListener);
        jrbNegative.removeActionListener(actionListener);
        actionListener = null;
    }

    /**
     * Procediment que assigna un ActionListener als botons
     * @param actionListener Listener dels botons
     */
    public void registerActionController(ActionListener actionListener) {
        this.actionListener = actionListener;
        jbTaskBack.addActionListener(actionListener);
        jbTaskEditor.addActionListener(actionListener);
        jbTaskDelete.addActionListener(actionListener);
        jbDescriptionEditor.addActionListener(actionListener);
        jbTagAdder.addActionListener(actionListener);
        jrbAffirmative.addActionListener(actionListener);
        jrbNegative.addActionListener(actionListener);
    }

    /**
     * Procediment que assigna un DocumentListener al camp de text
     * @param documentListener Listener del text
     */
    public void registerDocumentController(DocumentListener documentListener) {
        jtfTagName.getDocument().addDocumentListener(documentListener);
    }

    /**
     * Metode que habilitat o deshabilita el boto d'afegir etiquetes
     * @param enableState Estat
     */
    @Override
    public void setDocumentEnableState(boolean enableState) {
        jbTagAdder.setEnabled(enableState);
    }

}