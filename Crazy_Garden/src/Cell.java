import java.awt.*;
import java.util.ArrayList;

/**
 * Cells in the Board class are populated with entities created by the entity class
 */
public class Cell {
    private boolean moveToggle;                           // Using it to check if an entity has been updated
    private final ArrayList<Point> emptyPos = new ArrayList<>();
    public int foxNum;

    public Cell(){
        this.moveToggle = false;
    }
    public Cell(boolean toggleState) {
        this.moveToggle = toggleState;
    }

    public void switchToggle() {                                    // change updateToggle - used when cell is updated
        moveToggle = !moveToggle;
    }

    public boolean getToggle() {                                  // get the state of the toggle
        return moveToggle;
    }
}
