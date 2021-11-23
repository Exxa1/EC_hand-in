import java.awt.*;
import java.util.ArrayList;

/**
 * Cells in the Board class are populated with entities created by the entity class
 */
/*//We use an abstract cell because of the extended classes. Each extended class follow the methods and attributes written
here, but some specific

 */
public abstract class Cell {

    public abstract String whatType(); //This one is never used (therefore abstract) but needed here to be able to override in sub-classes

    public void move(Board board, int x, int y){  //This is called in Board.update if cell is not fox
        // do nothing
    }
}
