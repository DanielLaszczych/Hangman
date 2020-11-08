import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static int answer = 2;

    public static int display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(300);
        window.setMinWidth(300);
        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        Button cancelButton = new Button("Cancel");
        yesButton.setOnMouseClicked(e -> { answer = 0; window.close();
        });
        noButton.setOnMouseClicked(e -> { answer = 1; window.close();
        });
        cancelButton.setOnMouseClicked(e -> { answer = 2; window.close();
        });
        window.setOnCloseRequest(e -> { answer = 2; window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton, cancelButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

}
