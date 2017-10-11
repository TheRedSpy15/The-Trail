import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class CareerGang extends Main {

    protected void posseSetupMethod(){

        // Posse scene
        try {
            // assigns posse scene fxml file to posse pane object
            possePane = FXMLLoader.load(getClass().getResource("posseScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void careerPicker() throws IOException {

        // Career scene
        // assigns career scene fxml file to career anchor object
        careerAnchor = FXMLLoader.load(getClass().getResource("CareerScene.fxml"));
    }
}
