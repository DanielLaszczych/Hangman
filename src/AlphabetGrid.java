import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class AlphabetGrid implements EventHandler<MouseEvent> {

    GridPane gridPane = new GridPane();

    public AlphabetGrid(BorderPane borderPane) {
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(30);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER_RIGHT);
        borderPane.setCenter(gridPane);
    }

    public void createGrid() {
        int row = 20;
        int letters = 0;
        Label x;
        List<Character> charactersLeft = Hangman.gameControl.getGameState().getCharacterLeft();
        List<Character> alphabet = Hangman.gameControl.getGameState().getAlphabet();
        for (Character c : alphabet) {
            String s = Character.toString(c);
            x = setSettings(s);
            if (!charactersLeft.contains(c)) {
                x.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                x.setDisable(true);
                x.setStyle("-fx-opacity: 1");
            }
            gridPane.addRow(row, x);
            x.setOnMouseClicked(this);
            letters++;
            if(letters % 10 == 0) {
                row+=5;
                GridPane.setMargin(x, new Insets(0, 20, 0, 0));
            }
        }
    }

    public Label setSettings(String s) {
        Label returnLabel = new Label();
        returnLabel.setText(s);
        returnLabel.setPrefWidth(30);
        returnLabel.setPrefHeight(30);
        returnLabel.setAlignment(Pos.CENTER);
        returnLabel.setScaleX(2);
        returnLabel.setScaleY(2);
        returnLabel.setPadding(new Insets(2.0, 3.0, 2.0, 3.0));
        returnLabel.setBackground(new Background(new BackgroundFill(Color.rgb(0, 216, 43), CornerRadii.EMPTY, Insets.EMPTY)));
        returnLabel.setTextFill(Color.BLACK);
        returnLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        return returnLabel;
    }

    public void enable() {
        gridPane.setVisible(true);
        gridPane.setDisable(false);
        createGrid();
        Hangman.guessGrid.createGrid();
    }

    public void disable() {
        gridPane.setVisible(false);
        gridPane.setDisable(true);
        gridPane.getChildren().clear();
    }

    public void listener(Scene scene) {
            scene.setOnKeyTyped(event -> {
                if(!gridPane.isDisabled() && Hangman.gameControl.getGameState().getGameActive()) {
                    Character c = event.getCharacter().toUpperCase().charAt(0);
                    List<Character> charactersLeft = Hangman.gameControl.getGameState().getCharacterLeft();
                    if(charactersLeft.contains(c)) {
                        int index = ((int) c) - 65;
                        Label x = (Label) gridPane.getChildren().get(index);
                        x.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                        x.setDisable(true);
                        x.setStyle("-fx-opacity: 1");
                        gridPane.getChildren().set(index, x);
                        Hangman.gameControl.getGameState().useCharacter(c);
                    }
                }
            });
    }

    public void handle(MouseEvent event) {
        if(event.getSource() instanceof javafx.scene.control.Label && Hangman.gameControl.getGameState().getGameActive()) {
            Label x = ((javafx.scene.control.Label)event.getSource());
            String s = x.getText();
            Character c = s.charAt(0);
            x.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            x.setDisable(true);
            x.setStyle("-fx-opacity: 1");
            Hangman.gameControl.getGameState().useCharacter(c);
        }
    }



}
