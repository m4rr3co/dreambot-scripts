package tasks.Fishing.branches;

import framework.Node;
import framework.NodeBranch;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.grandexchange.GrandExchange;
import tasks.Fishing.leaves.BuyNetleaf;
import tasks.Fishing.leaves.GetCoinsleaf;

public class FishingNetbranch extends NodeBranch {
    private final Node buyNet = new BuyNetleaf();
    private final Node getCoins = new GetCoinsleaf();
    @Override
    public boolean validate() {
        return Inventory.count("Coins") >= 500 || GrandExchange.isReadyToCollect();
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Node isTrue() {
        return buyNet;
    }

    @Override
    public Node isFalse() {
        return getCoins;
    }
}
