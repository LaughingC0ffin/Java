package ubung9.maze.model;

import java.util.List;

public class Walker {

    private int x;
    private int y;
    private Orientation orientation;
    private Maze maze;

    public Walker(int x, int y, Orientation orientation, Maze maze) throws MazeException {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.maze = maze;
        if(!maze.isAccessible(x,y)) throw new MazeException("...");

    }

    public boolean canWalk(){
        int tmp1 = x+orientation.getX();
        int tmp2 = y+orientation.getY();
        return maze.isAccessible(tmp1,tmp2);
    }

    public void walk() throws MazeException {
        if (!canWalk()) throw new MazeException("...");
        else {
            x=x+orientation.getX();
            y=y+orientation.getY();
        }
    }

    public void turn (TurnDir turnDir){
        List<Orientation> liste =List.of( Orientation.values());

        int index = liste.indexOf(orientation);
        index += turnDir.getX();
        if( index == -1) index = 3;
        else if (index == 4) index =0;
        orientation = liste.get(index);
    }


    public enum TurnDir {
        LEFT(-1),RIGHT(1);

        private int x;

        TurnDir(int x) {
            this.x=x;
        }

        public int getX() {
            return x;
        }
    }
}
