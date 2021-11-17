import java.awt.*;
import java.util.ArrayList;

/**
 * Cells in the Board class are populated with entities created by the entity class
 */
public class Cell {
    private boolean moveToggle;                           // Using it to check if an entity has been updated
    private static final ArrayList<Point> emptyPos = new ArrayList<>();
    public int foxNum = 0;

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

    public static void addEmpty(Point pos) {
        emptyPos.add(pos);
    }
    public static Point getEmpty(){                        // picks and empty position and deletes it from the arraylist
        int index = randInt(0, emptyPos.size());
        emptyPos.remove(index);
        return emptyPos.get(index);
    }

    public static int randInt(int min, int max) {                  // Generate random integer method
        return (int)(Math.random()*(max-min))+min;
    }

    public String getType() {
        return "noType";
    }
}
