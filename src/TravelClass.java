import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class TravelClass extends Main {

    void travelSetup() throws IOException {

        travelPane = FXMLLoader.load(Main.class.getResource("TravelScene.fxml"));

        midStorePane = FXMLLoader.load(Main.class.getResource("storeScene.fxml"));
    }
}
