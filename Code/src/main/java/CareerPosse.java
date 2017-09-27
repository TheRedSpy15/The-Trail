import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class CareerPosse extends Main {

    protected void posseSetupMethod(){

        // Posse scene
        try {

            // assigns posse scene fxml file to posse pane object
            possePane = FXMLLoader.load(getClass().getResource("posseScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // sets posse pane background color
        possePane.setStyle("-fx-background-color: #36454f");
    }

    protected void careerPicker() throws IOException {

        // Career scene
        // assigns career scene fxml file to career anchor object
        careerAnchor = FXMLLoader.load(getClass().getResource("CareerScene.fxml"));

        // sets career anchor background color
        careerAnchor.setStyle("-fx-background-color: #36454f");
    }
}
