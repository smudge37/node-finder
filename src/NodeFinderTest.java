import org.junit.Test;
import java.util.ArrayList;

public class NodeFinderTest {

    @Test
    public void nodesFoundShouldMatchSetupNodes() {
        int[][] nodePositions = {
                {1,1},
                {3,1},
                {19,25},
                {6,5},
                {2,7}
        };
        Config config = new Config(20, 30, nodePositions);
        AppMaster master = new AppMaster(config);

        ArrayList<Node> nodesFound = master.getNodes();

        assert(nodesMatch(nodesFound, nodePositions));
    }

    @Test
    public void nodesFoundShouldHaveNeighbouringNodesAsClaimed() {
        int[][] nodePositions = {
                {1,1},
                {3,1},
                {19,25},
                {6,5},
                {2,7}
        };
        Config config = new Config(20, 30, nodePositions);
        AppMaster master = new AppMaster(config);

        ArrayList<Node> nodesFound = master.getNodes();

        assert(neighboursAsClaimed(nodesFound));
    }

    private boolean nodesMatch(ArrayList<Node> nodesFound, int[][] setupNodes) {
        for (int[] setupNode: setupNodes) {
            boolean noMatchFound = true;
            for (Node nodeFound: nodesFound) {
                if (nodeFound.getCol() == setupNode[0] && nodeFound.getRow() == setupNode[1]) {
                    noMatchFound = false;
                }
            }
            if (noMatchFound) {
                return false;
            }
        }
        return true;
    }

    private boolean neighboursAsClaimed(ArrayList<Node> nodesFound) {
        boolean asClaimed = true;
        for (Node nodeToTest: nodesFound) {
            if (distanceRightWrong(nodesFound, nodeToTest) || distanceBelowWrong(nodesFound, nodeToTest)) {
                asClaimed = false;
            }
        }
        return asClaimed;
    }

    private boolean distanceRightWrong(ArrayList<Node> nodesFound, Node nodeToTest) {
        int distanceNextRight = nodeToTest.getDistanceNextRight();
        if (distanceNextRight != 0) {
            boolean neighbourFoundAsClaimed = false;
            for (Node node: nodesFound) {
                if (node.getRow() == nodeToTest.getRow() &&
                        node.getCol() == nodeToTest.getCol() + distanceNextRight) {
                    neighbourFoundAsClaimed = true;
                }
            }
            return !neighbourFoundAsClaimed;
        } else {
            return false;
        }
    }

    private boolean distanceBelowWrong(ArrayList<Node> nodesFound, Node nodeToTest) {
        int distanceNextBelow = nodeToTest.getDistanceNextBelow();
        if (distanceNextBelow != 0) {
            boolean neighbourFoundAsClaimed = false;
            for (Node node: nodesFound) {
                if (node.getRow() == nodeToTest.getRow() + distanceNextBelow &&
                        node.getCol() == nodeToTest.getCol()) {
                    neighbourFoundAsClaimed = true;
                }
            }
            return !neighbourFoundAsClaimed;
        } else {
            return false;
        }
    }

}
