import javafx.fxml.FXML;
import javafx.scene.Scene;
import java.io.IOException;

public class StartController extends Main{

    // on button click, run career picker method to scene up career anchor, which is sat as scene
    @FXML
    private void handleButtonClick() throws IOException {

        cp.careerPicker();
        Window.setScene(new Scene(careerAnchor));
    }

    @FXML
    private void loadGame(){


    }
}
