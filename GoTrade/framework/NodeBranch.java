package framework;

public abstract class NodeBranch extends Node {

    /**
     * Name of this task. Paint purposes.
     */
    private String name;
    public String getName() {
        return name;
    }

    /**
     * This is supposed to provide info about whether
     * this task was completed in this run or not.
     */
    public abstract boolean isDone();

    /*
     * Left node, chosen when validation is successful
     */
    public abstract Node isTrue();

    /*
     * Right node, chosen when validation is not successful
     */
    public abstract Node isFalse();

    @Override
    public int onLoop() {
        Node left = isTrue();
        Node right = isFalse();
        return validate() ? left.onLoop() : right.onLoop();
    }

}