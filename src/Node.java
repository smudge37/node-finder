public class Node {
    private int row;
    private int col;
    private int distanceNextRight;
    private int distanceNextBelow;

    Node(int colNumber, int rowNumber) {
        this.row = rowNumber;
        this.col = colNumber;
        this.distanceNextRight = 0;
        this.distanceNextBelow = 0;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    int getDistanceNextRight() {
        return distanceNextRight;
    }

    void setDistanceNextRight(int distanceNextRight) {
        this.distanceNextRight = distanceNextRight;
    }

    int getDistanceNextBelow() {
        return distanceNextBelow;
    }

    void setDistanceNextBelow(int distanceNextBelow) {
        this.distanceNextBelow = distanceNextBelow;
    }
}
