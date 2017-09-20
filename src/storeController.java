import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.io.IOException;

public class StoreController extends Main {

    public Slider waterSlider;
    public Slider foodSlider;
    public Slider ammoSlider;
    public Button purchaseBtn;
    public Label moneyLabel;

    private static AlertBox Alt = new AlertBox();
    public Label currentWater;
    public Label currentFood;
    public Label currentAmmo;

    public void purchase(){

        purchaseBtn.setOnAction(e -> {

            double cartValue;

            cartValue = (waterSlider.getValue() * 5) + (foodSlider.getValue() * 10) + (ammoSlider.getValue() * 25);

            if (Money >= cartValue){

                Money -= cartValue;

                setFood((int) foodSlider.getValue());
                Ammo += ammoSlider.getValue();
                Water += waterSlider.getValue();

                try {
                    trvl.travelSetup();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Window.setScene(new Scene(travelPane));

            }else{

                amountOver -= (Money - cartValue);

                Alt.alertMenu(4);
            }
        });
    }

    @FXML
    public void initialize(){

        moneyLabel.setText("Money: $"+(int) Money);
        currentAmmo.setText("AMMO: "+Ammo);
        currentFood.setText("FOOD: "+getFood());
        currentWater.setText("WATER: "+Water);
    }
}
