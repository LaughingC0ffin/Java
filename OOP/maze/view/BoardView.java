package ubung9.maze.view;

import maze.model.Observer;
import maze.model.Walker;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardView extends Group implements Observer {
    private static final int SQUARE_WIDTH = 48;
    private static final String WALL_PATH = "/wall.png";
    private static final String EMPTY_PATH = "/empty.png";
    private static final String WALKER_PATH = "/walker.png";

    private static final Image WALL = getImage(WALL_PATH);
    private static final Image EMPTY = getImage(EMPTY_PATH);
    private static final Image WALKER = getImage(WALKER_PATH);

    private final Walker walker;
    private final ImageView walkerView;

    /**
     * Returns an BoardView object that realizes the view in the GUI
     *
     * @param walker the walker which will be placed in the created BoardView object
     * @param width the width of the painted maze, given as a number of fields.
     * @param height the height of the painted maze, given as a number of fields.
     *
     * @return the initial BoardView with walls, empty fields and the walker
     */
    public BoardView(Walker walker, int width, int height) {
        this.walker = walker;
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                final boolean acc = walker.getMaze().isAccessible(x, y);
                final ImageView iv = new ImageView(acc ? EMPTY : WALL);
                iv.setX(x * SQUARE_WIDTH);
                iv.setY(y * SQUARE_WIDTH);
                getChildren().add(iv);
            }
        walkerView = new ImageView(WALKER);
        getChildren().add(walkerView);
        update();
        walker.addObserver(this);
    }


    /**
     * updates the view of the walker,depending on the orientation
     */
    @Override
    public void update() {
        switch (walker.getOrientation()) {
            case SOUTH:
                walkerView.setScaleX(1.0);
                walkerView.setRotate(90.0);
                break;
            case NORTH:
                walkerView.setScaleX(1.0);
                walkerView.setRotate(-90.0);
                break;
            case WEST:
                walkerView.setScaleX(-1.0);
                walkerView.setRotate(0.0);
                break;
            case EAST:
                walkerView.setScaleX(1.0);
                walkerView.setRotate(0.0);
        }
        walkerView.setX(walker.getX() * SQUARE_WIDTH);
        walkerView.setY(walker.getY() * SQUARE_WIDTH);
    }

    /**
     * Getter-method for the Image
     * 
     * @param s Path of the Image
     * @return the Image object
     */
    private static Image getImage(String s) {
        return new Image(BoardView.class.getResourceAsStream(s));
    }
}
