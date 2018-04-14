package View;

import Controller.LogInController;
import Controller.ProjectSelectionController;
import Controller.SignInController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame {
    private static final String IMAGE_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") +
            "img" + System.getProperty("file.separator") + "identifyImage.jpg";

    private JPanel jpLogSign;
    private SignInPanel signInPanel;
    private LogInPanel logInPanel;
    private ProjectsMainView projectsView;

    public MainView() {
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
        logInPanel = new LogInPanel();
        signInPanel = new SignInPanel();
        jpLogSign = new JPanel(new CardLayout());


        jpLogSign.add(logInPanel, "logIn");
        jpLogSign.add(signInPanel, "signIn");
        jpLogSign.setMinimumSize(new Dimension(450, 500));
        jpLogSign.setBackground(Color.BLUE);
        jpIdentifyPanel.add(jpLogSign, BorderLayout.CENTER);

        projectsView = new ProjectsMainView();

        this.getContentPane().setLayout(new CardLayout());
        this.add(jpIdentifyPanel, "identify");
        this.add(projectsView, ProjectsMainView.VIEW_NAME);

        super.setMinimumSize(new Dimension(1000,500));
        super.setSize(1200,750);
        super.setTitle("LogIn - Organizer");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);

    }

    public void swapPanel(int whatPanel) {
        switch (whatPanel) {
            case 1:
                super.setTitle("LogIn - Organizer");
                ((CardLayout)jpLogSign.getLayout()).show(jpLogSign, "logIn");
                break;

            case 2:
                super.setTitle("SignIn - Organizer");
                ((CardLayout)jpLogSign.getLayout()).show(jpLogSign, "signIn");
                break;
            case ProjectsMainView.VIEW_TAG:
                super.setTitle("Organizer");
                ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), ProjectsMainView.VIEW_NAME);
                break;
        }
    }

    public void addControllerButton (LogInController lic, SignInController sic) {
        logInPanel.addControllerButton(lic);
        signInPanel.addControllerButton(sic);
    }

    public void addProjectSelectionController (ProjectSelectionController controller) {
        projectsView.registerProjectSelectionController(controller);
        projectsView.registerAddProjectViewController(controller);
    }

    public void createOwnerBoxProjects (String[] titles, Color[] colors) {
        projectsView.createOwnerBoxProjects(titles, colors);
    }

    public void createSharedBoxProjects (String[] titles, Color[] colors) {
        projectsView.createSharedBoxProjects(titles, colors);
    }

    public void addOwnerProjectBox(String title, Color color) {
        projectsView.addOwnerProjectBox(title, color);
    }

    public void addSharedProjectBox(String title, Color color) {
        projectsView.addSharedProjectBox(title, color);
    }
}
