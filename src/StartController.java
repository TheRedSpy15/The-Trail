import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;

public class StartController extends Main{

    public Button button;

    public void handleButtonClick() throws IOException {

        cp.CareerPicker();
        Window.setScene(new Scene(careerAnchor));
    }
}
