import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class Store extends Main {

    private Media purchaseSound = new Media(new File("C:\\Users\\Hunter\\IdeaProjects\\The Trail JAVAFX VER\\src\\main\\resources\\Cha_Ching_Register-Muska666-173262285.wav").toURI().toString());

    void playPurchaseSound(){

        MediaPlayer playPurchaseSound = new MediaPlayer(purchaseSound);

        playPurchaseSound.setVolume(0.3f);
        playPurchaseSound.play();
    }

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
