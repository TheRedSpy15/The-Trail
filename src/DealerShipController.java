import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DealerShipController extends Store {

    private static int amountOver = 0;
    public Button backBtn2;
    public Label moneyLbl2;
    public Button backBtn1;
    @FXML private Label moneyLbl1;

    public void blueTruck(){

        if (Money >= 5000){

            Money -= 5000;

            Score += 50000;

            carSpriteURL = "sprite6_0.png";

            alert.specialPurchase("BLUE TRUCK");

            playPurchaseSound();

            moneyLbl1.setText("Money: $"+Money);
        }else if (Money < 5000){

            amountOver = (int) (Money - 5000);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void setBackBtn1(){

        AlertWindow.setScene(cityScene);
    }

    @FXML private void setBackBtn2(){

        AlertWindow.setScene(cityScene);
    }

    public void rallyCar(){

        if (Money >= 3500){

            Money -= 3500;

            Score += 35000;

            carSpriteURL = "spr_rally_0.png";

            alert.specialPurchase("RALLY CAR");

            playPurchaseSound();

            moneyLbl1.setText("Money: $"+Money);
        }else if (Money < 3500){

            amountOver = (int) (Money - 3500);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void initialize(){

        moneyLbl1.setText("Your money: $"+(int)Money);
        moneyLbl2.setText("Your money: $"+(int)Money);
    }
}
