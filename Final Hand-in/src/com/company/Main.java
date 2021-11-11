package com.company;

//Create a small universe consisting of a board of fields. Each field can be empty or be occupied.
//A field can be occupied by an obstacle or an animal.
//Obstacles are placed randomly.
//Animals are placed randomly.
//When time starts, animals start to move randomly in any direction (also diagonally). One step per cycle / loop.
//If an animal moves to a field where there is an obstacle, it dies.
//Two animals cannot occupy the same square, so at least one of them must die. Implement a rule that says which one of them that dies (disappears from the board).
//The end condition is when there is exactly one animal alive.

//Author: Kristina Carter       Last Edited: 10/11/2021
public class Main {

    public static void main(String[] args) {
        int row = 10;       //size of the grid
        int column = 10;
        GridCanvas grid = new GridCanvas(row,column);
        grid.run();         //playing the game: calls information from the other classes to run the entire program.
    }
}

