import java.awt.*;
import java.util.Scanner;

//The methods and attributes specific for the chicken are in this extension of the class cell.
public class Chicken extends Cell {
    private Point position;
    private Scanner in = new Scanner(System.in);

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

    // Chicken (player) move

    public Point input() {
        System.out.println("Use WASD to move the chicken and J to jump to a random place! You can only make one move at a time.");
        while(true) {
            char moveInput = in.next().charAt(0);
            moveInput = Character.toLowerCase(moveInput);
            if (moveInput == 'w') {
                return (new Point(position.x - 1, position.y));
            } else if (moveInput == 's') {
                return (new Point(position.x + 1, position.y));

            } else if (moveInput == 'a') {
                return (new Point(position.x, position.y - 1));
            } else if (moveInput == 'd') {
                return (new Point(position.x, position.y + 1));
            } else if (moveInput == 'j') {
                return Cell.getEmpty();
            } else {
                System.out.println("Use WASD to move the chicken and J to jump to a random place! You can only make one move at a time.");
            }
        }
    }

    public void move(Board board) {
        Point destination = this.input();
        if (board.getCell(destination).getType().equals("Empty")){
            board.setCell(destination, board.chic);
            board.setCell(position, new Empty());
            Cell.addEmpty(position);
            position = destination;
        } else {
            board.gameState = "foxWin";
        }
    }

}
