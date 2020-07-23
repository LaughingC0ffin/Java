package ubung9.maze.model;

public class MazeException extends Exception {
    public MazeException(String s) {
        super(s);
        System.out.println("MazeException");
    }
}
