package tasks.Fishing;

import framework.Node;
import framework.NodeBranch;
import org.dreambot.api.methods.container.impl.Inventory;
import tasks.Fishing.branches.FishingNetbranch;
import tasks.Fishing.branches.GoFishingbranch;

public class Fishingbranch extends NodeBranch {
    private final Node goFishingbranch = new GoFishingbranch();
    private final Node fishingNetbranch = new FishingNetbranch();
    @Override
    public boolean validate() {
        return Inventory.contains("Small fishing net");
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Node isTrue() {
        return goFishingbranch;
    }

    @Override
    public Node isFalse() {
        return fishingNetbranch;
    }
}
