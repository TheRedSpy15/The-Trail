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

            // Subtracting money by cart value
            Money -= cartValue;

            // adding a int casted value of food slider to food
            Food += (int) foodSlider.getValue();

            // adding a int casted value of ammo slider to ammo
            Ammo += (int) ammoSlider.getValue();

            // adding a int casted value of water slider to water
            Water += (int) waterSlider.getValue();

            // Run travel setup with a try/catch
            try {
                trvl.travelSetup();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // setting window scene to travel pane
            Window.setScene(new Scene(travelPane));

            // closes the alert window if it is open
            if (AlertWindow.isShowing()){

                AlertWindow.close();
            }

        // show not enough money scene in alert window with the amount over
        }else{

            amountOver -= (Money - cartValue);

            Alt.notEnoughMoney();
        }
    }

    // code run on initialization of scene
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
