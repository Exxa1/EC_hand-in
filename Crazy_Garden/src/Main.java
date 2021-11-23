/*
22/11/2021
Essential Computing Fall 2021, RUC
Mini Project "Enemies"
Authors: Aron Kuna, Su Mei Gwen Ho, and Kristina Carter

The program prints a board where cells are either empty '.', rakes (obstacles) 'X', foxes (enemies) '🦊', or the Chicken '🐥'.
The game has difficulty levels. Once you win one, the game gets harder. The goal is to live as many days as you can.
When one level starts, the game is initialized, you start each round by making a move in one of four directions or by jumping to a random
place, then the foxes will move in attempt to eat you. If all foxes eliminated you win and next level comes. If you go into a rake or a fox catches you,
the game ends.
The player controls the chicken with five keys: w,a,s,d, and j. We used the universal w,a,s,d for the up, left, down, and
right directions. J is used to make the chicken jump to a random place on the board. Whenever these keys are pressed the
code will enact these actions.

(You, the player, are the chicken. The goal of the game is to be the last survivor. You must avoid the foxes, which are chasing
you around the garden, while dodging the rakes scattered around. Both foxes and rakes can kill you.
To eliminate all the foxes, you must guide them into the rakes and each other.) -- might transfer to the report.
 */

public class Main {

    public static void main(String[] args) {
        boolean play = true;                                        // initialize gameplay variables
        int numRakes = 20;                                          // starting number of rakes
        int numFox = 2;                                             // starting number of foxes
        int level = 0;                                              // variable to see how many times can the player win
        while(play && numRakes > 0){                                // loop stops if player loses or no more rake left (impossible to win)
            play = startGame(numRakes, numFox);
            level++;
            numRakes -= 2;
            numFox += 2;
        }
        System.out.println("Congratulation, your chicken lived " + (level-1) + " day(s)");
    }

    private static boolean startGame(int numRakes, int numFox) {
        Board board = new Board(15, 20);      // Board object, it contains all the cells
        board.populate(numRakes, numFox);                                // obstacles + animals has to be smaller than ((row-2)*(column-2))
        board.display();                                                 // Printing the board to the terminal

        while(board.gameState.equals("run")) {                          // loop runs until no more fox or chicken dies
            board.chic.move(board);                                     // chicken (player) move
            board.computerMove();                                       // iterates through board cells to move foxes
            board.display();                                            // Printing the board to the terminal
        }
        if (board.gameState.equals("foxWin")) {                         //If player lose
            System.out.println("GAME OVER");
            System.out.println("The fox had a delicious lunch.");
            return false;                                               //returns false meaning main loop stops
        } else if (board.gameState.equals("chicWin")) {                 //If player win
            System.out.println("YOU WON");
            System.out.println("The chicken lived another day.");
            return true;                                                // returns true meaning main loop runs and new level comes
        }
        else return false;                                              // if no bug this cannot happen
    }
}
