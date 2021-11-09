public class Cell {
    public String type;
    public int[] position;
    public boolean updateToggle = false;

    public Cell() {
        this.type = "Empty";
    }
    public Cell(boolean updateState) {
        this.type = "Empty";
        this.updateToggle = updateState;
    }

    public Cell(String type) {
        if (!type.equals("Empty") && !type.equals("Obstacle") && !type.equals("Animal"))
            System.out.println("type argument is not correct");
        else
            this.type = type;
    }

    public Cell(Cell that) {
        this.type = that.type;
        this.position = that.position;
        this.updateToggle = that.updateToggle;
    }
}
