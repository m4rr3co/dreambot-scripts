import framework.Node;
import framework.NodeBranch;
import framework.TreeScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import javax.swing.*;
import java.util.ArrayList;

@ScriptManifest(category = Category.MISC, name = "GoTrade F2P", author = "m4rr3co", version = 0.1)
public class Main extends TreeScript {
    private ArrayList<NodeBranch> tasks = new ArrayList<>();
    @Override
    public void onStart() {
        SwingUtilities.invokeLater(() -> new GoTradeGUI(tasks));
    }

    @Override
    public NodeBranch getRootBranch() {
        return null;
    }

}
