package tasks.TutorialIsland.branches;

import framework.Node;
import framework.NodeBranch;
import org.dreambot.api.methods.settings.PlayerSettings;
import tasks.TutorialIsland.TutorialIslandleaf;

public class TutorialIslandbranch extends NodeBranch {
    private final Node tutorialIslandleaf = new TutorialIslandleaf();

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Node isTrue() {
        return tutorialIslandleaf;
    }

    @Override
    public Node isFalse() {
        return null;
    }

    @Override
    public boolean validate() {
        return PlayerSettings.getConfig(281) != 1000;
    }
}
