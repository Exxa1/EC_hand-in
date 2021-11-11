package com.company;

import java.util.Random;

public class GridCanvas {
    private Cell[][] array;
    private int row;
    private int column;

    public GridCanvas(int row, int column){
        this.row=row;
        this.column=column;

        array = new Cell[row][column];
        for (int r = 0; r < row; r++){      //adding the random cells to the 2d array.
            int y = r;
            for (int c = 0; c < column; c++){
                int x = c;
                array[r][c] = new Cell (x, y);
            }
        }
    }

    /*public Cell upper(Cell cell)
    {
        if(cell.y != 0) return array[cell.y-1][cell.x];
        return null;
    }
    public Cell Lower(Cell cell)
    {
        if(cell.y != row-1) return array[cell.y+1][cell.x];
        return null;
    }

    public Cell Head (Cell cell)
    {
        if (cell.x != 0) return array[cell.y][cell.x-1];
        return null;
    }

    public Cell Tail ( Cell cell)
    {
        if(cell.x != column-1) return array[cell.y][cell.x+1];
        return null;
    }*/     //this code was for the boarder. This was called for the neighbours in line 65 when I made the neighbors method before the move method and would identify if the CellType.Animal was at the boarder.

    public void run() {
        while (hasAnimal()) {       //I used the condition that there must be an animal in the grid or else the game quits. This is different from the requirement for the game to quit with one animal left. To do that I would set the condition to hasAnimal = 1 or make and if statement that runs until hasAnimal equals 1. For example: if (hasAnimal > 1), but to use the > operator, I need to change boolean to an int or initialize the variable as an int.
            move();
            draw();
            System.out.println("###################");  //This separates the grids with each time step making it easier for the user to read and understand.

           try{
                Thread.sleep(1000);         //This is the exception handling code from Think Java. I changed it from 500 to 1000 milliseconds, so it is easier to see each time step.
            } catch (InterruptedException e){
                // do nothing
            }
        }
    }

    public void move() {                //This method is how the animal moves
        Random random = new Random();   // This line makes the animal move at random.
        for (var row: array) {
            for(var cell : row) {
                /**
                Cell upper = upper(cell);
                if(upper == null) System.out.println("At the boarder");
                else if (upper.type == Cell.TypeofCell.Animal) System.out.println("Neighbour is animal");
                else if (upper.type == Cell.TypeofCell.Empty) System.out.println("Neighbour is empty" );
                else if (upper.type == Cell.TypeofCell.Obstacle) System.out.println("Neighbour is Obstacle");

                Cell lower = Lower(cell);
                if (lower == null) System.out.println("At the boarder");
                else if (lower.type == Cell.TypeofCell.Animal) System.out.println("Neighbour is animal");
                else if (lower.type == Cell.TypeofCell.Empty) System.out.println("Neighbour is empty");
                else if (lower.type == Cell.TypeofCell.Obstacle) System.out.println("Neighbour is obstacle");

                Cell tail = Tail(cell);
                if (tail == null) System.out.println("At the boarder");
                else if (tail.type == Cell.TypeofCell.Animal) System.out.println("Neighbour is animal");
                else if (tail.type == Cell.TypeofCell.Empty) System.out.println("Neighbour is empty");
                else if (tail.type == Cell.TypeofCell.Obstacle) System.out.println("Neighbour is an obstacle");

                Cell head = Head(cell);
                if (head == null) System.out.println("At the boarder");
                else if (head.type == Cell.TypeofCell.Animal) System.out.println("Neighbour is animal");
                else if (head.type == Cell.TypeofCell.Empty) System.out.println("Neighbour is empty");
                else if (head.type == Cell.TypeofCell.Obstacle) System.out.println("Neighbour is an obstacle.");
                */  //This was when I created neighbours before the method of moving the animals. This also only had four neighbouring cells I had not written the cells in the diagonal directions yet. But then realized I should make the animal cells move before looking at neighbour cells.
                if(cell.type != Cell.TypeofCell.Animal || cell.hasMoved)
                    continue;
                int x,y;
                do {
                    x = random.nextInt(3) - 1;      // The do loop is the animal moving. It does have a potentially alarming flaw from the conditions in the while loop below.
                    y = random.nextInt(3) - 1;      //This moves on the grid in a coordinate plan fashion. With the -1, it shifts from (0,1,2) to (-1(down),0(stays the same),1(up)) for row x-directions. In the y-direction column(-1(left, 0(stays the same), 1(right)).
                } while (
                        x + cell.x == column
                    ||  x + cell.x == - 1
                    ||  y + cell.y == this.row
                    ||  y + cell.y== - 1        //These conditions allow the animals to move until it doesn't try to move out of the boarder. This means that if an animal is at [row][cell]= (0,0) and tried to move upwards and/or to the left, etc., the loop allows the animal to keep trying that direction but won't move in that direction until it moves within the grid. So the do loop can run for infinity. It is highly improbably for an animal cell to try to move out of bounds forever, but it could happen, so I would want to edit this so that it won't continue forever and not exit the game. But, I left this because the probability is so low.
                );

                Cell neighbour = array[y + cell.y][x + cell.x];     //This checks the neighbours in 8 directions
                if (neighbour.type == Cell.TypeofCell.Empty) {      //When the animal cell collides with an obstacle or another animal cell, it will become an empty cell, in other words it dies.
                    neighbour.type = Cell.TypeofCell.Animal;
                    neighbour.hasMoved = true;                      // This refers back to the boolean = false. So since it is true, this means that the animal has moved making the cell empty.
                }
                if(x != 0 || y !=0)
                    cell.type = Cell.TypeofCell.Empty;  //If it doesn't move it dies. This was placed when the animals seemed to disappear without running into an obstacle or another animal and this line fixed that.

            }
        }

        for (var row: array) {
            for (var cell : row) cell.hasMoved = false; //starts a new iteration or time step.
        }
    }
    public boolean hasAnimal() {        //This is the method for checking if the grid has animal cells. Even if I were to change the conditions for the run method on line 47, this method is still necessary for the new condition.
        for (var row : array)           //checks the cells in grid
            for (var cell : row)
                if (cell.type == Cell.TypeofCell.Animal) return true;   //if true there are animals
        return false;   //if false, there are no animals. This will be called in the run method, and when it is false, the game will stop.
    }

    /*
    public int boolToInt(boolean hasAnimal) {       My attempt to change hasAnimal to int so i can use > operator to end game with one animal on the grid
        return hasAnimal ? 1 : 0;                   by adam on stack overflow
    }
    */
    public void draw() {
        for(var row : array) {
            for(var cell : row) System.out.printf(cell.toString());
            System.out.println();       //visualizes the grid with the cell types.
        }
    }
}
