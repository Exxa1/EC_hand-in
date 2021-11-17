import java.awt.*;

public class Chicken extends Cell{
    private Point position;

    public Chicken(Point pos) {
        this.position = pos;
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
