import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveGame {
    public void saveGame(GameState gameState) {
        Stage primaryStage = Hangman.stageToUse;
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Hangman File (*.hng)", "*.hng");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            saveTextToFile(gameState, file);
    }
    }

    public void saveTextToFile(GameState gameState, File file) {
        try {
            FileWriter fw = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Valid Hangman Game File");
            pw.println(gameState.getGuessLeft());
            for(Character c : gameState.getCharacterLeft()) {
                pw.print(c);
            }
            pw.println();
            pw.println(gameState.getWordToGuess());
            pw.println(gameState.getLettersCorrect());
            for(Integer i : gameState.getCorrectIndexes()) {
                pw.print(i + " ");
            }
            pw.close();
            fw.close();
            Hangman.topBar.disableSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
