package ubung9.maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maze.controller.WalkerKeyListener;
import maze.model.*;
import maze.view.BoardView;

public class MazeApp1 extends Application {
    private static final int BOARD_WIDTH = 8;
    private static final int BOARD_HEIGHT = 8;

    private final Walker walker;
    private final BoardView view;
    private final WalkerKeyListener controller;

    public MazeApp1() throws MazeException {
        final Maze maze = ...
        walker = new Walker(1, 5, Orientation.EAST, maze);
        view = new BoardView(walker, BOARD_WIDTH, BOARD_HEIGHT);
        controller = new WalkerKeyListener(walker);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(view));
        stage.sizeToScene();


        stage.setTitle("Walker in the Maze");
        stage.show();
    }
}