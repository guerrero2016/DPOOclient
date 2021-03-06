package View;

import View.project.ProjectsMainView;
import View.user.LogInPanel;
import View.user.SignInPanel;
import model.DataManager;
import View.edition.EditionPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Vista general que conte els altres panells del programa.
 * Te un JMenuBar quan s'inicia sessio amb un boto per a tancar-la.
 * Utilitza CardLayout
 */
public class MainView extends JFrame {

    private static final String IMAGE_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") +
            "img" + System.getProperty("file.separator") + "identifyImage.jpg";

    private final static String PROJECT_TITLE = "Organizer - Project";
    private final static String PROJECT_CONSTRAINT = "Project";
    public final static int PROJECT_ID = 4;

    private JPanel jpLogSign;
    private SignInPanel signInPanel;
    private LogInPanel logInPanel;
    private JButton jbLogout;

    /**
     * Constructor que crea la vista i guarda els diferents panells del programa
     * @param logInPanel Panell per a iniciar sessio
     * @param signInPanel Panell per a registrar-se
     * @param projectsMainView Panell per a seleccionar projectes
     * @param editionPanel Panell per a editar projectes
     */
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

        jpLogSign = new JPanel(new CardLayout());


        jpLogSign.add(logInPanel, "logIn");
        jpLogSign.add(signInPanel, "signIn");
        jpLogSign.setMinimumSize(new Dimension(450, 500));
        jpLogSign.setBackground(Color.BLUE);
        jpIdentifyPanel.add(jpLogSign, BorderLayout.CENTER);

        this.getContentPane().setLayout(new CardLayout());
        this.add(jpIdentifyPanel, "identify");
        this.add(projectsMainView, ProjectsMainView.VIEW_NAME);

        //Edition panel
        add(editionPanel, PROJECT_CONSTRAINT);

        DataManager.getSharedInstance().setWhatPanel(LogInPanel.LOGIN);

        jbLogout = new JButton("Logout");

        super.setMinimumSize(new Dimension(1000,500));
        super.setSize(1200,750);
        super.setTitle("LogIn - Organizer");
        super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        super.setLocationRelativeTo(null);

    }

    /**
     * Procediment que s'encarrega de canviar quin panell s'esta mostrant
     * @param whatPanel Indica quin panell es mostrara
     */
    public void swapPanel(int whatPanel) {
        DataManager.getSharedInstance().setWhatPanel(whatPanel);
        switch (whatPanel) {
            case SignInPanel.SIGN_IN:
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

    /**
     * Procediment que afegeix controladors a la vista i al boto del menu (per tancar sessio)
     * @param wl <code>WindowsListener</code> que controlara la finestra
     * @param al <code>ActionListener</code> que controlara el boto
     */
    public void addController (WindowListener wl, ActionListener al){
        this.addWindowListener(wl);
        jbLogout.addActionListener(al);
    }

    /**
     * Procediment que crea un JOptionPane d'error amb un missatge
     * @param errorMSG Missatge a mostrar al JOptionPane
     */
    public void showErrorDialog(String errorMSG) {
        JOptionPane.showMessageDialog(this, errorMSG,
                "Error", JOptionPane.ERROR_MESSAGE);

    }

}