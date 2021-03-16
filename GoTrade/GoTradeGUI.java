import org.dreambot.api.methods.MethodProvider;

import javax.swing.*;
import java.awt.*;

public class GoTradeGUI extends JFrame {
    public GoTradeGUI() {
        super("GoTrade - F2P Essentials");
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
