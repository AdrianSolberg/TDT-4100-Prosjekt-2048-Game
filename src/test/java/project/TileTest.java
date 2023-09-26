package project;

import javafx.scene.paint.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklasse for å sjekke om Tile-klassen fungerer som den skal
 */
public class TileTest {

    Tile tile;
    
    @BeforeEach
    public void setUp() {
        tile = new Tile();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, tile.getValue(), "Wrong value from contructor");
        assertEquals(Color.WHITE, tile.getColor(), "Wrong color from constructor");
    }

    @Test
    public void testSetColor() {
        tile.setValue(4);
        assertEquals(4, tile.getValue(), "setValue() sets the wrong value");
        assertEquals(Color.YELLOW, tile.getColor(), "Wrong color for the given value");
        assertThrows(IllegalArgumentException.class, () -> tile.setValue(-1), "Not supposed to be able to set negative value for a tile");
    }
}
