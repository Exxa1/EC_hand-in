import java.awt.*;
import java.util.ArrayList;

public class Board {
    private final int ROWS;                                 // row variable
    private final int COLUMNS;                              // column variable
    private final Cell[][] farmArray;                       // 2D array to store the entities
    private ArrayList<Point> emptyPos = new ArrayList<>();  // Storing empty positions. Used for populating and when chicken jumps
    public String gameState = "run";                        // tells if the game is running or chicken or foxes won
    public int foxNumber;                                   // Number of alive foxes. When zero chicken wins
    public final Chicken chic = new Chicken();              // The chicken object. Used in populate() and in update()
    public boolean moveToggle = false;                      // Using this to only move foxes once before displaying (used in Fox.move())

    public Board(int numberOfRows, int numberOfColumns){
        ROWS = numberOfRows;                                // setting the number of rows and columns
        COLUMNS = numberOfColumns;
        farmArray = new Cell[ROWS][COLUMNS];                // initializing the 2D array for the board
        for (int i = 0; i < ROWS; i++) {                    // populating the board with rakes for borders and empty cells elsewhere
            for (int j = 0; j < COLUMNS; j++) {
                if (i == 0 || i == ROWS -1 || j == 0 || j == COLUMNS -1) {
                    setCell(i,j, new Rake());               // creates Rake objects as borders
                }
                else {
                    setCell( i, j, new Empty());
                }
            }
        }
    }

    public void populate(int numRakes, int numFox) {            // populating the board with obstacles and animals
        if ((ROWS -2)*(COLUMNS -2)-1 > numRakes+numFox) {       // validating input
            for (int i = 0; i < numRakes; i++) {
                setCell(getRandomEmpty(), new Rake());          // making new Rake at random position
            }
            for (int i = 0; i < numFox; i++) {
                setCell(getRandomEmpty(), new Fox());           // making new Fox at random position
                foxNumber++;
            }
            chic.setPos(getRandomEmpty());                      // PLACE CHICKEN ON BOARD. Since there is only once chicken, we don't need a for statement.
            setCell(chic.getPos(), chic);

        } else System.out.println("Too many foxes and rakes!"); //This is displayed when the board is overpopulated.
    }

    public void display() {                                     // Printing out the board
        for (Cell[] cells : farmArray) {
            for (Cell cell : cells) {
                switch (cell.whatType()) {
                    case "Empty" -> System.out.print(" . ");
                    case "Rake" -> System.out.print(" X ");
                    case "Fox" -> System.out.print("🦊 ");
                    case "Chicken" -> {
                        if (gameState.equals("run")) System.out.print("🐥 ");           //This will be displayed when the chicken is alive.
                        else if (gameState.equals("foxWin")) System.out.print("🍗 ");   //This will be displayed when the fox eats the chicken.
                        else if (gameState.equals("chicWin")) System.out.print("🐤 ");  //This will be displayed when the chicken win.
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // COMPUTER MOVE - REWORK
    public void computerMove() {
        for (int i = 1; i < ROWS - 1; i++) {                   //Iterate through the cells of the board  except for borders
            for (int j = 1; j < COLUMNS - 1; j++) {
                farmArray[i][j].move(this, i, j);        //If cell is not fox, nothing will happen. (See in Cell.move())
            }
        }
        moveToggle = !moveToggle;
    }

    public Cell getCell(Point pos) {                        //Get the cell object at requested position
        return this.farmArray[pos.x][pos.y];
    }

    private void setCell(int row, int column, Cell to) {//Making a cell in the board to point to an entity
        this.farmArray[row][column] = to;
        if (to.whatType().equals("Empty")) {            //If the cell is set to empty, adding position to empty positions list
            emptyPos.add(new Point(row, column));       //Never replacing empty cells with this method so no need for else statement as in other setCell()
        }
    }
    public void setCell(Point pos, Cell to) {           // overload setCell to be able to handle Points
        this.farmArray[pos.x][pos.y] = to;
        if (to.whatType().equals("Empty")) {            //If making an empty cell at position
            emptyPos.add(pos);                          //Adding it to emptyPos list
        } else {
            try {
                emptyPos.remove(pos);
            } catch (Exception e) {                     //In case the position is not on the emptyPos list.
                // do nothing                           //Shouldn't happen but making it further development proof
            }
        }
    }

    public Point getRandomEmpty(){                        // picks a random empty position and deletes it from the arraylist
        int index = (int)(Math.random()*(emptyPos.size()));
        return emptyPos.get(index);
    }

}
