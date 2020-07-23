package ubung9.maze.model.stmt;

import ubung9.maze.model.MazeException;
import ubung9.maze.model.Walker.TurnDir;

public class Turn implements Stmt {

    public TurnDir d;

    public Turn(TurnDir d) {
        this.d = d;
    }

    @Override
    public void accept(Visitor v) throws MazeException, MazeException {
        v.visit(this);
    }
}
