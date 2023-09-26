package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * Testklasse for å sjekke om FileManager-klassen fungerer som den skal
 */
public class FileManagerTest {
    /**
     * Gjorde sånn at man kan velge seed dersom det er ønskelig
     * Velger dermed en kjente seeds, slik at jeg vet hvordan brettet er ment til å se ut
     */
    @Test
    public void saveTest() throws IOException {
        Game2048 game = new Game2048(1);
        FileManager fm = new FileManager("/Users/adriansolberg/tdt-4100-prosjekt/src/test/java/project/testFile.txt");

        fm.save(game);  
        Scanner s1 = new Scanner(new File("/Users/adriansolberg/tdt-4100-prosjekt/src/test/java/project/testFile.txt"));
        Scanner s2 = new Scanner(new File("/Users/adriansolberg/tdt-4100-prosjekt/src/test/java/project/correctFile.txt"));

        s1.useDelimiter("\n");
        s2.useDelimiter("\n");

        List<Integer> actual = new ArrayList<>();
        while (s1.hasNextInt()) {
            actual.add(s1.nextInt());
        }

        List<Integer> expected = new ArrayList<>();
        while (s2.hasNextInt()) {
            expected.add(s2.nextInt());
        }

        assertEquals(expected.size(), actual.size(), "Supposed to be as many integers in each file");

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i), "The integers are supposed to be equal.");
        }
    }

    @Test
    public void loadTest() throws IOException {
        Game2048 game = new Game2048();
        FileManager fm = new FileManager("/Users/adriansolberg/tdt-4100-prosjekt/src/test/java/project/seed2.txt");
        Game2048 correctGame = new Game2048(2);

        fm.load(game);

        for (int i = 0; i < Board.GRID_SIZE; i++) {
            for (int j = 0; j < Board.GRID_SIZE; j++) {
                assertEquals(game.getValue(i, j), correctGame.getValue(i, j), "Supposed to be the same values");
            }
        }
    }
    
}
