package project;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * Controller-klasse som binder Modellen sammen med brukergrensesnittet
 */
public class AppController {
    
    @FXML private GridPane grid;
    @FXML private Label message;
    @FXML private Button sgButton, lgButton;
    private Game2048 game;

    /**
     * Lager et nytt spill og sørger for at brukergrensesnittet samsvarer med tilstanden til brettet
     * @param None Ingen parameter
     */
    @FXML
    public void newGame() {
        this.game = new Game2048();
        updateGrid();
        sgButton.setDisable(false);
        lgButton.setDisable(false);
        grid.requestFocus();
        message.setText("");
    }

    /**
     * Skyver verdiene på brettet i modellen i den retningen piltasten som ble trykket ned peker
     * Oppdaterer så GridPanen i brukergrensesnittet
     * @param evt KeyEventet som førte til at metoden ble kallt
     * @return None Returnerer ingenting
     */
    @FXML
    public void keyPress(KeyEvent evt) {
        if (evt.getCode() == KeyCode.RIGHT) {
            evt.consume();
            game.moveRight();
            updateGrid();
        }
        if (evt.getCode() == KeyCode.LEFT) {
            evt.consume();
            game.moveLeft();
            updateGrid();
        }
        if (evt.getCode() == KeyCode.UP) {
            evt.consume();
            game.moveUp();
            updateGrid();
        }
        if (evt.getCode() == KeyCode.DOWN) {
            evt.consume();
            game.moveDown();
            updateGrid();
        }
    }

    /**
     * Oppdaterer GridPane i brukergrensesnittet slik at det er konsistent med brettet i modellen
     * @param None Ingen Parameter
     * @return None Returnerer ingenting
     */
    private void updateGrid() {
        int index = 0;
        // Itererer gjennom Tile-objektene i modellen og rutene i GridPanen samtidig, og oppdaterer verdiene
        for (Tile tile : game) {
            Node node = grid.getChildren().get(index);
            // Nodene i GridPanen er alle satt til å være TextFireld, men er greit å validere likevel
            if (node instanceof TextField) {

                TextField textField = (TextField) node;
                if (tile.getValue() != 0) {
                    textField.setText(String.valueOf(tile.getValue()));
                }
                else {
                    textField.setText("");
                }

                // Setter riktig farge for ruta i GridPanen
                String colorCode = String.format("#%02X%02X%02X", (int) (tile.getColor().getRed() * 255), (int) (tile.getColor().getGreen() * 255), (int) (tile.getColor().getBlue() * 255));
                textField.setStyle("-fx-background-color: " + colorCode);
            }
            index++;
        }

        // Oppdaterer GridPanen
        grid.requestLayout();

        // Sjekker om man har vunnet eller tapt, og gir isåfall tilhørende melding
        if (game.checkWin()) {
            message.setText("You won!");
        }
        else if (game.checkLost()) {
            message.setText("You lost!");
        }
    }

    /**
     * Laster inn spill fra fil
     * Legg merke til at jeg bruker try og catch, da for å fange eventuelle IOExceptions
     * @param None Ingen paramter
     * @return None Returnerer ingenting
     */
    @FXML
    public void loadGame() {
        grid.requestFocus();
        message.setText("");
        try {
            FileManager fm = new FileManager("/Users/adriansolberg/tdt-4100-prosjekt/src/main/java/project/GameSave.txt");
            fm.load(game);
            updateGrid();
            if (game.checkWin()) {
                message.setText("You won!");
            }
            else if (game.checkLost()) {
                message.setText("You lost!");
            }
        }
        catch (IOException ioe) {
            // Sett i forelesning
            Alert alert = new Alert(AlertType.WARNING, "Problemer med lasting fra fil");
            alert.show();
        }
    }

    /**
     * Lagrer tilstanden til spillet til fil
     * Legg merke til at jeg bruker try og catch, da for å fange eventuelle IOExceptions
     * @param None Ingen paramter
     * @return None Returnerer ingenting
     */
    public void saveGame() {
        grid.requestFocus();
        try {
            FileManager fm = new FileManager("/Users/adriansolberg/tdt-4100-prosjekt/src/main/java/project/GameSave.txt");
            fm.save(game);
        }
        catch (IOException ioe) {
            // Sett i forelesning
            Alert alert = new Alert(AlertType.WARNING, "Problemer med lagring til fil");
            alert.show();
        }
    }
}
