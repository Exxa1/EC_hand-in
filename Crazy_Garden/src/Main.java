/*
22/11/2021
Essential Computing Fall 2021, RUC
Mini Project "Enemies"
Authors: Aron Kuna, Su Mei Gwen Ho, and Kristina Carter

The program prints a board where cells are either empty '_', rakes (obstacles) 'X', foxes (animals) '*', or the Chicken 'C'.
After the game is initialized, you start each round by making a move in one of four directions or by jumping to a random
place, then the foxes will move in attempt to eat you. Once all foxes are eliminated or you die, the game ends.
The player controls the chicken with five keys: w,a,s,d, and j. We used the universal w,a,s,d for the up, left, down, and
right directions. J is used to make the chicken jump to a random place on the board. Whenever these keys are pressed the
code will enact these actions.

(You, the player, are the chicken. The goal of the game is to be the last survivor. You must avoid the foxes, which are chasing
you around the garden, while dodging the rakes scattered around. Both foxes and rakes can kill you.
To eliminate all the foxes, you must guide them into the rakes and each other.) -- might transfer to the report.
 */

public class Main {

    public static void main(String[] args) {
        Board board = new Board(15, 20);      // Board object, it contains all the cells
        board.populate(20, 5);      // obstacles + animals has to be smaller than ((row-2)*(column-2))
        board.display();                                   // Printing the board to the terminal

        while(board.gameState.equals("run")) {                       // loop runs until only one animal left
            board.chic.move(board);
            board.update();                                // iterates through board cells and moving animals
            board.display();                               // Printing the board to the terminal
            try {                                          // prints a frame in every half a second (2fps)
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (board.gameState.equals("foxWin")) {         //What will be displayed at the end of the game if you lose.
            System.out.println("GAME OVER");
            System.out.println("The fox had a delicious lunch.");  //Don't we need an else if to display something if player wins?
        } else if (board.gameState.equals("chicWin")){
            System.out.println("YOU WON");
            System.out.println("The chicken lived another day.");  //Don't we need an else if to display something if player wins?
        }
    }
}
