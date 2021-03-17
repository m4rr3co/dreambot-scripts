import framework.NodeBranch;
import sun.tools.jstat.Alignment;
import tasks.Chicken.ChickenRootbranch;
import tasks.Fishing.Fishingbranch;

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

    /**
     * @param tasks List of tasks to be done in this session.
     */
    public InitialGUI(ArrayList<NodeBranch> tasks) {
        super("F2P Essentials");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.tasks = tasks;
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
        DefaultListModel<String> tasksListModel = new DefaultListModel<>();
        setTasks(tasksListModel);
        JList<String> availableTasksJList = new JList<>(tasksListModel);

        JScrollPane tasksMenu = new JScrollPane(availableTasksJList);
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

        JList<String> chosenTasks = new JList<>(new DefaultListModel<>());
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

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBackground(Color.WHITE);
        startButtonPanel.setLayout(new BoxLayout(startButtonPanel,BoxLayout.PAGE_AXIS));
        JButton startButton = new JButton("START");
        JLabel startErrors = new JLabel("Status: Waiting.");
        startErrors.setPreferredSize(new Dimension(100,50));
        startButton.setPreferredSize(startErrors.getPreferredSize());
        startErrors.setHorizontalAlignment(SwingConstants.CENTER);
        startButtonPanel.add(startButton);
        startButtonPanel.add(startErrors);
        c.gridwidth = 1;
        c.gridx = 0;
        mainPanel.add(startButtonPanel,c);

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

    private void setTasks(DefaultListModel<String> list) {
        list.addElement("Melee Chickens");
        list.addElement("Range Chickens");
        list.addElement("Lumbridge Fishing");
        list.addElement("Lumbridge Woodcutting");
    }
}
