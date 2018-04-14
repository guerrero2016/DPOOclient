package View;

import javax.swing.*;
import java.awt.*;

public class ProjectInfoView extends JPanel {

    final JLabel projectNameLabel;
    final JTextArea membersTextArea;

    public ProjectInfoView () {

        JLabel nomLabel = new JLabel("Nom");
        nomLabel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
        nomLabel.setFont(nomLabel.getFont().deriveFont(Font.BOLD));
        projectNameLabel = new JLabel("Default name");
        projectNameLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel membersLabel = new JLabel("Members");
        membersLabel.setFont(membersLabel.getFont().deriveFont(Font.BOLD));
        membersLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        membersTextArea = new JTextArea();
        membersTextArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        membersTextArea.setEditable(false);

        JPanel namePanel = new JPanel(new BorderLayout());
        JPanel membersPanel = new JPanel(new BorderLayout());

        namePanel.add(nomLabel,BorderLayout.NORTH);
        namePanel.add(projectNameLabel, BorderLayout.CENTER);

        membersPanel.add(membersLabel, BorderLayout.NORTH);
        membersPanel.add(membersTextArea, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(namePanel, BorderLayout.NORTH);
        add(membersPanel, BorderLayout.CENTER);

        CustomDialog dialog = new CustomDialog("Informació", this);
        dialog.setDialogVisible(true);
    }

    public void setProjectName (String name) {
        this.projectNameLabel.setText(name);
    }

    public void setMembers (String members) {
        this.membersTextArea.setText(members);
    }
}
