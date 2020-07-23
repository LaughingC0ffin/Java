package ubung9.maze.model.stmt;

import ubung9.maze.model.MazeException;

public class Repeat implements Stmt{

    public int n;
    public Stmt s;

    public Repeat(int n, Stmt commands){
        this.n =n;
        this.s=commands;
    }


    @Override
    public void accept(Visitor v) throws MazeException, MazeException {
        v.visit(this);
    }
}
