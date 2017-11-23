import javafx.fxml.FXML;
import javafx.scene.Scene;
import java.io.IOException;

public class StartController extends Main{

    @FXML
    private void handleButtonClick() throws IOException {

        cp.careerPicker();
        MainWindow.setScene(new Scene(descripitionPane));
    }
}
