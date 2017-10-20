import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class TravelClass extends Main {

    void travelSetup() {

        // Links travel pane to FXML file
        try {
            travelPane = FXMLLoader.load(Main.class.getResource("TravelScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Dealer ship pane
        try {
            dealerPane = FXMLLoader.load(Main.class.getResource("DealerShip.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dealerScene = new Scene(dealerPane);
    }
}
