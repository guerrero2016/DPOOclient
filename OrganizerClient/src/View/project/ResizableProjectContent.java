package View.project;

public class ResizableProjectContent implements Runnable {

    private ProjectView projectView;

    private ResizableProjectContent() {
        //Never used constructor
    }

    public ResizableProjectContent(ProjectView projectView) {
        this.projectView = projectView;
    }

    @Override
    public void run() {

        while(!projectView.isVisible()) {
            //Waiting until the JFrame is visible
        }

        projectView.resizePanels();

    }

}