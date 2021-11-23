import java.awt.*;
import java.util.ArrayList;

/**
 * Cells in the Board class are populated with entities created by the entity class
 */
/*//We use an abstract cell because of the extended classes. Each extended class follow the methods and attributes written
here, but some specific

 */
public abstract class Cell {
    private boolean moveToggle;       // Using it to check if an entity has been updated    (Which entity? the chicken? all?)
    private static final ArrayList<Point> emptyPos = new ArrayList<>();

    public Cell(){
        this.moveToggle = false;
    }
    public Cell(boolean toggleState) {
        this.moveToggle = toggleState;
    }

    public void switchToggle() {                                    // change updateToggle - used when cell is updated
        moveToggle = !moveToggle;                                   // This is how we start a new round
    }

    public boolean getToggle() {                                  // get the state of the toggle
        return moveToggle;
    }

    public static void addEmpty(Point pos) {                //Storing the positions of empty cells
        emptyPos.add(pos);
    }
    public static Point getEmpty(){                        // picks and empty position and deletes it from the arraylist
        int index = randInt(0, emptyPos.size());
        Point result = emptyPos.get(index);
        emptyPos.remove(index);
        return result;
    }

    public static void removeEmpty(Point pos) {
        emptyPos.remove(pos);
    }       //

    public static int randInt(int min, int max) {                  // Generate random integer method
        return (int)(Math.random()*(max-min))+min;
    }

    public String getType() {                   //WHat is this? why do we do this? does change what kind of variable it is?
        return "noType";
    }

    public Point pickDestination(Point pos, Point chicPos) {return null;}   //Method for the computer deciding the position of the fox to move to.
}
