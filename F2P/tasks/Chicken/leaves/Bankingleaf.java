package tasks.Chicken.leaves;

import framework.NodeLeaf;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;

public class Bankingleaf extends NodeLeaf {
    @Override
    public int onLoop() {
        if(!Bank.isOpen() && Bank.openClosest())
            MethodProvider.sleepUntil(Bank::isOpen,Calculations.random(5000,6000));
        else if (!Inventory.isEmpty() && Bank.depositAllItems())
            MethodProvider.sleepUntil(Inventory::isEmpty,Calculations.random(2000,3000));
        else if(Bank.isOpen() && Bank.close())
            MethodProvider.sleepUntil(() -> !Bank.isOpen(),Calculations.random(2000,3000));

        return Calculations.random(500,700);
    }
}
