import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class Store extends Main {

    void storeMethod() {

        // Store
        try {
            storePane = FXMLLoader.load(Main.class.getResource("storeScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        storePane.setStyle("-fx-background-color: #36454f");

        // Store for Alert box stage
        try {
            midStorePane = FXMLLoader.load(Main.class.getResource("storeScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        midStorePane.setStyle("-fx-background-color: #36454f");

        // Sell scene for Alert box stage
        try {
            midSellStorePane = FXMLLoader.load(Main.class.getResource("SellScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        midSellStorePane.setStyle("-fx-background-color: #36454f");
    }
}
