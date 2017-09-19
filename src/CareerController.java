import javafx.scene.Scene;
import javafx.scene.control.Button;

public class CareerController extends Main {

    public Button carpenterBtn;
    public Button minerBtn;

    public void carpenterHandle() {

        Money = 5000;
        cp.posseSetupMethod();
        Window.setScene(new Scene(possePane));
    }

    public void minerHandle() {

        Money = 2500;
        cp.posseSetupMethod();
        Window.setScene(new Scene(possePane));
    }
}
