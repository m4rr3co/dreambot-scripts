package tasks.Chicken;

import framework.Node;
import framework.NodeBranch;
import org.dreambot.api.methods.container.impl.Inventory;
import tasks.Chicken.branches.Bankbranch;
import tasks.Chicken.branches.CheckForCookingbranch;


public class ChickenRootbranch extends NodeBranch {

    private final Node bankbranch = new Bankbranch();
    private final Node checkForCooking = new CheckForCookingbranch();

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Node isTrue() {
        return bankbranch;
    }

    @Override
    public Node isFalse() {
        return checkForCooking;
    }

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }
}