import javafx.fxml.FXML;

public class DealerShipController extends Main {

    private static int amountOver = 0;

    @FXML private void greenSportsCar(){

        if (Money >= 5000){

            Money -= 5000;

            Score += 50000;

            spriteURL = "@spr_car4_0.png";
        }else {

            amountOver = (int) (Money - 5000);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void rallyCar(){

        if (Money >= 3500){

            Money -= 3500;

            Score += 35000;

            spriteURL = "@spr_rally_0.png";
        }else {

            amountOver = (int) (Money - 3500);

            alert.notEnoughMoney(amountOver);
        }
    }
}
