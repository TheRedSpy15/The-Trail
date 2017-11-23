import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class ThiefKilledController extends Main {

    public Button lootBtn;

    @FXML
    private void setLootBtn(){

        try {
            lootPane = FXMLLoader.load(Main.class.getResource("LootMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        lootScene = new Scene(lootPane);

        AlertWindow.setScene(lootScene);
    }
}
