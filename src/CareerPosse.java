import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CareerPosse extends Main {

    protected void posseSetupMethod(){

        // Posse scene
        try {
            setPossePane(FXMLLoader.load(getClass().getResource("posseScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void careerPicker() throws IOException {

        // Career scene
        setCareerAnchor(FXMLLoader.load(getClass().getResource("CareerScene.fxml"))) ;
    }
}
