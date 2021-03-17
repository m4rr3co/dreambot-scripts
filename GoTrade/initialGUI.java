import framework.NodeBranch;
import org.dreambot.api.methods.MethodProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class initialGUI extends JFrame {

    //List for storing tasks chosen by user
    private ArrayList<NodeBranch> tasks;
    //List for storing all implemented tasks
    private ArrayList<NodeBranch> availableTasks;

    /**
     * @param tasks List of tasks to be done in this session
     */
    public initialGUI(ArrayList<NodeBranch> tasks) {
        super("GoTrade - F2P Essentials");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.tasks = tasks;
        setAvailableTasks();
        add(initComponents());
        setVisible(true);
    }

    /**
     * Populate our GUI that will be shown when onStart() is called.
     * @return JPanel populated with swing components
     */
    private JPanel initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(200,200));
        MethodProvider.log("Initiating GUI components");
        return mainPanel;
    }
    private void setAvailableTasks() {

    }
}
