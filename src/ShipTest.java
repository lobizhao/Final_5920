import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    public void testIsSunk() {
        Ship ship = new Submarine(); // 假设Submarine是Ship的一个具体子类
        ship.placeShipAt(0, 0, true, new Ocean());
        assertFalse(ship.isSunk(), "Ship should not be sunk initially.");
        ship.shootAt(0, 0);
        assertTrue(ship.isSunk(), "Ship should be sunk after being hit.");
    }

    @Test
    public void testOkToPlaceShipAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Destroyer(); // 假设Destroyer是Ship的一个具体子类
        assertTrue(ship.okToPlaceShipAt(0, 0, true, ocean), "Should be ok to place ship at (0, 0).");
    }

    @Test
    public void testPlaceShipAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Cruiser(); // 假设Cruiser是Ship的一个具体子类
        ship.placeShipAt(0, 0, true, ocean);
        assertSame(ocean.getShipArray()[0][0], ship, "Ship should be placed at (0, 0).");
    }

    @Test
    public void testShootAt() {
        Ship ship = new Battleship(); // 假设Battleship是Ship的一个具体子类
        ship.placeShipAt(0, 0, true, new Ocean());
        assertFalse(ship.shootAt(1, 0), "Should return false if shot misses the ship.");
        assertTrue(ship.shootAt(0, 0), "Should return true if shot hits the ship.");
    }

    @Test
    public void testToString() {
        Ship ship = new Submarine(); // 假设Submarine是Ship的一个具体子类
        ship.placeShipAt(0, 0, true, new Ocean());
        ship.shootAt(0, 0);
        assertEquals("x", ship.toString(), "Should return 'x' if the ship is sunk.");
    }

}