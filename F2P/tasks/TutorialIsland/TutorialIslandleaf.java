package tasks.TutorialIsland;

import framework.NodeLeaf;
import framework.Tools;
import org.dreambot.api.input.Mouse;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.magic.Magic;
import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.prayer.Prayer;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import java.awt.*;
import java.util.List;

import static org.dreambot.api.methods.container.impl.Inventory.get;
import static org.dreambot.api.methods.container.impl.bank.Bank.close;
import static org.dreambot.api.methods.container.impl.bank.Bank.isOpen;
import static org.dreambot.api.methods.dialogues.Dialogues.*;
import static org.dreambot.api.methods.input.Keyboard.type;
import static org.dreambot.api.methods.interactive.GameObjects.closest;
import static org.dreambot.api.methods.prayer.Prayers.flick;
import static org.dreambot.api.methods.settings.PlayerSettings.getConfig;
import static org.dreambot.api.methods.skills.Skills.getBoostedLevels;
import static org.dreambot.api.methods.widget.Widgets.*;

public class TutorialIslandleaf extends NodeLeaf {

    private final int TABCONFIG = 1021;
    private boolean oldtut = true;

    private static boolean flag1 = false;
    private static boolean flag2 = false;
    public static boolean getFlags() {
        return flag1 && flag2;
    }

    final String gielinorGuide = "Gielinor Guide";
    final String survivalExpert = "Survival Expert";
    final String masterChef = "Master Chef";
    final String questGuide = "Quest Guide";
    final String miningInstructor = "Mining Instructor";
    final String combatInstructor = "Combat Instructor";
    final String accountGuide = "Account Guide";
    final String brotherBrace = "Brother Brace";
    final String magicInstructor = "Magic Instructor";
    final String UNHAND = "Unhandled situation.";

