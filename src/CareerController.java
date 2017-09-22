import javafx.fxml.FXML;
import javafx.scene.Scene;

public class CareerController extends Main {

    @FXML
    private void carpenterHandle() {

        Money = 3000;
        cp.posseSetupMethod();
        Window.setScene(new Scene(possePane));
    }

    @FXML
    private void minerHandle() {

        Money = 1500;
        cp.posseSetupMethod();
        Window.setScene(new Scene(possePane));
    }

    @FXML
    private void farmerHandle() {

        Money = 1000;
        cp.posseSetupMethod();
        Window.setScene(new Scene(possePane));
    }
}
