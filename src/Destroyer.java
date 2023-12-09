/**
 * A ship with a length of two tiles.
 */
public class Destroyer extends Ship {
    /**
     * Sets the inherited length variable and initializes the hit array, based on the size of this ship (2 tiles).
     */
    public Destroyer() {
        super();
        length = 2;
        hit = new boolean[length];
    }

    /**
     * @return "Destroyer", indicating that this is a Destroyer.
     */
    public String getShipType() {
        return "Destroyer";
    }
}
