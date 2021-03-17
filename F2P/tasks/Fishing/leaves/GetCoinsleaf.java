package tasks.Fishing.leaves;

import framework.NodeLeaf;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;

public class GetCoinsleaf extends NodeLeaf {
    @Override
    public int onLoop() {
        if (Bank.isOpen() && Bank.withdraw("Coins",500))
            MethodProvider.sleepUntil(() -> Inventory.count("Coins") >= 500,Calculations.random(3000,3500));
        else if (Bank.openClosest())
            MethodProvider.sleepUntil(Bank::isOpen,Calculations.random(3000,3500));
        return Calculations.random(300,500);
    }
}
