import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class Store extends Main {

    void storeMethod() {

        // Store
        try {
            storePane = FXMLLoader.load(Main.class.getResource("storeScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        storeScene = new Scene(storePane);

        // Store for Alert box stage
        try {
            midStorePane = FXMLLoader.load(Main.class.getResource("storeScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sell scene for Alert box stage
        try {
            midSellStorePane = FXMLLoader.load(Main.class.getResource("SellScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Gun store
        try {
            gunStorePane = FXMLLoader.load(Main.class.getResource("GunStore.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gunStoreScene = new Scene(gunStorePane);
    }
}
