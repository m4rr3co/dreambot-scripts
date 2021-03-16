import framework.NodeBranch;
import framework.TreeScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import javax.swing.*;

@ScriptManifest(category = Category.MISC, name = "GoTrade F2P", author = "m4rr3co", version = 0.1)
public class Main extends TreeScript {
    @Override
    public void onStart() {
        SwingUtilities.invokeLater(() ->{

        });
    }

    @Override
    public NodeBranch getRootBranch() {
        return null;
    }

}
