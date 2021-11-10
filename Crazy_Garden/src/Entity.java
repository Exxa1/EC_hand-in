/**
 * Cells in the Board class are populated with entities created by the entity class
 */
public class Entity {
    private String type;                                            // Animal/Obstacle/Empty
    private boolean updateToggle = false;                           // Using it to check if an entity has been updated

    public Entity() {                                               // by default, construct an empty cell
        this.type = "Empty";
    }
    public Entity(boolean updateState) {                            // when update state is defined
        this.type = "Empty";
        this.updateToggle = updateState;
    }

    public Entity(String type) {                                    // constructor for certain types
        if (type.equals("Empty") ||                                 // validate input
                type.equals("Obstacle") ||
                type.equals("Animal"))
            this.type = type;
        else
            System.out.println("type argument is not correct");

    }

    public void switchToggle() {                                    // change updateToggle - used when cell is updated
        updateToggle = !updateToggle;
    }

    public boolean toggleState() {                                  // get the state of the toggle
        return updateToggle;
    }

    public boolean isType(String type) {                           // check if type equals input
        return type.equals(this.type);
    }

    public String getType() {                                      // get the type of the entity
        return type;
    }
}
