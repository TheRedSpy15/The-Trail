import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class TravelClass extends Main {

    void travelSetup() throws IOException {

        // Links travel pane to FXML file
        travelPane = FXMLLoader.load(Main.class.getResource("TravelScene.fxml"));
    }
}