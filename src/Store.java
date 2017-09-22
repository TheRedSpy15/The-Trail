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
    }
}
