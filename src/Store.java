import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class Store extends Main {

    void storeMethod() {

        // Store
        try {
            setStorePane(FXMLLoader.load(Main.class.getResource("storeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
