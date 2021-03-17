import framework.NodeBranch;

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
        super("F2P Essentials");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.tasks = tasks;
        setAvailableTasks();
        setJMenuBar(initMenu());
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
        mainPanel.setBackground(Color.WHITE);

        mainPanel.setPreferredSize(new Dimension(450,300));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Dimension scrollPaneSize = new Dimension(200,200);

        JScrollPane tasksMenu = new JScrollPane(availableTasks);
        tasksMenu.setBorder(BorderFactory.createTitledBorder("Available Tasks"));
        tasksMenu.setPreferredSize(scrollPaneSize);
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(tasksMenu,c);

        JPanel addRemoveButtons = new JPanel();
        addRemoveButtons.setBackground(Color.WHITE);
        addRemoveButtons.setPreferredSize(new Dimension(50,80));
        addRemoveButtons.setLayout(new BoxLayout(addRemoveButtons,BoxLayout.PAGE_AXIS));
        JButton addButton = new JButton(">");
        JButton removeButton = new JButton("<");
        addRemoveButtons.add(addButton);
        addRemoveButtons.add(removeButton);
        c.gridx = 1;
        mainPanel.add(addRemoveButtons,c);

        JScrollPane chosenTasksMenu = new JScrollPane(chosenTasks);
        chosenTasksMenu.setBorder(BorderFactory.createTitledBorder("Chosen Tasks"));
        chosenTasksMenu.setPreferredSize(scrollPaneSize);
        c.gridx = 2;
        mainPanel.add(chosenTasksMenu,c);

        JLabel tasksInfo = new JLabel("<html>Some info goes here <br> more info<br> more info<br> more info<br> more info<br> more info<br> more info </html>");
        JScrollPane tasksInfoPane = new JScrollPane(tasksInfo);
        tasksInfoPane.setBorder(BorderFactory.createTitledBorder("Tasks Info"));
        tasksInfoPane.setPreferredSize(new Dimension(250,100));
        c.gridwidth = 2;
        c.gridy = 1;
        c.gridx = 1;
        mainPanel.add(tasksInfoPane,c);

        JButton startButton = new JButton("START SCRIPT");
        startButton.setPreferredSize(new Dimension(150,80));
        c.gridwidth = 1;
        c.gridx = 0;
        mainPanel.add(startButton,c);

        return mainPanel;
    }

    /**
     * Menu bar for saving/loading tasks.
     * @return JMenuBar
     */
    private JMenuBar initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu taskManager = new JMenu("File");
        menuBar.add(taskManager);

        JMenuItem saveTasks = new JMenuItem("Save Tasks");
        JMenuItem loadTasks = new JMenuItem("Load Tasks");
        taskManager.add(saveTasks);
        taskManager.add(loadTasks);

        return menuBar;
    }
}
