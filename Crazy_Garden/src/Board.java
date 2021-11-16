import java.awt.*;
import java.util.ArrayList;
// comment

public class Board {
    private final int ROWS;                                 // row variable
    private final int COLUMNS;                              // column variable
    private final Entity[][] boardArray;                    // 2D array to store the entities
    private final ArrayList<Point> emptyCellPositions = new ArrayList<>();    // positions of empty cells
    public int animalNum;                                   // number of animals alive

    public Board(int numberOfRows, int numberOfColumns){
        ROWS = numberOfRows;                                // setting the number of rows and columns
        COLUMNS = numberOfColumns;
        boardArray = new Entity[ROWS][COLUMNS];             // initializing the 2D array for the board
        for (int i = 0; i < ROWS; i++) {                    // populating the board with obstacles for borders and empty cells elsewhere
            for (int j = 0; j < COLUMNS; j++) {
                if (i == 0 || i == ROWS -1 || j == 0 || j == COLUMNS -1) {
                    setCell(i,j,new Entity("Obstacle"));
                }
                else {
                    boardArray[i][j] = new Entity();             // Making empty cells
                    emptyCellPositions.add(new Point(i, j));     // adding empty cells to list
                }
            }
        }
    }

    public void populate(int numObstacles, int numAnimals) {         // populating the board with obstacles and animals
        if ((ROWS -2)*(COLUMNS -2) > numObstacles+numAnimals) {      // validating input
            int pick;                                               //variable to store randomly picked index
            Point pos;                                              // variable to store randomly picked position
            for (int i = 0; i < numObstacles; i++) {
                pick = randInt(0, emptyCellPositions.size());       // picking random empty position
                pos = emptyCellPositions.get(pick);
                setCell(pos, new Entity("Obstacle"));          // making new Obstacle at pos
                emptyCellPositions.remove(pos);                     // removing the coordinate from empty positions list
            }
            for (int i = 0; i < numAnimals; i++) {
                pick = randInt(0, emptyCellPositions.size());       // picking random empty position
                pos = emptyCellPositions.get(pick);
                setCell(pos.x, pos.y, new Entity("Animal"));   // making new Animal at pos
                emptyCellPositions.remove(pos);                     // removing the coordinate from empty positions list
                animalNum = numAnimals;                             // assigning amount of animals to animalNum
            }
        } else System.out.println("Too many animals and obstacles!");
    }

    public static int randInt(int min, int max) {                  // Generate random integer method
        return (int)(Math.random()*(max-min))+min;
    }

    public void display() {                                        // Printing out the board
        for (Entity[] entities : boardArray) {
            for (Entity cell : entities) {
                switch (cell.getType()) {
                    case "Empty" -> System.out.print(" _ ");
                    case "Obstacle" -> System.out.print(" X ");
                    case "Animal" -> System.out.print(" * ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean updateIf = true;                                      // only update the cells if this variable matches the value of updateToggle inside cell
    public void update(){
        Point[] neighbours;                                               // array for neighbour positions
        Point pos;                                                        // position of the cell
        Point moveTo;                                                     // position to move to
        updateIf = !updateIf;                                             // in every round updateIf is switched to its opposite
        for (int i = 1; i < ROWS -1; i++) {                               // iterate through the cells of the board  except for borders
            for (int j = 1; j < COLUMNS - 1; j++) {
                pos = new Point(i, j);
                Entity cell = getCell(i, j);
                if (cell.toggleState() == updateIf) {                    // if cell is not updated
                    cell.switchToggle();                                 // change updateToggle of the cell, so it will not be moved again
                    if (cell.isType("Animal")) {                         // if animal
                        neighbours = getNeighbours(pos);                 //get neighbours
                        moveTo = neighbours[randInt(0, 8)];              // where the animal wants to move to is randomly picked from neighbour positions
                        if (getCell(moveTo).isType("Empty")) {           // if the cell is not occupied
                            setCell(moveTo, cell);                       // making the picked cell to refer to this object
                            emptyCellPositions.remove(moveTo);           // the position where the cell moved is removed from empty positions list
                        } else animalNum--;                              // if the cell where animal want to move is not empty, in next step animal is deleted
                        setCell(pos, new Entity(!updateIf));             // empty cell on place of animal
                        emptyCellPositions.add(new Point(pos));          // adding to empty entities list
                        if (animalNum < 2) return;                       // if only one animal remains program stops
                    }
                }
            }
        }
    }

    private static Point[] getNeighbours(Point pos) {                    // get the coordinates around a coordinate
        Point[] result = new Point[8];
        int index = 0;
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j ==0)){
                    result[index] = new Point(pos.x + i, pos.y + j);
                    index++;
                }
            }
        }
        return result;
    }

    private Entity getCell(int row, int column) {                     // get the entity at given location
        return this.boardArray[row][column];
    }
    private Entity getCell(Point pos) {                               // overload getCell to be able to handle Points
        return this.boardArray[pos.x][pos.y];
    }

    private void setCell(int row, int column, Entity to) {            // making a cell in the board to refer to an entity
        this.boardArray[row][column] = to;
    }
    private void setCell(Point pos, Entity to) {                      // overload setCell to be able to handle Points
        this.boardArray[pos.x][pos.y] = to;
    }
}
