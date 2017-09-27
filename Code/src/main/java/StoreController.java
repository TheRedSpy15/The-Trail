import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.io.IOException;

public class StoreController extends Main {

    @FXML private Slider waterSlider;
    @FXML private Slider foodSlider;
    @FXML private Slider ammoSlider;
    @FXML private Label moneyLabel;
    @FXML private Label currentWater;
    @FXML private Label currentFood;
    @FXML private Label currentAmmo;
    private static AlertBox Alt = new AlertBox();

    @FXML
    private void purchase(){

        double cartValue;

        // cart value is that of the values of water slider, food slider, and ammo slider combined
        cartValue = (waterSlider.getValue() * 0.25) + (foodSlider.getValue() * 0.50) + (ammoSlider.getValue() * 5);

        // if statement if money is greater or equal to cart value, then add slider values
        if (Money >= cartValue){

            Money -= cartValue;

            Food = (int) foodSlider.getValue();
            Ammo += ammoSlider.getValue();
            Water += waterSlider.getValue();

            // Run travel setup
            try {
                trvl.travelSetup();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Window.setScene(new Scene(travelPane));

            if (AlertWindow.isShowing()){

                AlertWindow.close();
            }

        }else{

            amountOver -= (Money - cartValue);

            Alt.notEnoughMoney();
        }
    }

    @FXML
    public void initialize(){

        // On starting class, update labels and max value of sliders
        moneyLabel.setText("Money: $"+(int) Money);
        currentAmmo.setText("AMMO: "+Ammo);
        currentFood.setText("FOOD: "+Food);
        currentWater.setText("WATER: "+Water);
        moneyLabel.setText("Money: "+Money);

        // random store inventory
        waterSlider.setMax((int) (Math.random() * 1000) + 250);
        foodSlider.setMax((int) (Math.random() * 1000) + 250);
        ammoSlider.setMax((int) (Math.random() * 100));
    }
}