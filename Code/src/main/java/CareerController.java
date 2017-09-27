import javafx.fxml.FXML;
import javafx.scene.Scene;

public class CareerController extends Main {

    // runs posse setup method, sets money to 1500, and sets scene to posse pane
    @FXML
    private void carpenterHandle() {

        Money = 1500;
        cp.posseSetupMethod();
        Window.setScene(new Scene(possePane));
    }

    // runs posse setup method, sets money to 1000, and sets scene to posse pane
    @FXML
    private void minerHandle() {

        Money = 1000;
        cp.posseSetupMethod();
        Window.setScene(new Scene(possePane));
    }

    // runs posse setup method, sets money to 500, and sets scene to posse pane
    @FXML
    private void farmerHandle() {

        Money = 500;
        cp.posseSetupMethod();
        Window.setScene(new Scene(possePane));
    }
}
