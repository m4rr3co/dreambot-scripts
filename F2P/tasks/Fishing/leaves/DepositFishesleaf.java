package tasks.Fishing.leaves;

import framework.NodeLeaf;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public class DepositFishesleaf extends NodeLeaf {
    private final int BANK_QUANTITY_MODE_PSETTINGS = 1666;
    private final int BANK_QUANTITY_ALL_VALUE = 16;
    private final Tile draynorBankTile = new Tile(3092,3243,0);
    @Override
    public int onLoop() {
        if (Inventory.isFull()) {
            if (draynorBankTile.distance() <= 3) {
                if (!Bank.isOpen() && Bank.openClosest())
                    MethodProvider.sleepUntil(Bank::isOpen, Calculations.random(3000, 3500));
                else if (PlayerSettings.getConfig(BANK_QUANTITY_MODE_PSETTINGS) != BANK_QUANTITY_ALL_VALUE && Bank.isOpen()) {
                    WidgetChild allButton = Widgets.getMatchingWidget(widgetChild -> widgetChild != null && widgetChild.hasAction("Default quantity: All"));
                    if (allButton != null && allButton.interact())
                        MethodProvider.sleepUntil(() -> PlayerSettings.getConfig(BANK_QUANTITY_MODE_PSETTINGS) == BANK_QUANTITY_ALL_VALUE,Calculations.random(3000,3500));

                }
                else if (Bank.depositAllExcept("Small fishing net"))
                    MethodProvider.sleepUntil(() -> Inventory.fullSlotCount() == 1, Calculations.random(3000, 3500));
            }
            else {
                if (Walking.clickTileOnMinimap(draynorBankTile))
                    MethodProvider.sleepUntil(() -> draynorBankTile.distance() < 3,Calculations.random(5000,6000));
            }
        }
        return Calculations.random(300,500);
    }
}
