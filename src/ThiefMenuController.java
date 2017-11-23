import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThiefMenuController extends Main {

    public Button captureBtn;
    public Button attackBtn;
    public Button ignoreBtn;
    public Label loseLbl;
    private int amount = 0;

    @FXML private void initialize(){

        amount = (int) (Math.random() * 500);

        if (amount > Money) amount = (int) Money;

        loseLbl.setText("Would lose: $"+amount);
    }

    @FXML public void setIgnoreBtn(){

        Money -= amount;

        AlertWindow.close();
    }

    @FXML public void setCaptureBtn(){

        int chance = (int) (Math.random() * 100);

        if (chance >= 50) {

            alert.alert("You have capture the thief, turn them in for a reward");

            capturedThieves += 1;
        }else {

            alert.alert("You failed catch them and they got away");

            Money -= amount;
        }
    }

    @FXML public void setAttackBtn(){

        AlertWindow.setScene(shootOutScene);
    }
}
