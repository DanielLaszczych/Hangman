import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Hangman extends Application {

    public static Stage stageToUse;
    public static GameControl gameControl;
    public static TopBar topBar;
    public static BottomBar bottomBar;
    public static AlphabetGrid alphabetGrid;
    public static GuessGrid guessGrid;
    public static StickFigure stickFigure;
    public static SaveGame saveGame;
    public static BorderPane layout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stageToUse = primaryStage;
        layout = new BorderPane();
        Scene scene = new Scene(layout, 1300, 800);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        gameControl = new GameControl();
        topBar = new TopBar(layout);
        topBar.listener();
        bottomBar = new BottomBar(layout);
        alphabetGrid = new AlphabetGrid(layout);
        guessGrid = new GuessGrid();
        stickFigure = new StickFigure(layout);
        alphabetGrid.listener(scene);
        saveGame = new SaveGame();

        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Hangman");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
