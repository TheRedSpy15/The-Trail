import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.io.IOException;

public class StoreController extends Main {

    @FXML private Slider waterSlider;
    @FXML private Slider foodSlider;
    @FXML private Label moneyLabel;
    @FXML private Label currentWater;
    @FXML private Label currentFood;
    private static int amountOver = 0;

    @FXML
    private void purchase(){

        double cartValue;

        // cart value is that of the values of water slider, food slider, and ammo slider combined
        cartValue = (waterSlider.getValue() * 0.25) + (foodSlider.getValue() * 0.50);

        // if statement if money is greater or equal to cart value, then add slider values
        if (Money >= cartValue){

            // Subtracting money by cart value
            Money -= cartValue;

            // adding a int casted value of food slider to food
            Food += (int) foodSlider.getValue();

            // adding a int casted value of water slider to water
            Water += (int) waterSlider.getValue();

            // Run travel setup
            travel.travelSetup();

            // determines if the scene is being used in alert window or main window and changes depending on that
            if (MainWindow.getScene().equals(storeScene)){

                // setting window scene to travel pane
                MainWindow.setScene(new Scene(travelPane));
            }else{

                // Changing alert window scene to settlement scene
                AlertWindow.setScene(CityScene);
            }

        // show not enough money scene in alert window with the amount over
        }else{

            amountOver = (int) (Money - cartValue);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML
    private void gunStoreBtn(){

        MainWindow.setScene(gunStoreScene);
    }

    // code run on initialization of scene
    @FXML
    public void initialize(){

        // On starting class, update labels and max value of sliders
        moneyLabel.setText("Money: $"+(int) Money);
        currentFood.setText("FOOD: "+Food);
        currentWater.setText("WATER: "+Water);

        // random store inventory
        waterSlider.setMax((int) (Math.random() * 500) + 300);
        foodSlider.setMax((int) (Math.random() * 500) + 300);
    }
}
