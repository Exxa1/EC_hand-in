import java.awt.*;
import java.util.function.Function;

//The methods and attributes specific for the fox are in this extension of the class cell.
public class Fox extends Cell{
    public static int foxNum = 0;

    public Fox() {
        super();
        foxNum++;
    }

    //This is how the computer moves the foxes, so it can choose the quickest and shortest path to get to the chicken.
    public Point pickDestination(Point foxLocation, Point chicLocation){   //The computer must know the location of the chicken and the fox
        Point destination = new Point(foxLocation);

/* Each if statement compares the x and y coordinates and moves in the direction closer to the chicken.
        Does the fox move diagonally? Does this set up of the code allow both x and y to be compared at the same time?
        or does it only compare one coordinate at a time: since java is row-major does it only consider the row,
        the x position, until it lines up with the chicken before moving in the y-direction?
 */
        if (foxLocation.x > chicLocation.x) {
            destination.x--;
        }
        else if (foxLocation.x < chicLocation.x) {
            destination.x++;
        }
        if (foxLocation.y > chicLocation.y) {
            destination.y--;
        }
        else if (foxLocation.y < chicLocation.y) {
            destination.y++;
        }
        return destination;
    }

    public String getType() {
        return "Fox";
    }

}
