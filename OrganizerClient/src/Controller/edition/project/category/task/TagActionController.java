package Controller.edition.project.category.task;

import Controller.edition.EditionController;
import View.edition.project.category.task.TagPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TagActionController implements ActionListener {

    private EditionController mainController;
    private TagPanel view;
    private int currentTag;

    public TagActionController(EditionController mainController, TagPanel view, int currentTag) {
        this.mainController = mainController;
        this.view = view;
        this.currentTag = currentTag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(TagPanel.ACTION_TAG_NAME_EDIT)) {

        } else if(e.getActionCommand().equals(TagPanel.ACTION_TAG_DELETE)) {

        }
    }

}