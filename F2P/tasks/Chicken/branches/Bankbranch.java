package tasks.Chicken.branches;

import framework.Node;
import framework.NodeBranch;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import tasks.Chicken.leaves.Bankingleaf;
import tasks.Chicken.leaves.WalkToLumbBankleaf;

public class Bankbranch extends NodeBranch {
    private final Area LUMB_BANK_AREA = new Area(3207,3220,3210,3216,2);

    private final Node bankingleaf = new Bankingleaf();
    private final Node walkToLumbBankleaf = new WalkToLumbBankleaf();

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Node isTrue() {
        return bankingleaf;
    }

    @Override
    public Node isFalse() {
        return walkToLumbBankleaf;
    }

    @Override
    public boolean validate() {
        return LUMB_BANK_AREA.contains(Players.localPlayer());
    }
}
