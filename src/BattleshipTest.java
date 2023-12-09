import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BattleshipTest {
    @Test
    public void testBattleshipLength() {

        Battleship battleship = new Battleship();
        assertEquals(4, battleship.getLength(), "Battleship length should be 4.");
    }

    @Test
    public void testBattleshipType() {

        Battleship battleship = new Battleship();
        assertEquals("Battleship", battleship.getShipType(), "Battleship type should be 'Battleship'.");
    }

}