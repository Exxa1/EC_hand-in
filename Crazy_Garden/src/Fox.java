import java.awt.*;
import java.util.function.Function;

public class Fox extends Cell{
    public static int foxNum = 0;

    public Fox() {
        super();
        foxNum++;
    }

    public Point pickDestination(Point foxLocation, Point chicLocation){
        Point destination = new Point(foxLocation);
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
