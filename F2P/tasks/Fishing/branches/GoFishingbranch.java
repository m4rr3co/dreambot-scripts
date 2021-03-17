package tasks.Fishing.branches;

import framework.Node;
import framework.NodeBranch;
import org.dreambot.api.methods.container.impl.Inventory;
import tasks.Fishing.leaves.DepositFishesleaf;
import tasks.Fishing.leaves.Fishingleaf;

public class GoFishingbranch extends NodeBranch {
    private final Node deposit = new DepositFishesleaf();
    private final Node fishing = new Fishingleaf();
    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Node isTrue() {
        return deposit;
    }

    @Override
    public Node isFalse() {
        return fishing;
    }
}
