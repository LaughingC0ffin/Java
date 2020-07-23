package ubung9.maze.model.stmt;

import maze.model.MazeException;
import ubung9.maze.model.MazeException;

public interface Visitor {

    void visit(IfCanWalk stmt) throws MazeException, MazeException;

    void visit(Repeat stmt) throws MazeException;

    void visit(Sequence stmt) throws MazeException;

    void visit(Turn stmt) throws MazeException;

    void visit(Walk stmt) throws MazeException;
}
