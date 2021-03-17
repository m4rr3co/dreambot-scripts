package tasks.Chicken.leaves;

import framework.NodeLeaf;
import framework.Tools;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import tasks.Chicken.branches.CheckForCookingbranch;

import static org.dreambot.api.methods.container.impl.Inventory.contains;
import static org.dreambot.api.methods.container.impl.Inventory.get;
import static org.dreambot.api.methods.interactive.GameObjects.closest;
import static org.dreambot.api.methods.widget.Widgets.getWidgetChild;

public class Cookingleaf extends NodeLeaf {
    final Tile cookingTile = new Tile(3186,3273,0);
    @Override
    public int onLoop() {
        if(!Tabs.isOpen(Tab.INVENTORY))
            Tabs.open(Tab.INVENTORY);
        if (cookingTile.distance() > 1) {
            Tools.walkTo(cookingTile);
        }
        else {
            GameObject pot = closest("Cooking pot");
            WidgetChild cook = getWidgetChild(270,14);
            if (Players.localPlayer().getAnimation() != -1)
                MethodProvider.sleepUntil(() -> Players.localPlayer().getAnimation() == -1,Calculations.random(5000,6000));
            else if (cook != null && cook.isVisible() && cook.interact())
                MethodProvider.sleepUntil(() -> Players.localPlayer().getAnimation() != -1,Calculations.random(3000,3500));
            else if (contains("Raw chicken") && get("Raw chicken").useOn(pot))
                MethodProvider.sleepUntil(() -> getWidgetChild(270,14) != null,Calculations.random(3000,3500));
            else if (!contains("Raw chicken"))
                CheckForCookingbranch.setGoCooking(false);
        }
        return Calculations.random(500,700);
    }
}
