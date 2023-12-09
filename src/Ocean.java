import java.util.Random;

/**
 * This class manages the game state by keeping track of what entity is contained in each position on the game board.
 */
public class Ocean implements OceanInterface {
    /**
     * Row Size
     */
    public static final int ROW = 10;

    /**
     * Column Size
     */
    public static final int COL = 10;

    /**
     * A 10x10 2D array of Ships, which can be used to quickly determine which ship is in any given location.
     */
    protected Ship[][] ships;

    /**
     * The total number of shots fired by the user
     */
    protected int shotsFired;

    /**
     * The number of times a shot hit a ship. If the user shoots the same part of a ship more than once, every hit is
     * counted, even though the additional "hits" don't do the user any good.
     */
    protected int hitCount;

    /**
     * The number of ships totally sunk.
     */
    protected int shipsSunk;

    /**
     * mark the places fired
     */
    private final boolean[][] fired;

    /**
     * Creates an "empty" ocean, filling every space in the <code>ships</code> array with EmptySea objects. Should also
     * initialize the other instance variables appropriately.
     */
    public Ocean() {
        ships = new Ship[ROW][COL];
        fired = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                Ship ship = new EmptySea();
                ship.placeShipAt(i, j, true, this);
            }
        }
    }

    /**
     * Place all ten ships randomly on the (initially empty) ocean. Larger ships must be placed before smaller ones to
     * avoid cases where it may be impossible to place the larger ships.
     *
     * @see java.util.Random
     */
    public void placeAllShipsRandomly() {
        Ship[] allShips = {
                new Battleship(),
                new Cruiser(), new Cruiser(),
                new Destroyer(), new Destroyer(), new Destroyer(),
                new Submarine(), new Submarine(), new Submarine(), new Submarine()
        };

        Random random = new Random();
        for (var ship : allShips) {
            while (true) {
                int row = random.nextInt(ROW);
                int column = random.nextInt(COL);
                boolean horizontal = random.nextBoolean();

                if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
                    ship.placeShipAt(row, column, horizontal, this);
                    break;
                }
            }
        }
    }

    /**
     * Checks if this coordinate is not empty; that is, if this coordinate does not contain an EmptySea reference.
     *
     * @param row    the row (0 to 9) in which to check for a floating ship
     * @param column the column (0 to 9) in which to check for a floating ship
     * @return {@literal true} if the given location contains a ship, and {@literal false} otherwise.
     */
    public boolean isOccupied(int row, int column) {
        if (row >= 0 && row < ROW && column >= 0 && column < COL) {
            return !(ships[row][column] instanceof EmptySea);
        }
        return false;
    }

    /**
     * Fires a shot at this coordinate. This will update the number of shots that have been fired (and potentially the
     * number of hits, as well). If a location contains a real, not sunk ship, this method should return {@literal true}
     * every time the user shoots at that location. If the ship has been sunk, additional shots at this location should
     * return {@literal false}.
     *
     * @param row    the row (0 to 9) in which to shoot
     * @param column the column (0 to 9) in which to shoot
     * @return {@literal true} if the given location contains an afloat ship (not an EmptySea), {@literal false} if it
     * does not.
     */
    public boolean shootAt(int row, int column) {
        shotsFired++;
        if (row >= 0 && row < ROW && column >= 0 && column < COL) {
            fired[row][column] = true;
            boolean hit = ships[row][column].shootAt(row, column);
            if (hit) {
                hitCount++;
                if (ships[row][column].isSunk()) {
                    shipsSunk++;
                }
            }
            return hit;
        }

        return false;
    }

    /**
     * @return the number of shots fired in this game.
     */
    public int getShotsFired() {
        return this.shotsFired;
    }

    /**
     * @return the number of hits recorded in this game.
     */
    public int getHitCount() {
        return this.shotsFired;
    }

    /**
     * @return the number of ships sunk in this game.
     */
    public int getShipsSunk() {
        return this.shipsSunk;
    }

    /**
     * @return {@literal true} if all ships have been sunk, otherwise {@literal false}.
     */
    public boolean isGameOver() {
        return this.shipsSunk == 10;
    }

    /**
     * Provides access to the grid of ships in this Ocean. The methods in the Ship class that take an Ocean parameter
     * must be able to read and even modify the contents of this array. While it is generally undesirable to allow
     * methods in one class to directly access instance variables in another class, in this case there is no clear and
     * elegant alternatives.
     *
     * @return the 10x10 array of ships.
     */
    public Ship[][] getShipArray() {
        return this.ships;
    }

    /**
     * Prints the ocean. To aid the user, row numbers should be displayed along the left edge of the array, and column
     * numbers should be displayed along the top. Numbers should be 0 to 9, not 1 to 10. The top left corner square
     * should be 0, 0.
     * <ul>
     * <li>Use 'S' to indicate a location that you have fired upon and hit a (real)
     * ship</li>
     * <li>'-' to indicate a location that you have fired upon and found nothing
     * there</li>
     * <li>'x' to indicate a location containing a sunken ship</li>
     * <li>'.' (a period) to indicate a location that you have never fired
     * upon.</li>
     * </ul>
     * <p>
     * This is the only method in Ocean that has any printing capability, and it
     * should never be called from within the Ocean class except for the purposes of
     * debugging.
     */
    public void print() {
        System.out.println();
        System.out.print(" ");
        for (int i = 0; i < COL; i++) {
            System.out.printf(" %d", i);
        }

        System.out.println();
        for (int i = 0; i < ROW; i++) {
            System.out.print(i);
            for (int j = 0; j < COL; j++) {
                System.out.printf(" %s", fired[i][j] ? ships[i][j] : ".");
            }
            System.out.println();
        }
    }
}
