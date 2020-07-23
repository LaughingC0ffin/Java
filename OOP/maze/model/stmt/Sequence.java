package ubung9.maze.model.stmt;

import ubung9.maze.model.MazeException;

public class Sequence implements Stmt{

    public Stmt[] commands;

    public Sequence(Stmt... commands){
        this.commands =commands;
    }

    @Override
    public void accept(Visitor v) throws MazeException, MazeException {
        v.visit(this);
    }
}
