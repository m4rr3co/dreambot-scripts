package tasks.Fishing.leaves;

import framework.NodeLeaf;
import framework.Tools;
import org.dreambot.api.input.Mouse;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.wrappers.interactive.NPC;

public class Fishingleaf extends NodeLeaf {
    private final Tile draynorFishingSpot = new Tile(3087,3229,0);
    private final Tile safeTile = new Tile(3089,3216,0);
    @Override
    public int onLoop() {
        NPC fishingSpot = NPCs.closest("Fishing spot");
        if (draynorFishingSpot.distance() > 10 && Players.localPlayer().getHealthPercent() > 80)
            Tools.walkTo(draynorFishingSpot);
        else if (Players.localPlayer().isInCombat() || Players.localPlayer().getHealthPercent() < 80) {
            if (!Combat.isAutoRetaliateOn()) {
                if (Tabs.isOpen(Tab.COMBAT) && Combat.toggleAutoRetaliate(true))
                    MethodProvider.sleepUntil(Combat::isAutoRetaliateOn, Calculations.random(3000, 3500));
                else if (Tabs.open(Tab.COMBAT))
                    MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.COMBAT),Calculations.random(3000,3500));
            }
            else
                Tools.walkTo(safeTile);
        }
        else if (!Tabs.isOpen(Tab.INVENTORY) && Tabs.openWithMouse(Tab.INVENTORY))
            MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.INVENTORY),Calculations.random(3000,3500));
        else if (fishingSpot != null && Players.localPlayer().getAnimation() == -1)
            if(fishingSpot.interact("Small Net")) {
                MethodProvider.sleepUntil(() -> Players.localPlayer().isMoving(), Calculations.random(2000,3000));
                if(Mouse.getLastCrosshairColorID() == 2)
                    MethodProvider.sleepUntil(() -> Players.localPlayer().getAnimation() == 621,Calculations.random(3000,3500));
            }
        else if (Players.localPlayer().getAnimation() == 621 && Inventory.emptySlotCount() > 5 && Calculations.random(0,101) < 5)
            if (Tabs.open(Tab.SKILLS) && Skills.hoverSkill(Skill.FISHING))
                    MethodProvider.sleep(Calculations.random(3000,5000));
        return Calculations.random(300,500);
    }
}
