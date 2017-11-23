import javafx.fxml.FXML;
import javafx.scene.Scene;

import java.io.IOException;

public class DescriptionSceneController extends Main {

    @FXML private void setContinueBtn() throws IOException {

        cp.careerPicker();
        MainWindow.setScene(new Scene(careerAnchor));
    }
}
