import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class GuessGrid {

    GridPane gridPane = (GridPane)Hangman.layout.getCenter();
    HBox hbox = new HBox();

    public void createGrid() {
        List<Character> characters = Hangman.gameControl.getGameState().getWordsToGuessAsList();
        Label x;
        int row = 5;
        int letters = 0;
        for (char c : characters) {
            x = setSetting(c);
            gridPane.addRow(row, x);
            GridPane.setMargin(x, new Insets(16.0, 0, 0, 0));
            letters++;
            if(letters % 10 == 0) {
                GridPane.setMargin(x, new Insets(16.0, 20, 0, 0));
                row+=2;
            }
        }
    }

    private Label setSetting(Character c) {
        String s = c.toString();
        Label returnLabel = new Label();
        returnLabel.setText(s);
        returnLabel.setPrefWidth(30);
        returnLabel.setPrefHeight(30);
        returnLabel.setScaleX(2);
        returnLabel.setScaleY(2);
        returnLabel.setAlignment(Pos.CENTER);
        returnLabel.setPadding(new Insets(4.0, 3.0, 4.0, 3.0));
        returnLabel.setTextFill(Color.BLACK);
        returnLabel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        returnLabel.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        return returnLabel;
    }

    public void revealCharacters(List<Integer> index) {
        for(Integer x : index) {
            x = x + 26;
            Label temp = (Label)gridPane.getChildren().get(x);
            temp.setTextFill(Color.WHITE);
            gridPane.getChildren().set(x, temp);
            Hangman.gameControl.getGameState().incrementLettersCorrect();
        }
    }

    public void revealRemainingCharacters() {
        for(int i = 0; i < (Hangman.gameControl.getGameState().lengthOfWord()); i++) {
            int index = i + 26;
            Label temp = (Label)gridPane.getChildren().get(index);
            if (temp.getTextFill() == Color.BLACK) {
                temp.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                gridPane.getChildren().set(index, temp);
            }
        }
    }

    public void revealCharacterFromLoad() {
        for(Integer i : Hangman.gameControl.getGameState().getCorrectIndexes()) {
            int index = i + 26;
            Label temp = (Label)gridPane.getChildren().get(index);
            temp.setTextFill(Color.WHITE);
            gridPane.getChildren().set(index, temp);
        }
    }

}

