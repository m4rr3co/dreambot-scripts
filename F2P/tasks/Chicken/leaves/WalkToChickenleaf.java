package tasks.Chicken.leaves;

import framework.NodeLeaf;
import framework.Tools;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;

import static org.dreambot.api.methods.container.impl.bank.Bank.close;
import static org.dreambot.api.methods.container.impl.bank.Bank.isOpen;
import static org.dreambot.api.methods.interactive.GameObjects.closest;

public class WalkToChickenleaf extends NodeLeaf {
    private final Area chickenArea = new Area(3170,3299,3185,3289,0);
    @Override
    public int onLoop() {
        if(isOpen())
            close();

        switch(Players.localPlayer().getZ()){
            case 0:{
                Tools.walkTo(chickenArea.getCenter());
                break;
            }
            case 1:
            case 2:{
                GameObject staircase = closest(gameObject ->
                        gameObject.getName().equals("Staircase") &&
                        gameObject.hasAction("Climb-down"));
                if(staircase != null){
                    int z = Players.localPlayer().getZ();
                    if(staircase.interact("Climb-down")){
                        MethodProvider.sleepUntil(() -> Players.localPlayer().getZ() != z,Calculations.random(5000,6000));
                    }
                }
            }
        }
        return Calculations.random(600,800);
    }
}
