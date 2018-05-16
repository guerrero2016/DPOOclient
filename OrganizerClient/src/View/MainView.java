package View;

import Controller.LogInController;
import Controller.ProjectSelectionController;
import Controller.SignInController;
import model.project.Project;
import View.edition.EditionPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainView extends JFrame {
    private static final String IMAGE_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") +
            "img" + System.getProperty("file.separator") + "identifyImage.jpg";

    private final static String PROJECT_TITLE = "Organizer - Project";
    private final static String PROJECT_CONSTRAINT = "Project";
    public final static int PROJECT_ID = 4;

    private JPanel jpLogSign;
    private SignInPanel signInPanel;
    private LogInPanel logInPanel;
    private ProjectsMainView projectsView;
    private EditionPanel editionPanel;

    public MainView(LogInPanel logInPanel, SignInPanel signInPanel, ProjectsMainView projectsMainView) {
        JPanel jpIdentifyPanel = new JPanel(new BorderLayout());
        JLabel jlPicLabel = new JLabel();

        try {
            BufferedImage myPicture;
            myPicture = ImageIO.read(new File(IMAGE_PATH));
            Image resized = myPicture.getScaledInstance(750,750,Image.SCALE_SMOOTH);
            jlPicLabel = new JLabel(new ImageIcon(resized));
        } catch (IOException e) {
            e.printStackTrace();
        }

        jpIdentifyPanel.add(jlPicLabel, BorderLayout.LINE_START);
        this.logInPanel = logInPanel;
        this.signInPanel = signInPanel;
        this.projectsView = projectsMainView;

        jpLogSign = new JPanel(new CardLayout());


        jpLogSign.add(logInPanel, "logIn");
        jpLogSign.add(signInPanel, "signIn");
        jpLogSign.setMinimumSize(new Dimension(450, 500));
        jpLogSign.setBackground(Color.BLUE);
        jpIdentifyPanel.add(jpLogSign, BorderLayout.CENTER);

        this.getContentPane().setLayout(new CardLayout());
        this.add(jpIdentifyPanel, "identify");
        this.add(projectsView, ProjectsMainView.VIEW_NAME);

        //Edition panel
        try {
            editionPanel = new EditionPanel();
            add(editionPanel, PROJECT_CONSTRAINT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.setMinimumSize(new Dimension(1000,500));
        super.setSize(1200,750);
        super.setTitle("LogIn - Organizer");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);

    }

    public void swapPanel(int whatPanel) {
        switch (whatPanel) {

            case SignInPanel.SIGNIN:
                super.setTitle("SignIn - Organizer");
                ((CardLayout)jpLogSign.getLayout()).show(jpLogSign, "signIn");
                break;

            case LogInPanel.LOGIN:
                super.setTitle("LogIn - Organizer");
                ((CardLayout)jpLogSign.getLayout()).show(jpLogSign, "logIn");
                break;

            case ProjectsMainView.VIEW_TAG:
                super.setTitle("Organizer");
                ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), ProjectsMainView.VIEW_NAME);
                break;

            case PROJECT_ID:
                super.setTitle(PROJECT_TITLE);
                ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), PROJECT_CONSTRAINT);
                break;
        }
    }

    public void addControllerButton (LogInController lic, SignInController sic) {
        logInPanel.addControllerButton(lic);
        signInPanel.addControllerButton(sic);
    }
    
    public EditionPanel getEditionPanel() {
        return editionPanel;
    }

    public void showErrorDialog(String errorMSG) {
        JOptionPane.showMessageDialog(this, errorMSG,
                "Error", JOptionPane.ERROR_MESSAGE);

    }
}
