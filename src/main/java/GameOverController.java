import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController extends Main {

    @FXML
    Label scoreLbl;

    @FXML private void initialize(){

        scoreLbl.setText("Score: "+Score);
    }
}
