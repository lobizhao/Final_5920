/**
 * A ship with a length of three tiles.
 */
public class Cruiser extends Ship {
    /**
     * Sets the inherited length variable and initializes the hit array, based on the size of this ship (3 tiles).
     */
    public Cruiser() {
        super();
        length = 3;
        hit = new boolean[length];
    }

    /**
     * @return "Cruiser", indicating that this is a Cruiser.
     */
    public String getShipType() {
        return "Cruiser";
    }
}
