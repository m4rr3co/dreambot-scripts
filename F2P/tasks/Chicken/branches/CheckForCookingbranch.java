package tasks.Chicken.branches;

import framework.Node;
import framework.NodeBranch;
import tasks.Chicken.leaves.Cookingleaf;

public class CheckForCookingbranch extends NodeBranch {
    private final Node cookingleaf = new Cookingleaf();
    private final Node huntchicken = new HuntChickenbranch();

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Node isTrue() {
        return cookingleaf;
    }

    @Override
    public Node isFalse() {
        return huntchicken;
    }

    @Override
    public boolean validate() {
        return goCooking;
    }

    private static boolean goCooking = false;
    public static void setGoCooking(boolean goCooking) {
        CheckForCookingbranch.goCooking = goCooking;
    }
}
