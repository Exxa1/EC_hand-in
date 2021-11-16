import java.awt.*;
import java.util.ArrayList;

/**
 * Cells in the Board class are populated with entities created by the entity class
 */
public class Cell {
    private boolean moveToggle = false;                           // Using it to check if an entity has been updated
    private final ArrayList<Point> emptyPos = new ArrayList<>();
    public int foxNum;

    public void setToggle(boolean toggleState) {
        moveToggle = toggleState;
    }

    public void switchToggle() {                                    // change updateToggle - used when cell is updated
        moveToggle = !moveToggle;
    }

    public boolean toggleState() {                                  // get the state of the toggle
        return moveToggle;
    }
}
