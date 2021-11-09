public class Main {

    public static void main(String[] args) {
        int steps = 0;
        Board board = new Board();
        // obstacles + animals has to be smaller than ((row-2)*(column-2))
        board.populate(40, 10);
        board.display();

        while(board.animals.size() > 1) {
            board.update();
            board.display();
            steps++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { // SEARCH FOR THIS, WHAT IS THIS
                e.printStackTrace();
            }
        }
    }
}
