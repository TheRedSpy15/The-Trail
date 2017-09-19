import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CareerPosse extends Main {

    private Store str = new Store();

    void posseSetupMethod(){

        // Posse scene
        try {
            possePane = FXMLLoader.load(getClass().getResource("posseScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void careerPicker() throws IOException {

        // Career scene
        careerAnchor = FXMLLoader.load(getClass().getResource("CareerScene.fxml"));
    }
}
