import framework.NodeBranch;
import org.dreambot.api.methods.MethodProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {

    //List for storing tasks chosen by user
    private ArrayList<NodeBranch> tasks = new ArrayList<>();

    public GUI(ArrayList<NodeBranch> tasks) {
        super("GoTrade - F2P Essentials");
        this.tasks = tasks;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(initComponents());
        setVisible(true);
    }
    private JPanel initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(200,200));
        MethodProvider.log("Initiating GUI components");
        return mainPanel;
    }
}
