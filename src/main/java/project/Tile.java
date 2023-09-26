package project;

import javafx.scene.paint.Color;

/**
 * Klasse som representerer en Tile på brettet
 */
public class Tile {
    private int value;
    private Color color;

    /**
     * Setter de initielle felt-verdiene
     * @param None Ingen parametere
     */
    public Tile() {
        this.value = 0;
        this.color = Color.WHITE;
    }

    /**
     * Getter for verdien til Tilen
     * @param None Ingen parametre
     * @return int Returnerer verdien til Tilen
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter for fargen til Tilen
     * @param None Ingen parametre
     * @return Color Returnerer fargen til Tilen
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter ny verdi for Tilen, og kaster unntak dersom verdien er ugyldig
     * @param value Den nye verdien til Tilen
     * @return Color Returnerer fargen til Tilen
     */
    public void setValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Negative value");
        }
        this.value = value;
        updateColor();
    }

    /**
     * Oppdaterer fargen til Tilen basert på egen verdi
     * @param None Ingen parametre
     * @return None Returnerer ingenting
     */
    public void updateColor() {
        if (value == 0) {
            color = Color.WHITE;
        }
        else if (value == 2) {
            color = Color.GREENYELLOW;
        }
        else if (value == 4) {
            color = Color.YELLOW;
        }
        else if (value == 8) {
            color = Color.ORANGE;
        }
        else if (value == 16) {
            color = Color.LIGHTSALMON;
        }
        else if (value == 32) {
            color = Color.PINK;
        }
        else if (value == 64) {
            color = Color.VIOLET;
        }
        else if (value == 128) {
            color = Color.LIGHTBLUE;
        }
        else if (value == 256) {
            color = Color.BLUE;
        }
        else if (value == 512) {
            color = Color.BROWN;
        }
        else if (value == 1024) {
            color = Color.SILVER;
        }
        else {
            color = Color.GOLD;
        }
    }

    /**
     * Bestemmer at Tile-objekter skal skrives ut som verdiene deres
     * @param None
     * @return String Verdien til Tilen
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
