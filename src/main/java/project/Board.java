package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Klasse som representerer brettet i spillet
 */
public class Board implements Iterable<Tile> {
    public static final int GRID_SIZE = 4; 

    private List<Tile[]> board;
    private Random random;

    /**
     * Setter initialverdier for random, i tillegg til å opprette brettet
     * @param None Ingen parametre
     */
    public Board() {
        this.random = new Random();
        this.board = new ArrayList<>();
        initBoard();
    }

    /**
     * Setter initialverdier for random med en seed, i tillegg til å opprette brettet
     * @param seed Seen for random-feltet, slik at jeg kan gjennomføre tester på bestemte brett
     */
    public Board(int seed) {
        this.random = new Random(seed);
        this.board = new ArrayList<>();
        initBoard();
    }
    
    /**
     * Oppretter brettet med riktig størrelse
     * @param None Ingen parametre
     * @return None Returnerer ingenting
     */
    private void initBoard() {
        for (int i = 0; i < GRID_SIZE; i++) {
            Tile[] row = new Tile[GRID_SIZE];
            for (int j = 0; j < GRID_SIZE; j++) {
                row[j] = new Tile();
            }
            board.add(row);
        }

        placeNewTial(2);
    }

    /**
     * Returnerer enten 2 eller 4, med 90% sjanse for å returnere 2 og 10% sjanse for å returnere 4
     * @param None Ingen parametre
     * @return int REturnerer enten 2 eller 4
     */
    private int randomInitValue() {
        int val = random.nextInt(10);
        if (val == 0) {
            return 4;
        }
        return 2;
    }

    /**
     * Finner en tilfeldigf tom rute og gir den en verdi vha setValue() og randomInitValue()
     * @param num Antall nye Tiles man ønsker å plassere
     * @return None Returnerer ingenting
     */
    public void placeNewTial(int num) {
        if (num < 0) {
            throw new IllegalArgumentException();
        }
        int row;
        int column;
        for (int i = 0; i < num; i++) {
            row = random.nextInt(GRID_SIZE);
            column = random.nextInt(GRID_SIZE);
            while (getValue(row, column) != 0) {
                row = random.nextInt(GRID_SIZE);
                column = random.nextInt(GRID_SIZE);
            }
            setValue(row, column, randomInitValue());
        }
    }

    /**
     * Sjekker om det er noen tomme Tiles på brettet
     * @param None Ingen parametre
     * @return boolean True hvis det er minst én tom Tile, ellers false
     */
    public boolean freeTial() {
        for (Tile tile : this) {
            if (tile.getValue() == 0) {
                return true;
            }
        }
        return false;

    }

    /**
     * Gir deg verdien på Tilen på angitt rad og kolonne
     * @param row Raden Tilen ligger på
     * @param column Kolonnen Tilen ligger på
     * @return int Verdien til Tilen
     */
    public int getValue(int row, int column) {
        if (row < 0 || row >= GRID_SIZE || column < 0 || column >= GRID_SIZE) {
            throw new IllegalArgumentException();
        }
        return board.get(row)[column].getValue();
    }

    /**
     * Setter ny verdi for Tilen på angitt rad og kolonne
     * @param row Raden Tilen ligger på
     * @param column Kolonnen Tilen ligger på
     * @param value Verdien man vil gi til Tilen
     */
    public void setValue(int row, int column, int value) {
        if (row < 0 || row >= GRID_SIZE || column < 0 || column >= GRID_SIZE) { //Hva med value?
            throw new IllegalArgumentException();
        }
        board.get(row)[column].setValue(value);
    }

    /**
     * Gir deg en iterator som kan iterere gjennom brettet
     * @param None Ingen parametre
     * @return Iterator<Tile> Iterator som kan iterere gjennom brettet
     */
    @Override
    public Iterator<Tile> iterator() {
        return new BoardIterator(board);
    }

    /**
     * Definerer hvordan et Board-objekt skal skrives ut
     * @param None Ingen parametre
     * @return String Board-objektet på definert String-format
     */
    @Override
    public String toString() {
        String string = board.stream()
        .map(a -> Arrays.toString(a))
        .reduce((a,b) -> a + "\n" + b)
        .get();
        return string;
    }
}
