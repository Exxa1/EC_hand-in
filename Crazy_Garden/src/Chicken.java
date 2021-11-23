import java.awt.*;

//The methods and attributes specific for the chicken are in this extension of the class cell.
public class Chicken extends Cell{
    private Point position;

    public Chicken(Point pos) {     //Variables and methods must be public since it is called in the fox subclass.
        this.position = pos;
    }

    public Chicken() {

    }

    public Point getPos() {
        return position;
    }

    public void setPos(Point pos) {
        position = pos;
    }

    // input to move Chic

    public String getType() {
        return "Chicken";
    }
}
