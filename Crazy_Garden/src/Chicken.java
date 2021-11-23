import java.awt.*;
import java.util.Scanner;

//The methods and attributes specific for the chicken are in this extension of the class cell.
public class Chicken extends Cell {
    private Point position;                              //Stores own position in itself, so it is easy to reach for foxes
    private int jumpAvailable;                           //The amount of jumps available for the player
    private Scanner in = new Scanner(System.in);

    public Chicken() {
        this.jumpAvailable = 4;
    }

    // get input to move chicken
    public Point input(Board board) {
        System.out.println("Use WASD to move the chicken and J to jump to a random place! You can only make one move at a time.");
        while(true) {                                                 //Loops while correct input is given
            char moveInput = in.next().charAt(0);
            moveInput = Character.toLowerCase(moveInput);
            if (moveInput == 'w') {                                   //Up
                return (new Point(position.x - 1, position.y));
            } else if (moveInput == 's') {                            //Down
                return (new Point(position.x + 1, position.y));

            } else if (moveInput == 'a') {                            //Left
                return (new Point(position.x, position.y - 1));
            } else if (moveInput == 'd') {                            //Right
                return (new Point(position.x, position.y + 1));
            } else if (moveInput == 'j') {                            //Jump
                if (jumpAvailable > 0) {
                    jumpAvailable--;
                    System.out.println("Be careful, you have " + jumpAvailable + " jumps left.");
                    return board.getRandomEmpty();                    //Returns a random empty position
                } else {
                    System.out.println("No more jumps left, run chicken ruuuun!");
                }
            } else {
                System.out.println("Use WASD to move the chicken and J to jump to a random place! You can only make one move at a time.");
            }
        }
    }

                                                                     //Chicken (player) move
    public void move(Board board) {                                 //Different from the move() in Cell class since this has different parameters
        Point destination = this.input(board);                      //Picks destination based on input
        if (board.getCell(destination).whatType().equals("Empty")){ //If empty, move there
            board.setCell(destination, board.chic);
            board.setCell(position, new Empty());
            position = destination;
        } else {                                                   //If not empty then rake or fox, meaning chicken lost
            board.gameState = "foxWin";
        }
    }

    public Point getPos() {                                       //Returns position of the chicken. Used in Board.populate() and in Fox.move()
        return position;
    }
    public void setPos(Point pos) {                               //Sets position of the chicken. Used in Board.populate()
        position = pos;
    }
    @Override                                                       //Overrides Cell class method
    public String whatType() {
        return "Chicken";
    }

}
