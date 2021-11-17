import java.awt.*;

public class Fox extends Cell{
    public Fox() {
        super();
        foxNum++;
    }
//
//    public Point pickDestination(){
//        // gets location of chic
//        //if x is smaller than chic x x+1 etc.
//    }

    public String getType() {
        return "Fox";
    }
}
