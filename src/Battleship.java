/**
 * A ship with a length of four tiles.
 */
public class Battleship extends Ship {
    /**
     * Sets the inherited length variable and initializes the hit array, based on the size of this ship (4 tiles).
     */
    public Battleship() {
        super();
        length = 4;
        hit = new boolean[length];
    }

    /**
     * @return "Battleship", indicating that this is a Battleship.
     */
    public String getShipType() {
        return "Battleship";
    }
}
