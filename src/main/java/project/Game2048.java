package project;

import java.util.Iterator;

/**
 * Klasse som implementerer selve logikken for appen/spillet
 */
public class Game2048 implements Iterable<Tile> {

    private Board board;

    /**
     * Oppretter brettet spillet skal foregår på
     */
    public Game2048() {
        this.board = new Board();
    }

    /**
     * Oppretter brettet med en bestemt seed, slik at man kan få et bestemt brett
     * @param seed Seed for random-felt
     */
    public Game2048(int seed) {
        this.board = new Board(seed);
    }

    /**
     * Sjekker om man har tapt spillet
     * @param None Ingen parametre
     * @return boolean True hvis man har tapt, ellers false
     */
    public boolean checkLost() {
        // Hvis det er en tom Tial, så kan man ikke ah tapt
        if (board.freeTial()) {
            return false;
        }
        
        // Sjekker om det er noen Tiles med lik verdi som er inntil hverandre
        for (int i = 0; i < Board.GRID_SIZE; i++) {
            for (int j = 0; j < Board.GRID_SIZE; j++) {
                if (i != 0) {
                    if (board.getValue(i, j) == board.getValue(i-1, j)) {
                        return false;
                    }
                }
                if (i < Board.GRID_SIZE-1) {
                    if (board.getValue(i, j) == board.getValue(i+1, j)) {
                        return false;
                    }
                }
                if (j != 0) {
                    if (board.getValue(i, j) == board.getValue(i, j-1)) {
                        return false;
                    }
                }
                if (j < Board.GRID_SIZE-1) {
                    if (board.getValue(i, j) == board.getValue(i, j+1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Sjekker om man har vunnet spillet
     * @param Ingen parametre
     * @return boolean True om man har vunnet, ellers false
     */
    public boolean checkWin() {
        // Er nok å sjekke om en av Tile-objektene har verdi 2048
        for (Tile tile : board) {
            if (tile.getValue() == 2048) {
                return true;
            }
        }
        return false;
    }

    /**
     * Skyver alle verdiene til høyre på brettet, og legger sammen eventuelle like verdier som er ved siden av hverandre
     * @param None Ingen parametre
     * @return None Returnerer ingenting
     */
    public void moveRight() {
        // Hvis man har tapt skal man ikke kunne gjøre noe mer
        if (checkLost()) {
            return;
        }
        for (int i = 0; i < Board.GRID_SIZE; i++) {
            for (int j = Board.GRID_SIZE-2; j >= 0; j--) {
                // Skyver verdiene til høyre på brettet
                int freeTial = j;
                while (freeTial+1 < Board.GRID_SIZE && board.getValue(i, freeTial+1) == 0) { //Må ha den første betingelsen først, for ellers kræsjer det med IndexOutOfBounce
                    freeTial++;
                }
                if (freeTial != j) {
                    board.setValue(i, freeTial, board.getValue(i, j));
                    board.setValue(i, j, 0);
                }

                // Legger sammen eventuelle like verdier som er ved siden av hverandre
                if (freeTial+1 < Board.GRID_SIZE) {
                    if (board.getValue(i, freeTial) == board.getValue(i, freeTial+1)) {
                        board.setValue(i, freeTial+1, board.getValue(i, freeTial)+board.getValue(i, freeTial+1));
                        board.setValue(i, freeTial, 0);
                    }
                }
            }
        }

        // Sjekker om man har vunnet
        if (checkWin()) {
            return;
        }

        // Dersom det er flere ledige Tials, legger man til en ny verdi
        if (board.freeTial()) {
            board.placeNewTial(1);
        }
    }

    /**
     * Skyver alle verdiene til venstre på brettet, og legger sammen eventuelle like verdier som er ved siden av hverandre
     * @param None Ingen parametre
     * @return None Returnerer ingenting
     */
    public void moveLeft() {
        // Hvis man har tapt skal man ikke kunne gjøre noe mer
        if (checkLost()) {
            return;
        }
        for (int i = 0; i < Board.GRID_SIZE; i++) {
            for (int j = 1; j < Board.GRID_SIZE; j++) {
                // Skyver verdiene til venstre på brettet
                int freeTial = j;
                while (freeTial-1 >= 0 && board.getValue(i, freeTial-1) == 0) { //Må ha den første betingelsen først, for ellers kræsjer det med IndexOutOfBounce
                    freeTial--;
                }
                if (freeTial != j) {
                    board.setValue(i, freeTial, board.getValue(i, j));
                    board.setValue(i, j, 0);
                }

                // Legger sammen eventuelle like verdier som er ved siden av hverandre
                if (freeTial-1 >= 0) {
                    if (board.getValue(i, freeTial) == board.getValue(i, freeTial-1)) {
                        board.setValue(i, freeTial-1, board.getValue(i, freeTial)+board.getValue(i, freeTial-1));
                        board.setValue(i, freeTial, 0);
                    }
                }
            }
        }

        // Sjekker om man har vunnet
        if (checkWin()) {
            return;
        }

        // Dersom det er flere ledige Tials, legger man til en ny verdi
        if (board.freeTial()) {
            board.placeNewTial(1);
        }
    }

    /**
     * Skyver alle verdiene oppover på brettet, og legger sammen eventuelle like verdier som ligger ovenfor hverandre
     * @param None Ingen parametre
     * @return None Returnerer ingenting
     */
    public void moveUp() {
        // Hvis man har tapt skal man ikke kunne gjøre noe mer
        if (checkLost()) {
            return;
        }
        for (int i = 1; i < Board.GRID_SIZE; i++) {
            for (int j = 0; j < Board.GRID_SIZE; j++) {
                // Skyver verdiene opp på brettet
                int freeTial = i;
                while (freeTial-1 >= 0 && board.getValue(freeTial-1, j) == 0) { //Må ha den første betingelsen først, for ellers kræsjer det med IndexOutOfBounce
                    freeTial--;
                }
                if (freeTial != i) {
                    board.setValue(freeTial, j, board.getValue(i, j));
                    board.setValue(i, j, 0);
                }

                // Legger sammen eventuelle like verdier som ligger ovenfor hverandre
                if (freeTial-1 >= 0) {
                    if (board.getValue(freeTial, j) == board.getValue(freeTial-1, j)) {
                        board.setValue(freeTial-1, j, board.getValue(freeTial, j)+board.getValue(freeTial-1, j));
                        board.setValue(freeTial, j, 0);
                    }
                }
            }
        }

        // Sjekker om man har vunnet
        if (checkWin()) {
            return;
        }

        // Dersom det er flere ledige Tials, legger man til en ny verdi        
        if (board.freeTial()) {
            board.placeNewTial(1);
        }
    }

    /**
     * Skyver alle verdiene nedover på brettet, og legger sammen eventuelle like verdier som ligger nedenfor hverandre
     * @param None Ingen parametre
     * @return None Returnerer ingenting
     */
    public void moveDown() {
        // Hvis man har tapt skal man ikke kunne gjøre noe mer
        if (checkLost()) {
            return;
        }
        for (int i = Board.GRID_SIZE-2; i >= 0; i--) {
            for (int j = 0; j < Board.GRID_SIZE; j++) {
                // Skyver verdiene ned på brettet
                int freeTial = i;
                while (freeTial+1 < Board.GRID_SIZE && board.getValue(freeTial+1, j) == 0) { //Må ha den første betingelsen først, for ellers kræsjer det med IndexOutOfBounce
                    freeTial++;
                }
                if (freeTial != i) {
                    board.setValue(freeTial, j, board.getValue(i, j));
                    board.setValue(i, j, 0);
                }

                // Legger sammen eventuelle like verdier som ligger nedenfor hverandre
                if (freeTial+1 < Board.GRID_SIZE) {
                    if (board.getValue(freeTial, j) == board.getValue(freeTial+1, j)) {
                        board.setValue(freeTial+1, j, board.getValue(freeTial, j)+board.getValue(freeTial+1, j));
                        board.setValue(freeTial, j, 0);
                    }
                }
            }
        }

        // Sjekker om man har vunnet
        if (checkWin()) {
            return;
        }

        // Dersom det er flere ledige Tials, legger man til en ny verdi 
        if (board.freeTial()) {
            board.placeNewTial(1);
        }
    }

    /**
     * Gir verdien på angitt posisjon på brettet
     * @param row Raden verdien ligger på
     * @param column Kolonnen verdien ligger på
     * @return int Verdien på angitt Tial
     */
    public int getValue(int row, int column) {
       return board.getValue(row, column);
    }

    /**
     * Gir en iterator for å iterere gjennom brettet
     * @param None Ingen parameter
     * @return Interator<Tile> Iterator for å iterere gjennom brettet
     */
    @Override
    public Iterator<Tile> iterator() {
        return board.iterator();
    }

    /**
     * Definerer hvordan et Game2048-objekt skal skrives ut
     * @param None Ingen parameter
     * @return String Game2048-objekt på definert String-format
     */
    @Override
    public String toString() {
        return board.toString();
    }
}