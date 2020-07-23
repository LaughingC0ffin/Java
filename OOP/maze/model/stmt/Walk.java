package ubung9.maze.model.stmt;

import ubung9.maze.model.MazeException;

public class Walk implements Stmt{

    public Walk(){}

    @Override
    public void accept(Visitor v) throws MazeException {
        v.visit(this);
    }
}
