import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CareerPosse extends Main {

    private Store str = new Store();

    void PosseSetupMethod(){

        // Posse scene
        try {
            possePane = FXMLLoader.load(getClass().getResource("posseScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void CareerPicker() throws IOException {

        // Career scene
        careerAnchor = FXMLLoader.load(getClass().getResource("CareerScene.fxml"));
    }
}
