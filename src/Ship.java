/**
 * Ship is the abstract class for all the ships and sea tiles that will make up the game of Battleship. Ships of all
 * kinds are always considered to be facing up or to the left, meaning that any portion of the ship that is not the bow
 * will be at a higher numbered row or column than the bow.
 */
public abstract class Ship {
    /**
     * The column (0 to 9) which contains the bow (front) of the ship.
     */
    protected int bowColumn;

    /**
     * The row (0 to 9) which contains the bow (front) of the ship.
     */
    protected int bowRow;

    /**
     * hit is an array of four booleans telling whether that part of the ship has been hit.
     */
    protected boolean[] hit;

    /**
     * true if the ship occupies a single row, false otherwise.
     */
    protected boolean horizontal;

    /**
     * The number of tiles occupied by the ship.
     */
    protected int length;

    /**
     * @return the column of the bow (front) of the ship.
     */
    public int getBowColumn() {
        return this.bowColumn;
    }

    /**
     * @return the row of the bow (front) of the ship.
     */
    public int getBowRow() {
        return this.bowRow;
    }

    /**
     * @return the length of the ship.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @return the String representing the type of this ship.
     */
    public abstract String getShipType();

    /**
     * @return true if this boat is horizontal (facing left), false otherwise.
     */
    public boolean isHorizontal() {
        return this.horizontal;
    }

    /**
     * Returns true if this ship has been sunk and false otherwise.
     *
     * @return true if every part of the ship has been hit, and false otherwise.
     */
    public boolean isSunk() {
        for (boolean b : hit) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether this represents a valid placement configuration for this Ship in this Ocean. Ship objects in
     * an Ocean must not overlap other Ship objects or touch them vertically, horizontally, or diagonally. Additionally,
     * the placement cannot be such that the Ship would extend beyond the extents of the 2D array in which it is
     * placed.
     * <b>Calling this method should not actually change either the Ship or the provided Ocean.</b>
     *
     * @param row        the candidate row to place the ship
     * @param column     the candidate column to place the ship
     * @param horizontal whether to have the ship facing to the left
     * @param ocean      the Ocean in which this ship might be placed
     * @return true if it is valid to place this ship of this length in this location with this orientation, and false
     * otherwise.
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (horizontal) {
            if (column < 0 || column + length > Ocean.COL) {
                return false;
            }

            for (int i = row - 1; i < row + 2; ++i) {
                for (int j = column - 1; j < column + length + 1; ++j) {
                    if (ocean.isOccupied(i, j)) {
                        return false;
                    }
                }
            }
        } else {
            if (row < 0 || row + length > Ocean.ROW) {
                return false;
            }

            for (int i = row - 1; i < row + length + 1; ++i) {
                for (int j = column - 1; j < column + 2; ++j) {
                    if (ocean.isOccupied(i, j)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Puts the Ship in the Ocean. This will give values to the bowRow, bowColumn, and horizontal instance variables in
     * the Ship. This should also place a reference to this Ship in each of the one or more locations (up to four) in
     * the corresponding Ocean array this Ship is being placed in. Each of the references placed in the Ocean will be
     * identical since it is not possible to refer to a "part" of a ship, only the whole ship.
     *
     * @param row        the row to place the ship
     * @param column     the column to place the ship
     * @param horizontal whether to have the ship facing to the left
     * @param ocean      the Ocean in which this ship will be placed
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        setBowRow(row);
        setBowColumn(column);
        setHorizontal(horizontal);

        if (horizontal) {
            for (int i = 0; i < length; ++i) {
                ocean.getShipArray()[row][column + i] = this;
            }
        } else {
            for (int i = 0; i < length; ++i) {
                ocean.getShipArray()[row + i][column] = this;
            }
        }
    }

    /**
     * @param bowColumn the bowColumn to set
     */
    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    /**
     * @param bowRow the bowRow to set
     */
    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    /**
     * @param horizontal the horizontal to set
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * If a part of this ship occupies this coordinate, and if the ship hasn't been sunk, mark the part of the ship at
     * that coordinate as "hit".
     *
     * @param row    the row of the shot
     * @param column the column of the shot
     * @return true if this ship hasn't been sunk and a part of this ship occupies the given row and column and false
     * otherwise.
     */
    public boolean shootAt(int row, int column) {
        if (!isSunk()) {
            int i = -1;
            if (horizontal && bowRow == row) {
                i = column - bowColumn;
            } else if (!horizontal && bowColumn == column) {
                i = row - bowRow;
            }

            if (i >= 0 && i < length) {
                hit[i] = true;
                if (isSunk()) {
                    System.out.printf("You just sunk a %s.\n", getShipType());
                }
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a single character String to use in the Ocean's print method. This method should return "x" if the ship
     * has been sunk, and "S" if it has not yet been sunk. This method can only be used to print out locations in the
     * ocean that have been shot at; it should not be used to print locations that have not been the target of a shot
     * yet.
     *
     * @return "x" if this ship has been sunk, and "S" otherwise.
     */
    public String toString() {
        return isSunk() ? "x" : "S";
    }
}
