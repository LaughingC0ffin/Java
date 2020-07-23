package ubung9.maze.model;

public class SimpleMaze implements Maze {

    private boolean [][] feld;

    public SimpleMaze(boolean[][] feld){
        this.feld = feld;
    }

    @Override
    public boolean isAccessible(int x, int y) {
        return feld[x][y];
    }



}
