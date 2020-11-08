import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TopBar {

    private HBox topBar = new HBox(8);
    private ColorAdjust lowerBrightness = new ColorAdjust();
    private Button newButton = new Button();
    private Button loadButton = new Button();
    private Button saveButton = new Button();
    private Button exitButton = new Button();

    private Image newImg = new Image("New.png");
    private ImageView newView = new ImageView(newImg);
    private Image loadImg = new Image("Load.png");
    private ImageView loadView = new ImageView(loadImg);
    private Image saveImg = new Image("Save.png");
    private ImageView saveView = new ImageView(saveImg);
    private Image exitImg = new Image("Exit.png");
    private ImageView exitView = new ImageView(exitImg);

    public TopBar(BorderPane borderPane) {
        setImageScales();
        newButton.setPadding(new Insets(15));
        loadButton.setPadding(new Insets(15));
        saveButton.setPadding(new Insets(15));
        exitButton.setPadding(new Insets(15));
        lowerBrightness.setBrightness(0.2);
        newButton.setGraphic(newView);
        loadButton.setGraphic(loadView);
        saveButton.setGraphic(saveView);
        saveButton.setEffect(lowerBrightness);
        saveButton.setDisable(true);
        exitButton.setGraphic(exitView);
        topBar.getChildren().addAll(newButton, loadButton, saveButton, exitButton);
        topBar.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        topBar.setPadding(new Insets(10));
        borderPane.setTop(topBar);
    }

    private void setImageScales() {
        newView.setScaleX(1.5);
        newView.setScaleY(1.5);
        loadView.setScaleX(1.5);
        loadView.setScaleY(1.5);
        saveView.setScaleX(1.5);
        saveView.setScaleY(1.5);
        exitView.setScaleX(1.5);
        exitView.setScaleY(1.5);
    }

    public void newButtonListener() {
        newButton.setOnMouseClicked(e -> {
            if(Hangman.gameControl.getGameState().getGameActive() && !saveButton.isDisabled()) {
                int option = ConfirmBox.display("Save Game", "Would you like to save the game?");
                if(option == 0) {
                    Hangman.saveGame.saveGame(Hangman.gameControl.getGameState());
                    Hangman.gameControl.reset();
                    Hangman.bottomBar.enable();
                } else if (option == 1) {
                    Hangman.gameControl.reset();
                    Hangman.bottomBar.enable();
                } else if (option == 2) {
                }
            }
            else {
                Hangman.gameControl.reset();
                Hangman.bottomBar.enable();
            }
        });
    }

    public void saveButtonListener() {
        saveButton.setOnMouseClicked(e -> {
            Hangman.saveGame.saveGame(Hangman.gameControl.getGameState());
        });
    }

    public void loadButtonListener() {
        loadButton.setOnMouseClicked(e -> {
            if(Hangman.gameControl.getGameState().getGameActive() && !saveButton.isDisabled()) {
                int option = ConfirmBox.display("Save Game", "Would you like to save the game?");
                if(option == 0) {
                    Hangman.saveGame.saveGame(Hangman.gameControl.getGameState());
                    loadGame();
                } else if (option == 1) {
                    loadGame();
                } else if (option == 2) {
                }
            }
            else {
                loadGame();
            }
        });
    }

    public void exitButtonListener() {
        exitButton.setOnMouseClicked(e -> {
            if(Hangman.gameControl.getGameState().getGameActive() && !saveButton.isDisabled()) {
                int option = ConfirmBox.display("Save Game", "Would you like to save the game?");
                if(option == 0) {
                    Hangman.saveGame.saveGame(Hangman.gameControl.getGameState());
                    Hangman.stageToUse.close();
                } else if (option == 1) {
                    Hangman.stageToUse.close();
                } else if (option == 2) {
                }
            }
            else {
                Hangman.stageToUse.close();
            }
        });
    }

    public void loadGame() {
        Stage primaryStage = Hangman.stageToUse;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Hangman File (*.hng)", "*.hng");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
//            Hangman.alphabetGrid.disable();
//            Hangman.stickFigure.disable();
//            Hangman.topBar.disableSave();
            GameState gameState = ReadFile.loadGame(file);
            Hangman.gameControl.loadGame(gameState);
            Hangman.bottomBar.enable();
//            Hangman.alphabetGrid.enable();
//            Hangman.gameControl.getGameState().displayGuesses();
//            Hangman.guessGrid.revealCharacterFromLoad();
//            Hangman.stickFigure.updateStickFigure();
//            Hangman.bottomBar.disable();
        }
    }

    public void listener() {
        newButtonListener();
        saveButtonListener();
        loadButtonListener();
        exitButtonListener();
    }

    public void disableSave() {
        saveButton.setDisable(true);
    }

    public void enableSave() {
        saveButton.setDisable(false);
    }

}
