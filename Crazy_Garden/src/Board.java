import java.awt.*;
import java.util.Arrays;
// comment

public class Board {
    private final int ROWS;                                 // row variable
    private final int COLUMNS;                              // column variable
    private final Cell[][] farmArray;                    // 2D array to store the entities
    public String gameState = "run";

    public Board(int numberOfRows, int numberOfColumns){
        ROWS = numberOfRows;                                // setting the number of rows and columns
        COLUMNS = numberOfColumns;
        farmArray = new Cell[ROWS][COLUMNS];             // initializing the 2D array for the board
        for (int i = 0; i < ROWS; i++) {                    // populating the board with obstacles for borders and empty cells elsewhere
            for (int j = 0; j < COLUMNS; j++) {
                if (i == 0 || i == ROWS -1 || j == 0 || j == COLUMNS -1) {
                    setCell(i,j, new Rake());           // creates Rake objects as borders
                }
                else {
                    setCell( i, j, new Empty());
                    Cell.addEmpty(new Point(i,j));            // adding empty position to ArrayList in Cell
                }
            }
        }
    }

    private final Chicken chic = new Chicken();                       // need to create here since used in update() too
    public void populate(int numRakes, int numFox) {         // populating the board with obstacles and animals
        if ((ROWS -2)*(COLUMNS -2)-1 > numRakes+numFox) {      // validating input
            for (int i = 0; i < numRakes; i++) {
                setCell(Cell.getEmpty(), new Rake());          // making new Rake at pos
            }
            for (int i = 0; i < numFox; i++) {
                setCell(Cell.getEmpty(), new Fox());   // making new Animal at pos
            }
            chic.setPos(Cell.getEmpty());        // PLACE CHICKEN ON BOARD
            setCell(chic.getPos(), chic);

        } else System.out.println("Too many foxes and rakes!");
    }

    public void display() {                                        // Printing out the board
        for (Cell[] cells : farmArray) {
            for (Cell cell : cells) {
                switch (cell.getType()) {
                    case "Empty" -> System.out.print(" _ ");
                    case "Rake" -> System.out.print(" X ");
                    case "Fox" -> System.out.print(" * ");
                    case "Chicken" -> {
                        if (gameState.equals("run")) System.out.print(" C ");
                        else if (gameState.equals("foxWin")) System.out.print(" D ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // COMPUTER MOVE - REWORK
    private boolean updateIf = true;                                      // only update the cells if this variable matches the value of updateToggle inside cell
    public void update() {
        Point pos;                                                        // position of the cell
        Point moveTo;                                                     // position to move to
        updateIf = !updateIf;                                             // in every round updateIf is switched to its opposite
        for (int i = 1; i < ROWS - 1; i++) {                               // iterate through the cells of the board  except for borders
            for (int j = 1; j < COLUMNS - 1; j++) {
                pos = new Point(i, j);
                Cell cell = getCell(pos);
                if (cell.getToggle() == updateIf) {                    // if cell is not updated
                    cell.switchToggle();
                    if (cell.getType().equals("Fox"))
                        move(pos, cell);
                    }
                }
            }
        }

    private Cell getCell(int row, int column) {                     // get the entity at given location
        return this.farmArray[row][column];
    }
    private Cell getCell(Point pos) {                               // overload getCell to be able to handle Points
        return this.farmArray[pos.x][pos.y];
    }

    private void setCell(int row, int column, Cell to) {            // making a cell in the board to refer to an entity
        this.farmArray[row][column] = to;
    }
    private void setCell(Point pos, Cell to) {                      // overload setCell to be able to handle Points
        this.farmArray[pos.x][pos.y] = to;
    }

    private void move(Point fromLocation, Cell fox) {
        Point moveTo = fox.pickDestination(fromLocation, chic.getPos());
        if (getCell(moveTo).getType().equals("Empty")) {           // if the cell is not occupied
            setCell(moveTo, fox);                       // making the picked cell to refer to moving object
            Cell.removeEmpty(moveTo);           // the position where the cell moved is removed from empty positions list
        } else if (getCell(moveTo).getType().equals("Chicken")) {
            gameState = "foxWin";
        }else Fox.foxNum--;                              // if the cell where animal want to move is not empty, in next step animal is deleted
        setCell(fromLocation, new Empty(!updateIf));             // empty cell on place of animal
        Cell.addEmpty(new Point(fromLocation));                 // adding to empty entities list
    }
}
