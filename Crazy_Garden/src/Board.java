import java.util.ArrayList;

public class Board {
    private static final int rows = 15;
    private static final int columns = 20;
    private final Cell[][] boardArray = new Cell[rows][columns];
    private ArrayList<Cell> emptyEntities = new ArrayList<>();
    public ArrayList<Cell> animals = new ArrayList<>();

    // populates board with borders and empty cells
    {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || i == rows-1 || j == 0 || j == columns-1) {
                    this.boardArray[i][j] = new Cell("Obstacle");
                }
                else {
                    boardArray[i][j] = new Cell();                                    // Making empty cells
                    emptyEntities.add(this.boardArray[i][j]);
                }
                this.boardArray[i][j].position = new int[]{i, j};                       // Saving position inside object
            }
        }
    }

    // populate static method where you can decide the number of obstacles, animals
    public void populate(int numObstacles, int numAnimals) {
//        shuffleList(this.emptyEntities);
        int x; // coordinates
        int y;
        int pick; //variable to store randomly picked index
        for (int i = 0; i < numObstacles; i++) {
            pick = randInt(0, emptyEntities.size());
            x = this.emptyEntities.get(pick).position[0];
            y = this.emptyEntities.get(pick).position[1];
            this.boardArray[x][y].type = "Obstacle";
            emptyEntities.remove(this.boardArray[x][y]);
        }
        for (int i = 0; i < numAnimals; i++) {
            pick = randInt(0, emptyEntities.size());
            x = this.emptyEntities.get(pick).position[0];
            y = this.emptyEntities.get(pick).position[1];
            this.boardArray[x][y].type = "Animal";
            emptyEntities.remove(this.boardArray[x][y]);
            animals.add(this.boardArray[x][y]);
        }
    }

    public static int randInt(int min, int max) {
        return (int)(Math.random()*(max-min))+min;
    }

    public void display() {
        for (Cell[] i : this.boardArray) {
            for (Cell j : i) {
                switch (j.type) {
                    case "Empty" -> System.out.print(" _ ");
                    case "Obstacle" -> System.out.print(" X ");
                    case "Animal" -> System.out.print(" * ");
                }
            }
            System.out.println();
        }
//        System.out.println(animals.size());
        System.out.println();
//        for (Entity i: emptyEntities){
//            System.out.print(Arrays.toString(i.position));
//        }
//        System.out.println();
    }

    static boolean updateIf = true;
    public void update(){
        int[][] neighbours;
        int[] moveTo;
        updateIf = !updateIf;
        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < columns-1; j++) {
                if(this.boardArray[i][j].updateToggle == updateIf) {
                    if (this.boardArray[i][j].type.equals("Animal")) {
                        neighbours = getNeighbours(this.boardArray[i][j].position);//get neighbours
                        moveTo = neighbours[randInt(0, 8)];
                        if (this.boardArray[moveTo[0]][moveTo[1]].type.equals("Empty")) {
                            this.boardArray[moveTo[0]][moveTo[1]] = this.boardArray[i][j];
                            this.boardArray[moveTo[0]][moveTo[1]].position[0] = moveTo[0];
                            this.boardArray[moveTo[0]][moveTo[1]].position[1] = moveTo[1];
                            this.boardArray[moveTo[0]][moveTo[1]].updateToggle = !updateIf;
                            this.emptyEntities.remove(this.boardArray[moveTo[0]][moveTo[1]]);
                        } else animals.remove(this.boardArray[i][j]);
                        this.boardArray[i][j] = new Cell(updateIf);
                        this.boardArray[i][j].position = new int[]{i, j};
                        this.emptyEntities.add(this.boardArray[i][j]);
                        animals.remove(this.boardArray[i][j]);
                        if (animals.size() < 2) return;
                    }
                    this.boardArray[i][j].updateToggle = !updateIf;
                }
            }
        }
        // iterate through cells
        // if cell is animal
        // try to move
    }

    private static int[][] getNeighbours(int[] pos) {
        int[][] result = new int[8][2];
        int index = 0;
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j ==0)){
                    result[index][0] = pos[0] + i;
                    result[index][1] = pos[1] + j;
                    index++;
                }
            }
        }
        return result;
    }
}
