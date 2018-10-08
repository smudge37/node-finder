import java.util.ArrayList;

import static java.util.Objects.isNull;

public class GridAnalyser {
    private boolean[][] isNodeAt;
    private ArrayList<Node> nodeList = new ArrayList<>();
    private Node[] nodeCache;

    GridAnalyser(boolean[][] nodeGrid) {
        this.isNodeAt = nodeGrid;
        this.nodeCache = new Node[nodeGrid.length];
    }

    ArrayList<Node> analyseGrid() {
        this.locateNodes();
        this.finishOffLowestNodes();
        return this.nodeList;
    }



    private void locateNodes() {
        Node previousNode = null;
        for (int col = 0; col < this.isNodeAt.length; col++) {
            for (int row = 0; row < this.isNodeAt[col].length; row++) {
                if (this.isNodeAt[col][row]) {
                    Node newNode = new Node(col,row);
                    this.nodeList.add(newNode);

                    if (!isNull(previousNode)){
                        this.setDistanceNextRight(previousNode, col, row);
                    }
                    this.checkAbove(newNode);
                    previousNode = newNode;
                }
            }
        }
    }

    private void setDistanceNextRight(Node targetNode, int nextNodeCol, int nextNodeRow) {

        if (targetNode.getRow() == nextNodeRow) {
            targetNode.setDistanceNextRight(nextNodeCol - targetNode.getCol());
        } else {
            targetNode.setDistanceNextRight(0);
        }
    }

    private void checkAbove(Node currentNode) {
        int colNum = currentNode.getCol();
        Node nodeToCheck = this.nodeCache[colNum];

        if (isNull(nodeToCheck)) {
            this.nodeCache[colNum] = currentNode;
        } else {
            nodeToCheck.setDistanceNextBelow(currentNode.getRow() - nodeToCheck.getRow());
            this.nodeCache[colNum] = currentNode;
        }
    }

    private void finishOffLowestNodes() {
        for (Node node: nodeCache) {
            if (!isNull(node)) {
                node.setDistanceNextBelow(0);
            }
        }
    }
}