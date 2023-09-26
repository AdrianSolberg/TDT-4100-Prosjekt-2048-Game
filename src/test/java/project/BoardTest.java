package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklasse for å sjekke om Board-klassen fungerer som den skal
 */
public class BoardTest {

    private Board board;

    /**
     * Gjorde sånn at man kan velge seed dersom det er ønskelig
     * Velger dermed en kjent seed, nemlig seed=1, slik at jeg vet hvordan brettet er ment til å se ut
     */

    @BeforeEach
    public void setUp() {
        board = new Board(1); 
    }

    @Test
    public void getValueTest() {
        assertEquals(2, board.getValue(1,0), "The tile on row 2 column 1 should have value 2");
        assertEquals(0, board.getValue(0, 0), "The tile on row 0 column 0 should have value 0");
        assertThrows(IllegalArgumentException.class, () -> board.getValue(-1, 0), "Can't have negative row");
        assertThrows(IllegalArgumentException.class, () -> board.getValue(0, Board.GRID_SIZE), "Can't have a higher number of columns than the size og the grid");
    }

    @Test
    public void setValueTest() {
        board.setValue(0, 0, 4);
        assertEquals(4, board.getValue(0,0), "The tile on row 0 column 0 should have value 4");
        assertThrows(IllegalArgumentException.class, () -> board.setValue(-1, 0, 2), "Can't have negative row");
        assertThrows(IllegalArgumentException.class, () -> board.setValue(0, Board.GRID_SIZE, 2), "Can't have a higher number of columns than the size og the grid");
    }

    @Test
    public void freeTialTest() {
        assertEquals(true, board.freeTial(), "The board is supposed to have a free tial after making a new board");
    }

    @Test
    public void placeNewTialTest() {
        board.placeNewTial(1);
        assertEquals(2, board.getValue(1, 2), "The new tile was placed wrong");
        board.placeNewTial(2);
        assertEquals(2, board.getValue(0, 3), "The new tile was placed wrong");
        assertEquals(2, board.getValue(3, 2), "The new tile was placed wrong");
    }
}
