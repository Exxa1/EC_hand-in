package com.company;

import java.util.Random;

public class Cell {
    public final int x;        //These variables were private, but they were changed to public so other methods could call upon them.
    public final int y;
    public int state;
    public boolean hasMoved = false;

    public TypeofCell type;

    //The state of the cell can be animal, empty, or an obstacle
    enum TypeofCell {       //Inuk's idea
        Animal,             //enum is a data type that stores a list of variables and constants
        Empty,
        Obstacle,
    }

    public Cell(int x, int y) {   //constructor: calls from the Cell class. The base was taken from Think Java book.
        this.x = x;                 //Initializing variables
        this.y = y;
        this.state = 0;
        Random rand = new Random();                     // randomly assign types to cells
        int chance = rand.nextInt(100);         //This changes the chances of which type the cell will be. When using random only in theory, the cell types added will be distributed equally between each type. This is weighted. So when we randomly add cells (types of cells) in the array in the GridCanvas, it won't be equally distributed.
        if(chance < 5) this.type = TypeofCell.Animal;       // To make the game go faster, I used a very small chance of the cells added to be an animals.
        else if (chance < 90) this.type = TypeofCell.Empty; // I believe that majority of the cells should be empty so the animals can move around and interact more on the grid, so I made this variable heavily weighted.
        else this.type = TypeofCell.Obstacle;               // There needs to be obstacles, but I didn't want them to overpopulate the grid, so I made it larger than the animals but much less than the empty cells. Also, the amount I had left was just allocated for it.
    }

    public String toString() {
        return switch (type)
        {
            case Animal ->  // switching from type to string, so the variables: animal, empty, and obstacle, are represented on the grid as A,-,and X.
                    "A";
            case Empty ->
                    "-";
            case Obstacle ->
                    "X";
        };
    }
}

