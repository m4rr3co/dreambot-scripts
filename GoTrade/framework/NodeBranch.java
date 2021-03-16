package framework;

public abstract class NodeBranch extends Node {

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