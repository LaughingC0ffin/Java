package ubung9.maze.model.stmt;

import maze.model.MazeException;
import ubung9.maze.model.MazeException;

public interface Stmt {
    void accept(Visitor v) throws MazeException, MazeException;
}
