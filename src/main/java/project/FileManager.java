package project;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Klasse for lesing fra og skriving til fil
 */
public class FileManager {

    private String path;
    
    /**
     * Bestemmer filstien som skal brukes i metodene
     * @param path Filstien til ønsket fil
     */
    public FileManager(String path) {
        this.path = path;
    }

    /**
     * Lagrer tilstanden til det gitte spillet i filen som er gitt med filstien path
     * @param game Spillet man ønsker å lagre tilstanden til
     * @throws IOException Deklarerer at metoden kan utløse unntak
     * @return None Returnerer ingenting
     */
    public void save(Game2048 game) throws IOException { 
        PrintWriter pw = new PrintWriter(path);
        for (Tile tile : game) {
            pw.println(tile.getValue());
        }
        pw.close();
    }

    /**
     * Laster inn tilstanden fra filen som er gitt med filstien path, og gir den til det gitte spillet
     * @param game Spillet man ønsker at skal få tilstanden som er lest fra filen
     * @throws IOException Deklarerer at metoden kan utløse unntak
     * @return None Returnerer ingenting
     */
    public void load(Game2048 game) throws IOException{
        Scanner scanner = new Scanner(new File(path));
        scanner.useDelimiter("\n");
        for (Tile tile : game) {
            tile.setValue(scanner.nextInt());
        }
    }
}
