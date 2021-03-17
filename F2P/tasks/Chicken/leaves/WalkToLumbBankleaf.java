package tasks.Chicken.leaves;


import framework.NodeLeaf;
import framework.Tools;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.interactive.GameObjects.closest;
import static org.dreambot.api.methods.walking.impl.Walking.walk;

public class WalkToLumbBankleaf extends NodeLeaf {

    private final Tile staircasetile = new Tile(3206,3209,0);
    private final Area lumbBankArea = new Area(3207,3220,3210,3216,2);

    @Override
    public int onLoop() {
        switch(Players.localPlayer().getZ()){
            case 0: {
                GameObject staircase = closest(gameObject ->
                        gameObject.getName().equals("Staircase"));
                if (staircasetile.distance() > 5) {
                    Tools.walkTo(staircasetile);
                } else {
                        int z = Players.localPlayer().getZ();
                        staircase.interact("Climb-up");
                        MethodProvider.sleepUntil(() -> Players.localPlayer().getZ() != z,Calculations.random(5000,6000));
                    }
                break;
            }
            case 1: {
                GameObject staircase = closest(gameObject ->
                        gameObject.getName().equals("Staircase") &&
                                gameObject.hasAction("Climb-up"));
                if(staircase != null){
                    if(staircase.interact("Climb-up")){
                        MethodProvider.sleepUntil(() -> Players.localPlayer().getZ() == 2,Calculations.random(5000,6000));
                    }
                }

                break;
            }
            case 2: {
                if(walk(lumbBankArea.getCenter())){
                    MethodProvider.sleepUntil(() -> lumbBankArea.contains(Players.localPlayer()),Calculations.random(5000,6000));
                }
            }
        }
        return Calculations.random(500,700);
    }
}
