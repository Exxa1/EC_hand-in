/*
11/10/2021
Aron Kuna - Hand in number 6 for Essential Computing

The program prints a board where cells are either empty '_', obstacles 'X' or animals '*'.
Animals are moving frame by frame. When an animal moves to a place where there is obstacle or another animal,it dies.
When only one animal is left, the program ends.
 */

public class Main {

    public static void main(String[] args) {
        Board board = new Board(15, 20);      // Board object, it contains all the cells
        board.populate(20, 15);      // obstacles + animals has to be smaller than ((row-2)*(column-2))
        board.display();                                   // Printing the board to the terminal

        while(board.gameState.equals("run")) {                       // loop runs until only one animal left
            board.update();                                // iterates through board cells and moving animals
            board.display();                               // Printing the board to the terminal
            try {                                          // prints a frame in every half a second (2fps)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (board.gameState.equals("foxWin")) {
            System.out.println("GAME OVER");
            System.out.println("The fox had a delicious lunch.");
        }
    }
}
