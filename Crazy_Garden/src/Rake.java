/*The methods and attributes specific for the rake are in this extension of the class cell.
Since the rake is an obstacle that doesn't move there isn't much to rakes except for making them exist on the board.
And returning its type.
 */
public class Rake extends Cell{
    public Rake(){
    }

    @Override
    public String whatType() {
        return "Rake";
    }
}
