package ubung9.maze.model.stmt;

import ubung9.maze.model.MazeException;
import ubung9.maze.model.Walker;

public class SimpleVisitor implements Visitor{

    private Walker walker;

    public SimpleVisitor (Walker walker){
        this.walker = walker;
    }

    @Override
    public void visit(IfCanWalk stmt) throws MazeException {
        if(walker.canWalk()) stmt.s1.accept(this);
        else stmt.s2.accept(this);
        String string = String.format("(%s(%s %s))",1,2,3);
    }

    @Override
    public void visit(Repeat stmt) throws MazeException {
        for(int i=0;i<stmt.n;i++){
            stmt.s.accept(this);
        }
    }

    @Override
    public void visit(Sequence stmt) throws MazeException {
        for(int i =0;i<stmt.commands.length;i++){
            stmt.commands[i].accept(this);
        }
    }

    @Override
    public void visit(Turn stmt) throws MazeException {
        walker.turn(stmt.d);
    }

    @Override
    public void visit(Walk stmt) throws MazeException {
        if(walker.canWalk()){
            walker.walk();
        }else{
            throw new MazeException("");
        }
    }
}
