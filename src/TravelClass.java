import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TravelClass extends Main {

    void TravelSetup() throws IOException {

        travelPane = FXMLLoader.load(Main.class.getResource("TravelScene.fxml"));
    }
}
