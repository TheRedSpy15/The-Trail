import javafx.fxml.FXML;
import javafx.scene.Scene;

public class CareerController extends Main {

    // runs posse setup method, sets money to 1500, and sets scene to posse pane
    @FXML
    private void hitman() {

        Money = 3500;
        wage = 1000;
        cp.posseSetupMethod();
        MainWindow.setScene(new Scene(possePane));
    }

    // runs posse setup method, sets money to 1000, and sets scene to posse pane
    @FXML
    private void drugDealer() {

        Money = 2000;
        Score += 10000;
        wage = 500;
        cp.posseSetupMethod();
        MainWindow.setScene(new Scene(possePane));
    }

    // runs posse setup method, sets money to 500, and sets scene to posse pane
    @FXML
    private void thief() {

        Money = 500;
        Score += 15000;
        wage = (int) (Math.random() * 1000) + 250;
        cp.posseSetupMethod();
        MainWindow.setScene(new Scene(possePane));
    }
}
