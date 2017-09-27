import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class CareerPosse extends Main {

    protected void posseSetupMethod(){

        // Posse scene
        try {
            possePane = FXMLLoader.load(getClass().getResource("posseScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        possePane.setStyle("-fx-background-color: #36454f");
    }

    protected void careerPicker() throws IOException {

        // Career scene
        careerAnchor = FXMLLoader.load(getClass().getResource("CareerScene.fxml"));
        careerAnchor.setStyle("-fx-background-color: #36454f");
    }
}
