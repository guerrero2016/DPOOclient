package View;

import Controller.LogInController;
import Controller.ProjectSelectionController;
import Controller.SignInController;
import model.DataManager;
import model.project.Project;
import View.edition.EditionPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
    private JButton jbLogout;

    public MainView(LogInPanel logInPanel, SignInPanel signInPanel, ProjectsMainView projectsMainView,
                    EditionPanel editionPanel) {

        JPanel jpIdentifyPanel = new JPanel(new BorderLayout());
        JLabel jlPicLabel = new JLabel();
        try {
            BufferedImage myPicture;
            myPicture = ImageIO.read(new File(IMAGE_PATH));
            Image resized = myPicture.getScaledInstance(750,450,Image.SCALE_SMOOTH);
            jlPicLabel = new JLabel(new ImageIcon(resized));
            jpIdentifyPanel.setBackground(Color.WHITE);
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
        this.editionPanel = editionPanel;
        add(editionPanel, PROJECT_CONSTRAINT);

        DataManager.getSharedInstance().setWhatPanel(LogInPanel.LOGIN);

        jbLogout = new JButton("Tancar sessió");

        super.setMinimumSize(new Dimension(1000,500));
        super.setSize(1200,750);
        super.setTitle("LogIn - Organizer");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);

    }

    public void swapPanel(int whatPanel) {
        DataManager.getSharedInstance().setWhatPanel(whatPanel);
        switch (whatPanel) {
            case SignInPanel.SIGNIN:
                this.signInPanel.clearScreen();
                super.setTitle("SignIn - Organizer");
                ((CardLayout)jpLogSign.getLayout()).show(jpLogSign, "signIn");
                ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), "identify");
                break;

            case LogInPanel.LOGIN:
                this.setJMenuBar(null);
                this.logInPanel.clearScreen();
                super.setTitle("LogIn - Organizer");
                ((CardLayout)jpLogSign.getLayout()).show(jpLogSign, "logIn");
                ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), "identify");
                break;

            case ProjectsMainView.VIEW_TAG:
                super.setTitle("Organizer");
                JMenuBar jMenuBar = new JMenuBar();
                jMenuBar.add(Box.createHorizontalGlue());
                jMenuBar.setBorder(BorderFactory.createEmptyBorder(5,5,5,10));
                jMenuBar.setForeground(Color.CYAN);
                jMenuBar.add(jbLogout);

                setJMenuBar(jMenuBar);

                ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), ProjectsMainView.VIEW_NAME);
                break;

            case PROJECT_ID:
                super.setTitle(PROJECT_TITLE);
                ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), PROJECT_CONSTRAINT);
                break;
        }
    }

    public void addController (WindowListener wl, ActionListener al){
        this.addWindowListener(wl);
        jbLogout.addActionListener(al);
    }

    public void showErrorDialog(String errorMSG) {
        JOptionPane.showMessageDialog(this, errorMSG,
                "Error", JOptionPane.ERROR_MESSAGE);

    }

}
