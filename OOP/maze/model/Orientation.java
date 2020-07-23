package ubung9.maze.model;

public enum Orientation {
    NORTH(-1,0), EAST(0,1), SOUTH(1,0), WEST(0,-1);

    private int x;
    private int y;
    // Einige Funktionialitäten können auch hier ausgelagert werden, überlegen Sie welche!

    Orientation(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
