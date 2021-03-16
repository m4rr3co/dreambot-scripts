package framework;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.script.impl.TaskScript;

public class Tools extends TaskScript {
    public static void walkTo(Tile destination) {
        int runEnabled;
        if (Walking.isRunEnabled())
            runEnabled = 1;
        else
            runEnabled = 2;
        if (destination.distance() > 10) {
            Walking.walk(destination);
            MethodProvider.sleepUntil(() -> Players.localPlayer().isMoving(), Calculations.random(1000, 1500));
            Tile dest = org.dreambot.api.Client.getDestination();
            if (dest != null) {
                MethodProvider.sleepUntil(() -> dest.distance() < Calculations.random(3, 7), (long) runEnabled * Calculations.random(3000, 3500));
            } else {
                    MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving(), (long) runEnabled * Calculations.random(3000, 3500));
            }
        }
        else {
            Walking.walk(destination);
            MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving(), (long) runEnabled *Calculations.random(3000,3500));
        }
    }
    public static void changeAttackMode() {
        int atk = Skills.getRealLevel(Skill.ATTACK);
        int str = Skills.getRealLevel(Skill.STRENGTH);
        int def = Skills.getRealLevel(Skill.DEFENCE);
        if (atk%5 != 0 || atk <= def) {
            if (PlayerSettings.getConfig(43) != 0) {
                if (!Tabs.isOpen(Tab.COMBAT)) {
                    Tabs.open(Tab.COMBAT);
                    MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.COMBAT), Calculations.random(1000, 1200));
                }
                Widgets.getWidgetChild(593, 5).interact();
            }
        }
        else if (str%5 != 0 || str != atk) {
            if (PlayerSettings.getConfig(43) != 1) {
                if (!Tabs.isOpen(Tab.COMBAT)) {
                    Tabs.open(Tab.COMBAT);
                    MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.COMBAT), Calculations.random(1000, 1200));
                }
                Widgets.getWidgetChild(593, 9).interact();
            }
        }
        else {
            if (PlayerSettings.getConfig(43) != 3) {
                if (!Tabs.isOpen(Tab.COMBAT)) {
                    Tabs.open(Tab.COMBAT);
                    MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.COMBAT), Calculations.random(1000, 1200));
                }
                Widgets.getWidgetChild(593, 17).interact();
            }
        }
    }
}
