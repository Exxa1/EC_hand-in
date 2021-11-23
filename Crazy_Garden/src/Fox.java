import java.awt.*;

//The methods and attributes specific for the fox are in this extension of the class cell.
public class Fox extends Cell {
    private boolean moveToggle = false;       // Using it to check if a fox has benn moved already

    public Fox() {
    }

    //This is how the computer moves the foxes, so it can choose the quickest and shortest path to get to the chicken.
    private Point pickDestination(Point foxLocation, Point chicLocation) {   //The computer must know the location of the chicken and the fox
        Point destination = new Point(foxLocation);

/* Each if statement compares the x and y coordinates and moves in the direction closer to the chicken.
   The fox also moves diagonally.
 */
        if (foxLocation.x > chicLocation.x) {
            destination.x--;
        } else if (foxLocation.x < chicLocation.x) {
            destination.x++;
        }
        if (foxLocation.y > chicLocation.y) {
            destination.y--;
        } else if (foxLocation.y < chicLocation.y) {
            destination.y++;
        }
        return destination;
    }

    @Override                                                   //Overrides the Cell class move() method.
    public void move(Board board, int x, int y) {
        if (this.moveToggle == board.moveToggle) {              //Compares the fox's moveToggle to the board's. If matches, try to move.
            this.moveToggle = !this.moveToggle;                 //moveToggle becomes its opposite so this fox will only be updated again in next iteration
            Point foxPosition = new Point(x, y);
            Point moveTo = pickDestination(foxPosition, board.chic.getPos());
            if (board.getCell(moveTo).whatType().equals("Empty")) {           // if the cell is not occupied
                board.setCell(moveTo, this);                               // making the picked cell point to this fox
            } else if (board.getCell(moveTo).whatType().equals("Chicken")) {   //If where the fox moves there is the chicken
                board.gameState = "foxWin";                                    // fox won
            } else
                board.foxNumber--;                                      //If the cell where fox wants to move is not empty fox stays and will be deleted
                if (board.foxNumber < 1) board.gameState = "chicWin";   //If no more fox, chicken won
            board.setCell(foxPosition, new Empty());             //Empty cell on place of old position of the fox (if fox didn't move it is deleted here)
        }
    }

    @Override                                                 //Overrides Cell class method
    public String whatType() {
        return "Fox";
    }
}
