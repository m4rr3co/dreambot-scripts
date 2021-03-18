package tasks.Fishing;

import framework.NodeLeaf;
import framework.Tools;

public class FishingNetleaf extends NodeLeaf {
    @Override
    public int onLoop() {
        return Tools.getLatency();
    }
}