    @Override
    public int onLoop() {
        switch (getState()) {
            case "ACTIONS": {
                int NEWTUTPROG = 2686;
                int OLDTUTPROG = 281;
                if (getConfig(NEWTUTPROG) > 0 && oldtut)
                    oldtut = false;
                else if (oldtut) {
                    Tile choppingTile = new Tile (3095,3090,0);
                    Tile cookingExitTile = new Tile (3073,3090,0);
                    Tile questTile = new Tile (3086,3126,0);
                    Tile miningTile = new Tile (3080,9504,0);
                    Tile combatGate = new Tile (3094,9502,0);
                    Tile combatNPCTile = new Tile (3104,9508,0);
                    Tile ladderToBank = new Tile (3111,9525,0);
                    Tile bankTile = new Tile (3122,3123,0);
                    Tile brotherBraceTile = new Tile (3125,3106,0);
                    Tile magicTile = new Tile (3140,3089,0);
                    switch (getConfig(OLDTUTPROG)) {
                        case 0:
                        case 1: {
                            if (getWidget(663) != null && getWidget(663).isVisible()) {
                                if (oldtut)
                                    oldtut = false;
                            }
                            else if (getWidget(558) != null && getWidget(558).isVisible()) {
                                if (getWidgetChild(558, 12).getText().contains("Great!")) {
                                    getWidgetChild(558, 18).interact();
                                    MethodProvider.sleepUntil(() -> getWidgetChild(269, 100) != null, Calculations.random(5000, 6000));
                                } else if (getWidgetChild(558, 14).isVisible()) {
                                    getWidgetChild(558, 14).interact();
                                    MethodProvider.sleepUntil(() -> getWidgetChild(663, 12) != null &&
                                            getWidgetChild(663, 12).getText().contains("Great!"), Calculations.random(5000, 6000));
                                } else if (canEnterInput()) {
                                    if (!flag1)
                                        flag1 = true;
                                    Keyboard.type("asd");
                                    MethodProvider.sleepUntil(() -> getWidgetChild(558, 14) != null &&
                                            getWidgetChild(558, 14).isVisible(), Calculations.random(5000, 6000));
                                } else {
                                    getWidgetChild(558, 17, 0).interact();
                                    MethodProvider.sleepUntil(Dialogues::canEnterInput, Calculations.random(5000, 6000));
                                }
                            }
                            else if (getWidgetChild(679,68,9) != null && getWidgetChild(679,68,9).isVisible()) {
                                if (getWidgetChild(679,68,9).interact())
                                    MethodProvider.sleepUntil(() -> getWidgetChild(679,68,9) == null ||
                                            !getWidgetChild(679,68,9).isVisible(), Calculations.random(3000, 3500));
                            }
                            break; //Case 1
                        }
                        case 3:
                        case 2:
                        case 7: {
                            if (getWidget(558) != null) {
                                    if (getWidgetChild(558, 12).getText().contains("Great!")) {
                                        getWidgetChild(558, 18).interact();
                                        MethodProvider.sleepUntil(() -> getWidgetChild(269, 100) != null, Calculations.random(5000, 6000));
                                    } else if (getWidgetChild(558, 14).isVisible()) {
                                        getWidgetChild(558, 14).interact();
                                        MethodProvider.sleepUntil(() -> getWidgetChild(558, 12).getText().contains("Great!"), Calculations.random(5000, 6000));
                                    } else if (canEnterInput()) {
                                        type("asd");
                                        MethodProvider.sleepUntil(() -> getWidgetChild(558, 14).isVisible(), Calculations.random(5000, 6000));
                                    } else {
                                        getWidgetChild(558, 17, 0).interact();
                                        MethodProvider.sleepUntil(Dialogues::canEnterInput, Calculations.random(5000, 6000));
                                    }
                                }
                            else if (getWidgetChild(269, 100) != null && getWidgetChild(269, 100).interact())
                                MethodProvider.sleepUntil(() -> getWidgetChild(269, 100) == null ||
                                        !getWidgetChild(269, 100).isVisible(), Calculations.random(3000, 3500));
                            else {
                                talkTo(gielinorGuide);
                            }
                            break;
                        }
                        case 10: {
                            GameObject door = closest("Door");
                            if (door != null) {
                                if (door.interact("Open"))
                                    MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 10, Calculations.random(5000, 6000));
                            }
                            break;
                        }
                        case 20:
                        case 30:
                        case 60: {
                            NPC survivalExpertNPC = NPCs.closest(survivalExpert);
                            if (survivalExpertNPC != null && survivalExpertNPC.getTile().distance() > 5)
                                Tools.walkTo(survivalExpertNPC.getTile());
                            else
                                talkTo(survivalExpert);
                            break;
                        }
                        case 40: {
                            NPC fishingSpot = NPCs.closest("Fishing spot");
                            if (fishingSpot != null && fishingSpot.interact("Net"))
                                MethodProvider.sleepUntil(() -> Inventory.contains("Raw shrimps"),Calculations.random(12000,13000));
                            break;
                        }
                        case 70: {
                            if (!Tabs.isOpen(Tab.INVENTORY) && Tabs.open(Tab.INVENTORY))
                                MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.INVENTORY),Calculations.random(3000,3500));
                            else if (choppingTile.distance() > 3)
                                Tools.walkTo(choppingTile);
                            else if (!Inventory.contains("Logs")) {
                                GameObject tree = closest("Tree");
                                if (tree != null && tree.interact("Chop down"))
                                    MethodProvider.sleepUntil(() -> Inventory.contains("Logs"),Calculations.random(15000,16000));
                            }
                            break;
                        }
                        case 80: {
                            Area fireArea = new Area(3091,3093,3097,3090,0);
                            int fmExp = Skills.getExperience(Skill.FIREMAKING);
                            Tile myTile = Players.localPlayer().getTile();
                            GameObject fire = GameObjects.closest(gameObject ->
                                    gameObject.getName().equals("Fire") &&
                                    gameObject.getTile().equals(myTile));
                            if (fire == null) {
                                if (Inventory.contains("Logs") && get("Tinderbox").useOn("Logs"))
                                    MethodProvider.sleepUntil(() -> Skills.getExperience(Skill.FIREMAKING) != fmExp, Calculations.random(15000, 16000));
                            }
                            if (PlayerSettings.getConfig(OLDTUTPROG) == 80) {
                                Tile newTile = fireArea.getRandomTile();
                                Tools.walkTo(newTile);
                            }
                            break;
                        }
                        case 90: {
                            GameObject fire = closest("Fire");
                            if (Inventory.contains("Raw shrimps") && get("Raw shrimps").useOn(fire))
                                MethodProvider.sleepUntil(() -> Inventory.contains("Shrimps"),Calculations.random(15000,16000));
                            break;
                        }
                        case 120: {
                            GameObject gate = closest("Gate");
                            if (gate != null && gate.interact("Open"))
                                MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 120,Calculations.random(5000,6000));
                            break;
                        }
                        case 130: {
                            GameObject door = closest(9709);
                            if (door != null && door.getTile().distance() > 2)
                                Tools.walkTo(door.getTile());
                            else if (door != null && door.interact("Open"))
                                MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 130,Calculations.random(10000,12000));
                            break;
                        }
                        case 140: {
                            talkTo(masterChef);
                            break;
                        }
                        case 150: {
                            if (get("Bucket of water").useOn("Pot of flour"))
                                MethodProvider.sleepUntil(() -> Inventory.contains("Bread dough"),Calculations.random(5000,6000));
                            break;
                        }
                        case 160: {
                            GameObject range = closest("Range");
                            if (range != null && get("Bread dough").useOn(range))
                                MethodProvider.sleepUntil(() -> Inventory.contains("Bread"),Calculations.random(10000,12000));
                            break;
                        }
                        case 170: {
                            GameObject door = closest(9710);
                            if (cookingExitTile.distance() > 3)
                                Tools.walkTo(cookingExitTile);
                            else if (door != null && door.interact("Open"))
                                MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 170,Calculations.random(5000,6000));
                            break;
                        }
                        case 200: {
                            GameObject door = closest(9716);
                            if (door != null && door.getTile().distance() < 3 && door.interact("Open"))
                                MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 200,Calculations.random(10000,12000));
                            else
                                Tools.walkTo(questTile);
                            break;
                        }
                        case 240:
                        case 230:
                        case 220: {
                            talkTo(questGuide);
                            break;
                        }
                        case 250: {
                            GameObject ladder = closest(9726);
                            if (ladder != null && ladder.interact("Climb-down"))
                                MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 250,Calculations.random(5000,6000));
                            break;
                        }
                        case 330:
                        case 260: {
                            if (miningTile.distance() < 10)
                                talkTo(miningInstructor);
                            else
                                Tools.walkTo(miningTile);
                            break;
                        }
                        case 300:
                        case 270: {
                            if (!Tabs.isOpen(Tab.INVENTORY))
                                Tabs.open(Tab.INVENTORY);
                            else {
                                GameObject tin = closest(10080);
                                if (tin != null && tin.interact("Mine"))
                                    MethodProvider.sleepUntil(() -> Inventory.contains("Tin ore"),Calculations.random(10000,12000));
                            }
                            break;
                        }
                        case 310: {
                            if (!Tabs.isOpen(Tab.INVENTORY))
                                Tabs.open(Tab.INVENTORY);
                            else {
                                GameObject copper = closest(10079);
                                if (copper != null && copper.interact("Mine"))
                                    MethodProvider.sleepUntil(() -> Inventory.contains("Copper ore"),Calculations.random(10000,12000));
                            }
                            break;
                        }
                        case 320: {
                            GameObject furnace = closest(10082);
                            if (furnace != null && furnace.interact("Use"))
                                MethodProvider.sleepUntil(() -> Inventory.contains("Bronze bar"),Calculations.random(10000,12000));
                            break;
                        }
                        case 350:
                        case 340: {
                            WidgetChild dagger = getWidgetChild(312,9,0);
                            GameObject anvil = closest(2097);
                            if (dagger != null && dagger.interact())
                                MethodProvider.sleepUntil(() -> Inventory.contains("Bronze dagger"),Calculations.random(10000,12000));
                            else if (anvil != null && anvil.interact("Smith"))
                                MethodProvider.sleepUntil(() -> getWidgetChild(321,9,0) != null &&
                                        getWidgetChild(321,9,0).isVisible(),Calculations.random(10000,12000));
                            break;
                        }
                        case 360: {
                            if (combatGate.distance() > 5)
                                Tools.walkTo(combatGate);
                            else {
                                GameObject gate = closest(9718);
                                if (gate != null && gate.interact("Open"))
                                    MethodProvider.sleepUntil(() -> getConfig(NEWTUTPROG) != 360,Calculations.random(10000,12000));
                            }
                            break;
                        }
                        case 370: {
                            if (combatNPCTile.distance() > 5)
                                Tools.walkTo(combatNPCTile);
                            else
                                talkTo(combatInstructor);
                            break;
                        }
                        case 390: {
                            talkTo(combatInstructor);
                            break;
                        }
                        case 400: {
                            WidgetChild equipStats = getWidgetChild(387,2);
                            if (!Tabs.isOpen(Tab.EQUIPMENT))
                                Tabs.open(Tab.EQUIPMENT);
                            else if (equipStats != null && equipStats.isVisible() && equipStats.interact())
                                MethodProvider.sleepUntil(() -> getWidgetChild(84,0) != null &&
                                        getWidgetChild(84,0).isVisible(),Calculations.random(3000,3500));
                            break;
                        }
                        case 405: {
                            WidgetChild equipment = getWidgetChild(84,0);
                            if (equipment != null && equipment.isVisible() && get("Bronze dagger").interact())
                                MethodProvider.sleepUntil(() -> getConfig(NEWTUTPROG) != 405,Calculations.random(3000,3500));
                            break;
                        }
                        case 410: {
                            WidgetChild equipment = getWidgetChild(84,0);
                            if (equipment != null && equipment.isVisible())
                                Tools.walkTo(combatNPCTile);
                            else
                                talkTo(combatInstructor);
                            break;
                        }
                        case 420: {
                            if (!Inventory.contains("Bronze sword") && org.dreambot.api.methods.container.impl.equipment.Equipment.isSlotEmpty(EquipmentSlot.WEAPON.getRealSlot()))
                                talkTo(combatInstructor);
                            else if (!Tabs.isOpen(Tab.INVENTORY) && Tabs.open(Tab.INVENTORY))
                                MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.INVENTORY),Calculations.random(3000,3500));
                            else if (Inventory.contains("Bronze sword") && get("Bronze sword").interact("Wield"))
                                MethodProvider.sleepUntil(() -> !Inventory.contains("Bronze sword"),Calculations.random(5000,6000));
                            else if (Inventory.contains("Wooden shield") && get("Wooden shield").interact("Wield"))
                                MethodProvider.sleepUntil(() -> !Inventory.contains("Wooden shield"),Calculations.random(5000,6000));
                            break;
                        }
                        case 440: {
                            GameObject gate = closest(9720);
                            NPC rat = NPCs.closest("Giant rat");
                            if (gate != null && rat != null && !Map.canReach(rat) && gate.interact("Open"))
                                MethodProvider.sleepUntil(() -> Map.canReach(rat),Calculations.random(10000,12000));
                            break;
                        }
                        case 460:
                        case 450: {
                            NPC giantRat = NPCs.closest(npc -> npc.getName().equals("Giant rat") &&
                                    !npc.isInCombat());
                            if (Players.localPlayer().isInCombat())
                                MethodProvider.sleepUntil(() -> !Players.localPlayer().isInCombat(),Calculations.random(10000,12000));
                            else if (giantRat != null && giantRat.interact("Attack"))
                                MethodProvider.sleepUntil(() -> Players.localPlayer().isInCombat(),Calculations.random(10000,12000));
                            break;
                        }
                        case 470: {
                            NPC combatInstructorNPC = NPCs.closest(combatInstructor);
                            GameObject gate = closest("Gate");
                            if (combatInstructorNPC != null && !Map.canReach(combatInstructorNPC) &&
                                    gate != null && gate.getTile().distance() > 2)
                                Tools.walkTo(gate.getTile());
                            else if (combatInstructorNPC != null && !Map.canReach(combatInstructorNPC) &&
                                    gate != null && gate.interact("Open"))
                                MethodProvider.sleepUntil(() -> getConfig(NEWTUTPROG) != 470,Calculations.random(5000,6000));
                            else if (combatInstructorNPC != null && combatInstructorNPC.getTile().distance() > 5)
                                Tools.walkTo(combatNPCTile);
                            else
                                talkTo(combatInstructor);
                            break;
                        }
                        case 490:
                        case 480: {
                            if (!Tabs.isOpen(Tab.INVENTORY) && Tabs.open(Tab.INVENTORY))
                                MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.INVENTORY),Calculations.random(3000,3500));
                            else if (Inventory.contains("Shortbow") && get("Shortbow").interact("Wield"))
                                MethodProvider.sleepUntil(() -> Inventory.contains("Wooden shield"),Calculations.random(3000,3500));
                            else if (Inventory.contains("Bronze arrow") && get("Bronze arrow").interact("Wield"))
                                MethodProvider.sleepUntil(() -> !Inventory.contains("Bronze arrow"),Calculations.random(3000,3500));
                            else {
                                NPC giantRat = NPCs.closest("Giant rat");
                                if (Players.localPlayer().isInCombat())
                                    MethodProvider.sleepUntil(() -> !Players.localPlayer().isInCombat(),Calculations.random(10000,12000));
                                else if (combatNPCTile.distance() > 2)
                                    Tools.walkTo(combatNPCTile);
                                else if (giantRat != null && giantRat.interact("Attack"))
                                    MethodProvider.sleepUntil(() -> Players.localPlayer().isInCombat(),Calculations.random(3000,3500));
                            }
                            break;
                        }
                        case 500: {
                            if (ladderToBank.distance() > 3)
                                Tools.walkTo(ladderToBank);
                            else {
                                GameObject ladder = closest(9727);
                                if (ladder != null && ladder.interact("Climb-up"))
                                    MethodProvider.sleepUntil(() -> getConfig(NEWTUTPROG) != 500,Calculations.random(5000,6000));
                            }
                            break;
                        }
                        case 510: {
                            if (bankTile.distance() > 3)
                                Tools.walkTo(bankTile);
                            else if (!isOpen()) {
                                GameObject bankBooth = closest("Bank booth");
                                if (bankBooth != null && bankBooth.interact("Use"))
                                    MethodProvider.sleepUntil(Bank::isOpen, Calculations.random(5000, 6000));
                            }
                            break;
                        }
                        case 520: {
                            if (canContinue() && clickContinue())
                                MethodProvider.sleepUntil(() -> canContinue() ||
                                        getConfig(NEWTUTPROG) != 570,Calculations.random(3000,3500));
                            else if (isOpen() && close())
                                MethodProvider.sleepUntil(() -> !isOpen(),Calculations.random(3000,3500));
                            else {
                                GameObject pollBooth = closest("Poll booth");
                                if (pollBooth != null && pollBooth.interact("Use"))
                                    MethodProvider.sleepUntil(Dialogues::canContinue,Calculations.random(3000,3500));
                            }
                            break;
                        }
                        case 525: {
                            GameObject door = closest(9721);
                            if (door != null && door.getTile().distance() < 2 && door.interact("Open"))
                                MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 525,Calculations.random(5000,6000));
                            else if (door != null)
                                Tools.walkTo(door.getTile());
                            break;
                        }
                        case 532:
                        case 531:
                        case 530: {
                            talkTo(accountGuide);
                            break;
                        }
                        case 540: {
                            GameObject door = closest(9722);
                            if (door != null && door.interact("Open"))
                                MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 540,Calculations.random(5000,6000));
                            break;
                        }
                        case 560:
                        case 550: {
                            if (brotherBraceTile.distance() > 10)
                                Tools.walkTo(brotherBraceTile);
                            else {
                                talkTo(brotherBrace);
                            }
                            break;
                        }
                        case 570: {
                            NPC brotherBraceNPC = NPCs.closest(brotherBrace);
                            if (canContinue() && clickContinue())
                                MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 570 ||
                                        canContinue(),Calculations.random(5000,6000));
                            else if (getBoostedLevels(Skill.PRAYER) < 1) {
                                GameObject altar = closest("Altar");
                                if (altar != null && altar.interact("Pray-at"))
                                    MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 570,Calculations.random(5000,6000));
                            }
                            else if (brotherBraceNPC != null && Tabs.isOpen(Tab.INVENTORY))
                                talkTo(brotherBrace);
                            else if(getBoostedLevels(Skill.PRAYER) > 0 && flick(Prayer.THICK_SKIN,Calculations.random(5000,6000)) &&
                            Tabs.open(Tab.INVENTORY)) {
                                MethodProvider.sleepUntil(() -> Tabs.isOpen(Tab.INVENTORY), Calculations.random(3000, 3500));
                            }
                            break;
                        }
                        case 580: {
                            talkTo(brotherBrace);
                            break;
                        }
                        case 610:
                        case 600: {
                            List<WidgetChild> finalInstructor =
                                    getWidgetChildrenContainingText("Your final instructor!");
                            if (finalInstructor != null && finalInstructor.size() > 0 && finalInstructor.get(0).isVisible()) {
                                GameObject door = closest(9723);
                                if (door != null && door.interact("Open"))
                                    MethodProvider.sleepUntil(() -> getConfig(OLDTUTPROG) != 600,Calculations.random(5000,6000));
                            } else
                            talkTo(brotherBrace);
                            break;
                        }
                        case 640:
                        case 620: {
                            if (magicTile.distance() > 5) {
                                Tools.walkTo(magicTile);
                                if (!flag2)
                                    flag2 = true;
                            }
                            else {
                                NPC magicNPC = NPCs.closest(magicInstructor);
                                if (magicNPC != null)
                                    talkTo(magicInstructor);
                            }
                            break;
                        }
                        case 650: {
                            NPC chicken = NPCs.closest("Chicken");
                            if (chicken != null) {
                                Magic.castSpellOn(Normal.WIND_STRIKE, chicken);
                                MethodProvider.sleepUntil(() -> getConfig(NEWTUTPROG) != 650, Calculations.random(10000, 12000));
                            }
                            break;
                        }
                        case 670: {
                            if (Magic.isSpellSelected())
                                Mouse.click(Players.localPlayer().getCenterPoint());
                            else
                                talkTo(magicInstructor);
                            break;
                        }
                        case 1000: {
                            MethodProvider.sleep(Calculations.random(5000, 6000));
                            break;
                        }
                        default: {
                            MethodProvider.log(UNHAND);
                            break;
                        }
                    }
                }
                break;
            }
            case "OPENTAB": {
                Tab t = getTab();
                if (t == null) {
                    MethodProvider.log("Player setting at TABCONFIG = " + getConfig(TABCONFIG));
                    MethodProvider.log(UNHAND);
                } else if (Tabs.openWithMouse(t)) {
                    MethodProvider.sleepUntil(() -> Tabs.isOpen(t), Calculations.random(5000, 6000));
                    MethodProvider.log("Tab opened: " + t.toString());
                }
                break;
            }
        }
        return Calculations.random(300,500); // Sleep for onLoop function
    }
    private String getState() {
      if (getConfig(TABCONFIG) > 2048 && getOptions() == null && getTab() != null) {
          return "OPENTAB";
      } else
          return "ACTIONS";
    }
    private Tab getTab(){
        int conf = getConfig(TABCONFIG) - 2048;
        switch(conf){
            case 1:
                return Tab.COMBAT;
            case 2:
                return Tab.SKILLS;
            case 3:
                return Tab.QUEST;
            case 4:
                return Tab.INVENTORY;
            case 5:
                return Tab.EQUIPMENT;
            case 6:
                return Tab.PRAYER;
            case 7:
                return Tab.MAGIC;
            case 8:
                return Tab.CLAN;
            case 9:
                return Tab.ACCOUNT_MANAGEMENT;
            case 10:
                return Tab.FRIENDS;
            case 11:
                return Tab.LOGOUT;
            case 12: {
                Mouse.move(new Point(683,487));
                Mouse.click(new Point(683,487));
                MethodProvider.sleep(Calculations.random(1000,1500));
                Mouse.click(new Point(683,487));
                return Tab.OPTIONS;
            }
            case 13:
                return Tab.EMOTES;
            case 14:
                return Tab.MUSIC;
            default:
                return null;
        }
    }
    private void talkTo(String npcName) {
        List<WidgetChild> clickToContinue = getWidgetChildrenContainingText("Click to continue.");
        List<WidgetChild> clickHereToContinue = getWidgetChildrenContainingText("Click here to continue");
        List<WidgetChild> pleaseWait = getWidgetChildrenContainingText("Please wait...");
        if (getOptions() != null && getOptions().length > 0) {
            String[] options = getOptions();
            String[] talkOptions = { "Yes.","I am an experienced player.","No, I'm not planning to do that.",
            "Nope, I'm ready to move on!"};
            for (String option : talkOptions) {
                for (String opt : options)
                    if (opt.equals(option))
                        if (clickOption(opt))
                            MethodProvider.sleepUntil(() -> canContinue() ||
                                    !inDialogue(), Calculations.random(3000, 3500));
            }
        }
        else if (canContinue()) {
            if (spaceToContinue())
                MethodProvider.sleepUntil(() -> canContinue() ||
                        getOptions() != null,Calculations.random(3000,3500));
        } else if (clickToContinue != null && !clickToContinue.isEmpty()) {
            if (clickToContinue.get(0).interact())
                MethodProvider.sleepUntil(() -> canContinue() ||
                        !inDialogue(),Calculations.random(3000,3500));
        } else if (clickHereToContinue != null && !clickHereToContinue.isEmpty()) {
            if (clickHereToContinue.get(0).interact())
                MethodProvider.sleepUntil(() -> canContinue() ||
                        !inDialogue(), Calculations.random(3000, 3500));
        }
        else if (pleaseWait != null && !pleaseWait.isEmpty())
            MethodProvider.sleepUntil(() -> canContinue() ||
                    !inDialogue(), Calculations.random(2000,2500));
        else {
            NPC npc = NPCs.closest(npcName);
            if (npc != null && npc.interact("Talk-to"))
                    MethodProvider.sleepUntil(() -> inDialogue() ||
                            canContinue(),Calculations.random(3000,3500));
        }
    }
}