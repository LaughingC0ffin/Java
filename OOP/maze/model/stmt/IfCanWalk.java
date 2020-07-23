package ubung9.maze.model.stmt;

import ubung9.maze.model.MazeException;

public class IfCanWalk implements Stmt{

    public Sequence s1;
    public Sequence s2;

    public IfCanWalk(Sequence s1, Sequence s2){
        this.s1 =s1;
        this.s2=s2;

    }


    @Override
    public void accept(Visitor v) throws MazeException, MazeException {
        v.visit(this);
    }
}

