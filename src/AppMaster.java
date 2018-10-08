import java.util.ArrayList;

public class AppMaster {
    private Config config;
    private GridAnalyser ga;
    private boolean[][] nodeGrid;
    private ArrayList<Node> nodeList;

    AppMaster(Config config) {
        this.config = config;
        this.setup();
    }

    void run() {
        this.nodeList = this.getNodes();
        this.output();
    }

    private void setup() {
        this.nodeGrid = new boolean[this.config.gridWidth][this.config.gridHeight];
        for (int[] node: this.config.nodePositions) {

            if (node.length != 2) {
                System.out.println("The set of nodes has not been set up properly.");
                System.out.println("Each entry in the array GridSetup.NODE_POSITIONS " +
                        "must be an integer array with 2 entries.");
                System.exit(-1);
            }

            try {
                this.nodeGrid[node[0]][node[1]] = true;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("The set of nodes has not been set up properly.");
                System.out.println("All nodes must have column number < GRID_WIDTH and row number < GRID_HEIGHT.");
                System.exit(-1);
            }
        }
        this.ga = new GridAnalyser(this.nodeGrid);
    }

    ArrayList<Node> getNodes() {
        return this.ga.analyseGrid();
    }

    private void output() {
        System.out.println("The following nodes were found:");
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.println();
            Node node = nodeList.get(i);
            System.out.println("Node " + (i + 1));
            System.out.println("Position: (" + node.getCol() + "," + node.getRow() + ")");

            int distanceNextRight = node.getDistanceNextRight();
            int distanceNextBelow = node.getDistanceNextBelow();

            if (distanceNextRight == 0) {
                System.out.println("Distance to next node to the right: N/A");
            } else {
                System.out.println("Distance to next node to the right: " + distanceNextRight);
            }
            if (distanceNextBelow == 0) {
                System.out.println("Distance to next node downwards: N/A");
            } else {
                System.out.println("Distance to next node downwards: " + distanceNextBelow);
            }
        }
    }
}
