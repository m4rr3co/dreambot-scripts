import framework.NodeBranch;
import org.dreambot.api.methods.MethodProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InitialGUI extends JFrame {

    //Testing GUI look and feel psvm
    public static void main(String[] args) {
        new InitialGUI(new ArrayList<>());
    }

    //List for storing tasks chosen by user
    private ArrayList<NodeBranch> tasks;

    //List for storing all chosen tasks
    private JList<NodeBranch> chosenTasks;
    //List for storing all implemented tasks
    private JList<NodeBranch> availableTasks;
    /**
     * Populate availableTasks which user can choose from.
     */
    private void setAvailableTasks() {

    }

    /**
     * @param tasks List of tasks to be done in this session.
     */
    public InitialGUI(ArrayList<NodeBranch> tasks) {
        super("GoTrade - F2P Essentials");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.tasks = tasks;
        setAvailableTasks();
        add(initComponents());
        pack();
        setResizable(false);
        setVisible(true);
    }

    /**
     * Populate our GUI that will be shown when onStart() is called.
     * @return JPanel populated with swing components
     */
    private JPanel initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(400,200));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Dimension scrollPaneSize = new Dimension(200,200);

        JScrollPane tasksMenu = new JScrollPane(availableTasks);
        tasksMenu.setBorder(BorderFactory.createTitledBorder("Available Tasks"));
        tasksMenu.setPreferredSize(scrollPaneSize);
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(tasksMenu,c);

        JScrollPane chosenTasksMenu = new JScrollPane(chosenTasks);
        chosenTasksMenu.setBorder(BorderFactory.createTitledBorder("Chosen Tasks"));
        chosenTasksMenu.setPreferredSize(scrollPaneSize);
        c.gridx = 1;
        mainPanel.add(chosenTasksMenu,c);

        return mainPanel;
    }
}
