/**
 * A ship with a length of one tile.
 */
public class Submarine extends Ship {
    /**
     * Sets the inherited length variable and initializes the hit array, based on the size of this ship (1 tiles).
     */
    public Submarine() {
        super();
        length = 1;
        hit = new boolean[length];
    }

    /**
     * @return "Submarine", indicating that this is a Submarine.
     */
    public String getShipType() {
        return "Submarine";
    }
}