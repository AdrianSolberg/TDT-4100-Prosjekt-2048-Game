package project;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * En iterator som brukes til å iterere gjennom lister på formatet List<Tile[]>, slik som brettet i spillet
 */
public class BoardIterator implements Iterator<Tile> {

    private List<Tile[]> board;
    private int rowIndex;
    private int columnIndex;

    /**
     * Tar inn et brett som parameter, og setter de initielle felt-verdiene
     * @param board Brettet som blir bnrukt i spillet
     */
    public BoardIterator(List<Tile[]> board) {
        this.board = board;
        this.rowIndex = 0;
        this.columnIndex = 0;
    }

    /**
     * Sjekker om det flere elementer i brettet vi itererer over.
     * @param None Ingen parametere
     * @return boolean Returnerer hvorvidt det er flere elementer eller ikke
     */
    @Override
    public boolean hasNext() {
        if (rowIndex == Board.GRID_SIZE) {
            return false;
        }
        return true;
    }

    /**
     * Gir meg neste element i brettet vi itererer over.
     * Kaster unntak dersom det ikke er noe neste element.
     * @param None Ingen parametere
     * @return Tile Det neste Tile-objektet i brettet
     */
    @Override
    public Tile next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Tile tile = board.get(rowIndex)[columnIndex];
        
        if (columnIndex < Board.GRID_SIZE-1) {
            columnIndex++;
        }
        else {
            columnIndex = 0;
            rowIndex++;
        }
        return tile;
    }

    /**
     * Utløser unntak som sier at iteratoren ikke støtter remove().
     * @param None Ingen parametere
     * @throws UnsupportedOperationException Gir beskjed om at iteratoren ikke støtter metoden
     * @return None REturnerer ingenting
     */
    public void remove() {
        throw new UnsupportedOperationException("Can't remove strings from grid.");
    }
    
}
