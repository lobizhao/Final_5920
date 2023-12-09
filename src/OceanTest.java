import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OceanTest {
    @Test
    public void testOceanInitialization() {
        Ocean ocean = new Ocean();
        for (int i = 0; i < Ocean.ROW; i++) {
            for (int j = 0; j < Ocean.COL; j++) {
                assertTrue(ocean.getShipArray()[i][j] instanceof EmptySea, "Ocean should be initialized with EmptySea.");
            }
        }
    }

    @Test
    public void testPlaceAllShipsRandomly() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        int shipCount = 0;
        for (int i = 0; i < Ocean.ROW; i++) {
            for (int j = 0; j < Ocean.COL; j++) {
                if (!(ocean.getShipArray()[i][j] instanceof EmptySea)) {
                    shipCount++;
                }
            }
        }
        assertEquals(20, shipCount, "Total ship spaces should be 20.");
    }

    @Test
    public void testShootAt() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        ocean.shootAt(0, 0);
        assertEquals(1, ocean.getShotsFired(), "Shots fired should be incremented.");
    }

    @Test
    public void testIsOccupied() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        boolean occupied = ocean.isOccupied(0, 0);
        assertEquals(!(ocean.getShipArray()[0][0] instanceof EmptySea), occupied, "isOccupied should match ship presence.");
    }

    @Test
    public void testIsGameOver() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        // Assuming a method to forcefully sink all ships for testing
        sinkAllShips(ocean);
        assertTrue(ocean.isGameOver(), "Game should be over when all ships are sunk.");
    }

    // Helper method to sink all ships for testing
    private void sinkAllShips(Ocean ocean) {
        for (int i = 0; i < Ocean.ROW; i++) {
            for (int j = 0; j < Ocean.COL; j++) {
            ocean.shootAt(i, j);
            }
        }
    }

}