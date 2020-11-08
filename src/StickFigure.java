import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StickFigure {

    BorderPane layout;
    GridPane gridPane = new GridPane();
    Image setup = new Image("Stickman/StickFigureStep1.png");
    ImageView setupImage = new ImageView(setup);

    public StickFigure(BorderPane layout) {
        this.layout = layout;
        gridPane.setPadding(new Insets(120, 0, 40, 100));
        layout.setLeft(gridPane);
        setupImage.setVisible(false);
        gridPane.setAlignment(Pos.CENTER_RIGHT);
    }

    public void updateStickFigure() {
        int guessesLeft= Hangman.gameControl.getGameState().getGuessLeft();
        if (guessesLeft < 10 && guessesLeft >= 0) {
            int step = guessesLeft;
            step = 10 - step;
            this.setup = new Image("Stickman/StickFigureStep" + step + ".png");
            this.setupImage = new ImageView(setup);
            this.setupImage.setScaleX(1.5);
            this.setupImage.setScaleY(1.5);
            gridPane.getChildren().clear();
            gridPane.getChildren().add(setupImage);
            setupImage.setVisible(true);
        }
    }

    public void disable() {
        setupImage.setVisible(false);
    }

    public void enable() { setupImage.setVisible(true); }

}
