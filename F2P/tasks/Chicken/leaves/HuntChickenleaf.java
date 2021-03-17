package tasks.Chicken.leaves;

import framework.NodeLeaf;
import framework.Tools;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import tasks.Chicken.branches.CheckForCookingbranch;


public class HuntChickenleaf extends NodeLeaf {
    @Override
    public int onLoop() {
        if (Bank.isOpen() && Bank.close())
            MethodProvider.sleepUntil(() -> !Bank.isOpen(),Calculations.random(3000,3500));
        else if (Inventory.count("Raw chicken") >= 8)
            CheckForCookingbranch.setGoCooking(true);
        else if(!Tabs.isOpen(Tab.INVENTORY)){
            Tabs.open(Tab.INVENTORY);
            MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.INVENTORY),Calculations.random(3000,3500));
        }
        else if (Inventory.contains("Burnt chicken") && (Inventory.dropAll("Burnt chicken")))
                MethodProvider.sleepUntil(() -> Inventory.count("Burnt chicken") == 0,Calculations.random(1000,1500));
        else if (!Players.localPlayer().isInCombat()) {
            NPC chicken = org.dreambot.api.methods.interactive.NPCs.closest(npc -> (!npc.isInCombat() &&
                    Map.canReach(npc) &&
                    npc.getName().equals("Chicken") &&
                    !npc.isInteractedWith() &&
                    npc.getTile().distance() < 3));
            if (chicken!= null && chicken.interact("Attack")) {
                int waitingFactor = (int) Math.round(chicken.getTile().distance());
                if (waitingFactor < 1)
                    waitingFactor = 1;
                MethodProvider.sleepUntil(() -> Players.localPlayer().isInCombat(), (long) waitingFactor * Calculations.random(600, 650));
                Tools.changeAttackMode();
            }
        }
        else {
            GroundItem loot = GroundItems.closest(item -> item != null &&
                    (item.getName().equals("Feather") || item.getName().equals("Raw chicken")) &&
                    item.getTile().distance() < 3);
            if (loot != null && Calculations.random(0,101) < 15) {
                loot.interact("Take");
                MethodProvider.sleepUntil(() -> {
                    GroundItem gi1 = GroundItems.closest(groundItem ->
                            !groundItem.getTile().equals(loot.getTile()) ||
                                    !groundItem.getName().equals(loot.getName()) ||
                                    groundItem.getID() != loot.getID());
                    return gi1 == null;
                },Calculations.random(1500,2000));
            }
            else if (loot != null && loot.getTile().distance() < 1) {
                loot.interact("Take");
                MethodProvider.sleepUntil(() -> {
                    GroundItem gi1 = GroundItems.closest(groundItem -> !groundItem.getTile().equals(loot.getTile()) ||
                            !groundItem.getName().equals(loot.getName()) ||
                            groundItem.getID() != loot.getID());
                    return gi1 == null;
                },Calculations.random(1500,2000));
            }
            MethodProvider.sleepUntil(() -> Players.localPlayer().getCharacterInteractingWithMe() != null &&
                    Players.localPlayer().getHealthPercent() == 0, Calculations.random(3000, 4000));
        }
        return Calculations.random(300,500);
    }

}
