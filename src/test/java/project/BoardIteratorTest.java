package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklasse som tester at BoardIterator-klassen fungerer som den skal
 */
public class BoardIteratorTest {
    
    List<Tile[]> board;
    BoardIterator boardIterator;

    // Lager et eget List<Tile[]>-objekt med samme form som et valig brett
    // Dette er forsi nnkapslingen av Board-objekter ikke vil tillate å sende selve brettet inn til iteratoren
    @BeforeEach
    public void setUp() {
        Tile t1 = new Tile();
        Tile t2 = new Tile();
        Tile t3 = new Tile();
        Tile t4 = new Tile();
        Tile t5 = new Tile();
        Tile t6 = new Tile();
        Tile t7 = new Tile();
        Tile t8 = new Tile();
        Tile t9 = new Tile();
        Tile t10 = new Tile();
        Tile t11 = new Tile();
        Tile t12 = new Tile();
        Tile t13 = new Tile();
        Tile t14 = new Tile();
        Tile t15 = new Tile();
        Tile t16 = new Tile();

        t1.setValue(1);
        t2.setValue(2);
        t3.setValue(3);
        t4.setValue(4);
        t5.setValue(5);
        t6.setValue(6);
        t7.setValue(7);
        t8.setValue(8);
        t9.setValue(9);
        t10.setValue(10);
        t11.setValue(11);
        t12.setValue(12);
        t13.setValue(13);
        t14.setValue(14);
        t15.setValue(15);
        t16.setValue(16);

        Tile[] l1 = {t1,t2,t3,t4};
        Tile[] l2 = {t5,t6,t7,t8};
        Tile[] l3 = {t9,t10,t11,t12};
        Tile[] l4 = {t13,t14,t15,t16};
        board = new ArrayList<>(List.of(l1,l2,l3,l4));
        boardIterator = new BoardIterator(board);
    }

    @Test
    public void nextTest() {
        assertEquals(true, boardIterator.hasNext(), "The iterator should have next object");
        assertEquals(1, boardIterator.next().getValue(), "The first tile should have value 1");
        for (int i = 1; i < Board.GRID_SIZE*Board.GRID_SIZE; i++) {
            boardIterator.next();
        }
        assertEquals(false, boardIterator.hasNext(), "There are no thirtheenth tile");
        assertThrows(NoSuchElementException.class, () -> boardIterator.next(), "Should throw NoSuchElementException when asking for a Tile that does not exist");
    }
}
