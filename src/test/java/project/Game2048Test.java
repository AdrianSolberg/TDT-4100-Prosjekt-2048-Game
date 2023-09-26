package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklasse for å teste om Game2048-klassen fungerer som den skal
 */
public class Game2048Test {

    Game2048 game;

    /**
     * Gjorde sånn at man kan velge seed dersom det er ønskelig i Game2048- og Board-klassen
     * Velger dermed en kjent seed, nemlig seed=1, slik at jeg vet hvordan brettet er ment til å se ut
     */

    @BeforeEach
    public void setUp() {
        game = new Game2048(1);
    }

    @Test
    public void testCheckWin() {
        assertEquals(false, game.checkWin(), "Not supposed to have a winning board after starting new game");
    }

    @Test
    public void testCheckLost() {
        assertEquals(false, game.checkLost(), "Not supposed to get a loosing board after starting new game");
    }

    @Test
    public void moveTest() {
        game.moveUp();
        assertEquals(4, game.getValue(0, 0), "The values wasn't moved up properly.");
        game.moveRight();
        assertEquals(4, game.getValue(0, 3), "The values wasn't moved right properly.");
        game.moveDown();
        assertEquals(2, game.getValue(3, 3), "The values wasn't moved down properly.");
        game.moveLeft();
        assertEquals(4, game.getValue(3, 0), "The values wasn't moved left properly.");
    }
    
}
