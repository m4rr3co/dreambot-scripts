import org.dreambot.api.methods.MethodProvider;

import javax.swing.*;

public class GoTradeGUI extends JFrame {
    public GoTradeGUI() {
        super("GoTrade - F2P Essentials");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        setVisible(true);
    }
    private void initComponents() {
        MethodProvider.log("Initiating GUI components");
    }
}
