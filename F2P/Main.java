import framework.NodeBranch;
import framework.TreeScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import javax.swing.*;
import java.util.ArrayList;

@ScriptManifest(category = Category.MISC, name = "F2P Essentials", author = "m4rr3co", version = 0.1)
public class Main extends TreeScript {
    private static int latency;
    private ArrayList<NodeBranch> tasks = new ArrayList<>();
    @Override
    public void onStart() {
        SwingUtilities.invokeLater(() -> new InitialGUI(tasks));
    }

    @Override
    public NodeBranch getRootBranch() {
        return null;
    }

}
