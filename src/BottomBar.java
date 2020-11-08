import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BottomBar {

    ColorAdjust adjustBrightness = new ColorAdjust();
    Button startGame = new Button("Start Playing");
    HBox bottomBar = new HBox(8);

    public BottomBar(BorderPane borderPane) {
        startGame.setScaleX(1.3);
        startGame.setScaleY(1.3);
        startGame.setAlignment(Pos.CENTER);
        startGame.setPadding(new Insets(10, 10, 10, 10));
        bottomBar.getChildren().add(startGame);
        bottomBar.setAlignment(Pos.CENTER);
        borderPane.setBottom(bottomBar);
        bottomBar.setVisible(false);
        bottomBar.setPadding(new Insets(0, 0, 15, 0));
        bottomBar.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, new Insets(-15, 0, 0, 0))));
        start();
    }

    public void start() {
        startGame.setOnMouseClicked(e -> {
            disable();
            Hangman.alphabetGrid.enable();
            Hangman.gameControl.getGameState().setGameActive(true);
            Hangman.gameControl.getGameState().displayGuesses();
            Hangman.guessGrid.revealCharacterFromLoad();
            Hangman.stickFigure.updateStickFigure();
        });
    }

    public void enable() {
        bottomBar.setVisible(true);
        adjustBrightness.setBrightness(0);
        startGame.setEffect(adjustBrightness);
        startGame.setDisable(false);
        Hangman.alphabetGrid.disable();
        Hangman.stickFigure.disable();
        Hangman.topBar.disableSave();
    }

    public void disable() {
        adjustBrightness.setBrightness(0.2);
        startGame.setEffect(adjustBrightness);
        startGame.setDisable(true);
    }

}
