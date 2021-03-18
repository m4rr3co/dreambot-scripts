package tasks.Fishing;

import framework.Node;
import framework.NodeBranch;
import org.dreambot.api.methods.container.impl.Inventory;

public class Fishingbranch extends NodeBranch {
    private final long endOfTheTask;
    public Fishingbranch(long endOfTheTask) {
        this.endOfTheTask = endOfTheTask;
    }
    @Override
    public boolean isDone() {
        return System.currentTimeMillis() >= endOfTheTask;
    }

    private final Node fishingLeaf = new Fishingleaf();
    private final Node fishingNetleaf = new FishingNetleaf();
    @Override
    public boolean validate() {
        return Inventory.contains("Small fishing net");
    }



    @Override
    public Node isTrue() {
        return fishingLeaf;
    }

    @Override
    public Node isFalse() {
        return fishingNetleaf;
    }
}
