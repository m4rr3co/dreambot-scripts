package tasks.Fishing.leaves;

import framework.NodeLeaf;
import framework.Tools;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.grandexchange.GrandExchange;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.web.node.impl.bank.WebBankArea;

public class BuyNetleaf extends NodeLeaf {
    private final Area GE_AREA = WebBankArea.GRAND_EXCHANGE.getArea();
    @Override
    public int onLoop() {
        if (!GE_AREA.contains(Players.localPlayer()))
            Tools.walkTo(GE_AREA.getCenter());
        else if (Bank.isOpen() && Bank.close())
            MethodProvider.sleepUntil(() -> !Bank.isOpen(),Calculations.random(3000,3500));
        else if (!GrandExchange.isOpen() && GrandExchange.open())
            MethodProvider.sleepUntil(GrandExchange::isOpen,Calculations.random(3000,3500));
        else if (GrandExchange.isReadyToCollect() && GrandExchange.collect())
            MethodProvider.sleepUntil(() -> Inventory.contains("Small fishing net"),Calculations.random(3000,3500));
        else if (Inventory.count("Coins") >= 500 && GrandExchange.buyItem("Small fishing net",1,500))
            MethodProvider.sleepUntil(GrandExchange::isReadyToCollect,Calculations.random(10000,15000));
        return Calculations.random(300,500);
    }
}
