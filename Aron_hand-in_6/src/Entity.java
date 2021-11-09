public class Entity {
    public String type;
    public int[] position;
    public boolean updateToggle = false;

    public Entity() {
        this.type = "Empty";
    }
    public Entity(boolean updateState) {
        this.type = "Empty";
        this.updateToggle = updateState;
    }

    public Entity(String type) {
        if (!type.equals("Empty") && !type.equals("Obstacle") && !type.equals("Animal"))
            System.out.println("type argument is not correct");
        else
            this.type = type;
    }

    public Entity(Entity that) {
        this.type = that.type;
        this.position = that.position;
        this.updateToggle = that.updateToggle;
    }
}
