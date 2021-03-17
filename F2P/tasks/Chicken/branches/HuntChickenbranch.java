package tasks.Chicken.branches;

import framework.Node;
import framework.NodeBranch;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import tasks.Chicken.leaves.HuntChickenleaf;
import tasks.Chicken.leaves.WalkToChickenleaf;

public class HuntChickenbranch extends NodeBranch {

    private final Area chickenArea = new Area(3170,3299,3185,3289,0);

    private final Node huntChickenleaf = new HuntChickenleaf();
    private final Node walkToChickenleaf = new WalkToChickenleaf();

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Node isTrue() {
        return huntChickenleaf;
    }

    @Override
    public Node isFalse() {
        return walkToChickenleaf;
    }

    @Override
    public boolean validate() {
        return chickenArea.contains(Players.localPlayer());
    }
}
