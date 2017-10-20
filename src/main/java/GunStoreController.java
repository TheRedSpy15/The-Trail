import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class GunStoreController extends Main {

    private static int amountOver = 0;
    @FXML private Label moneyLbl;
    @FXML private Slider grenadeSlider;
    @FXML private Slider ammoSlider;

    @FXML public void ak47Btn(){

        if (Money >= 500){

            Money -= 500;

            Score += 5000;

            Attack = 45;

            alert.alert("Purchased: AK-47");
            gunID = "AK-47";
            gunSpriteURL = "ff1fbae3c3282a772246605d08225293.png";

            moneyLbl.setText("Money: $"+Money);
        }else if (Money < 500){

            amountOver = (int) (Money - 500);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML public void grenadeBtn(){

        if (Money >= (1000 * grenadeSlider.getValue())){

            Money -= (1000 * grenadeSlider.getValue());

            Score += (10000 * grenadeSlider.getValue());

            Grenades += grenadeSlider.getValue();

            alert.alert("Purchased: GRENADE ("+(int)grenadeSlider.getValue()+")");

            moneyLbl.setText("Money: $"+Money);
        }else if (Money < (1000 * grenadeSlider.getValue())){

            amountOver = (int) (Money - (1000 * grenadeSlider.getValue()));

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML public void ammoBtn(){

        if (Money >= (25 * ammoSlider.getValue())){

            Money -= (25 * ammoSlider.getValue());

            Score += (10000 * ammoSlider.getValue());

            Ammo += ammoSlider.getValue();

            alert.alert("Purchased: AMMO ("+(int)ammoSlider.getValue()+")");

            moneyLbl.setText("Money: $"+Money);
        }else if (Money < (25 * ammoSlider.getValue())){

            amountOver = (int) (Money - (25 * ammoSlider.getValue()));

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML public void backBtn(){

        store.storeMethod();
        MainWindow.setScene(storeScene);
    }

    @FXML private void initialize(){

        moneyLbl.setText("Your money: $"+(int)Money);
    }
}
