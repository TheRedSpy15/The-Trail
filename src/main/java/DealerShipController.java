import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DealerShipController extends Main {

    private static int amountOver = 0;
    @FXML private Label moneyLbl;

    public void blueTruck(){

        System.out.println("CLICKED");

        if (Money >= 5000){

            Money -= 5000;

            Score += 50000;

            carSpriteURL = "sprite6_0.png";

            System.out.println("Purchased blue Truck");
            alert.purchased("BLUE TRUCK");

            moneyLbl.setText("Money: $"+Money);
        }else if (Money < 5000){

            amountOver = (int) (Money - 5000);

            alert.notEnoughMoney(amountOver);

            System.out.println("Not enough money");
        }
    }

    public void rallyCar(){

        System.out.println("CLICKED");

        if (Money >= 3500){

            Money -= 3500;

            Score += 35000;

            carSpriteURL = "spr_rally_0.png";

            System.out.println("Purchased rally car");
            alert.purchased("RALLY CAR");

            moneyLbl.setText("Money: $"+Money);
        }else if (Money < 3500){

            amountOver = (int) (Money - 3500);

            alert.notEnoughMoney(amountOver);

            System.out.println("Not enough money");
        }
    }

    @FXML private void initialize(){

        moneyLbl.setText("Your money: $"+(int)Money);
    }
}
